package com.example.lokermu.RegisterLogin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.lokermu.*
import com.example.lokermu.Models.Pengguna
import com.example.lokermu.Utils.CustomProgressDialog
import com.example.lokermu.Utils.NetworkCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class RegisterActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private val progressDialog = CustomProgressDialog()
    private val networkCheck = NetworkCheck(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        networkCheck.result = { isAvailable, type ->
            when (isAvailable) {
                true -> {
                    when (type) {
                        NetworkCheck.ConnectionType.Wifi -> {
                            // Toast.makeText(this, "Terhubung Wifi", Toast.LENGTH_SHORT).show()
                        }
                        NetworkCheck.ConnectionType.Cellular -> {
                            //  Toast.makeText(this, "Terhubung data seluler", Toast.LENGTH_SHORT) .show()
                        }
                        else -> {
                        }
                    }
                }
                false -> {
                    Toast.makeText(this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
                }

            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = Color.TRANSPARENT
        }

        setWindowFlag(
            this,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false
        )
        window.statusBarColor = Color.TRANSPARENT
        toolbar_register.title = null
        setSupportActionBar(toolbar_register)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()

        btn_daftar.setOnClickListener {
            DaftarAkun()
        }
    }

    @SuppressLint("NewApi")
    private fun DaftarAkun() {
        val image_profile = "default"
        val nama_lengkap = et_nama_lengkap.text.toString().trim()
        val email = et_Email.text.toString().trim()
        val no_hp = et_no_hp.text.toString().trim()
        val kata_sandi = et_kata_sandi.text.toString().trim()


        val date = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss").withZone(ZoneId.of("UTC+7"))
            .format(Instant.now())
        val timestamp_pendaftaran_akun = date

        //validasi nama pangkalan
        if (nama_lengkap.isEmpty()) {
            et_nama_lengkap.error = "Masukkan nama lengkap"
            et_nama_lengkap.requestFocus()
            return
        } else if (nama_lengkap.length < 5) {
            et_nama_lengkap.error = "Nama lengkap terlalu pendek"
            et_nama_lengkap.requestFocus()
            return
        }

        //validasi email
        if (email.isEmpty()) {
            et_Email.error = "Masukkan email"
            et_Email.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_Email.error = "Email harus sesuai format"
            et_Email.requestFocus()
            return
        }

        //Valdasi nomor handphone
        if (no_hp.isEmpty()) {
            et_no_hp.error = "Masukkan nomor handphone"
            et_no_hp.requestFocus()
            return
        } else if (no_hp.length < 12) {
            et_no_hp.error = "Masukkan nomor handphone dengan benar"
            et_no_hp.requestFocus()
            return
        }

        //validasi kata sandi
        if (TextUtils.isEmpty(kata_sandi)) {
            et_kata_sandi.error = "Masukkan kata sandi"
            et_kata_sandi.requestFocus()
            return
        } else if (kata_sandi.length < 9) {
            et_kata_sandi.error = "Kata sandi minimal 8 digit"
            et_kata_sandi.requestFocus()
            return
        }

        if (cekbox.isChecked){
            //Toast.makeText(this, "Sudah ceklis", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Ceklis Syarat dan Ketentuan", Toast.LENGTH_SHORT).show()
            return
        }


        val id_pengguna = UUID.randomUUID().toString()
        val user = Pengguna(
            id_pengguna,
            image_profile,
            nama_lengkap,
            email,
            no_hp,
            kata_sandi,
            alamat = "",
            timestamp_pendaftaran_akun
        )

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, kata_sandi)
            .addOnCompleteListener(this) { task ->
                progressDialog.show(this, "Tolong tunggu...")
                if (task.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().uid ?: ""
                    val ref = FirebaseDatabase.getInstance().getReference("users/$uid")
                        .child("profile")
                    ref.child(uid).setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            progressDialog.dialog.dismiss()
                            AwesomeDialog.build(this)
                                .titleSuccess("Selamat")
                                .bodySuccess("Pendaftaran akun berhasil")
                                .iconSuccess(R.raw.success)
                                .onPositive("Lanjutkan") {
                                    val user = FirebaseAuth.getInstance().currentUser
                                    user!!.sendEmailVerification().addOnCompleteListener {
                                        Toast.makeText(
                                            this,
                                            "Email verifikasi telah di kirim, Silahkan cek email anda",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        AwesomeDialog.build(this).dismiss()
                                        val intent = Intent(
                                            applicationContext,
                                            LoginActivity::class.java
                                        )
                                        startActivity(intent)
                                    }
                                }

                        } else {
                            progressDialog.dialog.dismiss()
                            AwesomeDialog.build(this)
                                .titleFailed("Maaf")
                                .bodyFailed("Pendaftaran gagal")
                                .iconFailed(R.raw.failed)
                                .onNegative("Ulangi") {
                                    AwesomeDialog.build(this).dismiss()
                                }
                        }
                    }
                } else {
                    progressDialog.dialog.dismiss()
                    AwesomeDialog.build(this)
                        .titleFailed("Maaf")
                        .bodyFailed("Email sudah ada")
                        .iconFailed(R.raw.failed)
                        .onNegative("Ulangi") {
                            AwesomeDialog.build(this).dismiss()
                        }
                }
            }

    }

    override fun onResume() {
        super.onResume()
        networkCheck.register()
    }

    override fun onStop() {
        super.onStop()
        networkCheck.unregister()
    }




    override fun onBackPressed() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)

        if (item.itemId == android.R.id.home) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        //Set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }
}