package com.example.lokermu.RegisterLogin

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lokermu.Activity.HomeActivity
import com.example.lokermu.Models.Pengguna
import com.example.lokermu.R
import com.example.lokermu.Utils.CustomProgressDialog
import com.example.lokermu.Utils.NetworkCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val progressDialog = CustomProgressDialog()
    private val networkCheck = NetworkCheck(this)
    lateinit var userr: MutableList<Pengguna>
    var FACEBOOK_URL = "https://www.facebook.com/"
    var FACEBOOK_PAGE_ID = "https://www.facebook.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setContentView(R.layout.activity_login)
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
        userr = mutableListOf()

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

        tv_daftar.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btn_login.setOnClickListener {
            UserLogin()
        }

        img_fb.setOnClickListener {
            try {
                val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
                if (versionCode >= 3002850) {
                    val uri: Uri = Uri.parse("fb://facewebmodal/f?href=$FACEBOOK_URL")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                } else {
                    // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/336227679757310")
                        )
                    )
                }
            } catch (e: PackageManager.NameNotFoundException) {
                // Facebook is not installed. Open the browser
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL)))
            }
        }


    }

    private fun UserLogin() {
        val email = findViewById<EditText>(R.id.et_email_login)
        val password = findViewById<EditText>(R.id.et_kata_sandi_login)

        val inputEmail = email.text.toString().trim()
        val inputKataSandi = password.text.toString().trim()


        //validasi email
        if (inputEmail.isEmpty()) {
            email.error = "Masukkan email"
            email.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            email.error = "Email harus sesuai format"
            email.requestFocus()
            return
        }
        //validasi kata sandi
        if (inputKataSandi.isEmpty()) {
            password.error = "Isi kata sandi"
            password.requestFocus()
            return
        }

        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid").child("profile")
        val cekEmail = ref.orderByChild("email").equalTo(inputEmail)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(inputEmail, inputKataSandi)
            .addOnCompleteListener(this@LoginActivity) {
                if (it.isSuccessful) {
                    FirebaseAuth.getInstance().currentUser?.let { task ->
                        if (task.isEmailVerified) {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Verifikasi email dulu",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    cekEmail.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                //Data ada
                            } else {
                                Toast.makeText(
                                    this@LoginActivity, "Email tidak terdaftar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

                } else {
                    Toast.makeText(this, "Email atau sandi salah", Toast.LENGTH_SHORT).show()
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
    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().currentUser?.let {
            if (it.isEmailVerified) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
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