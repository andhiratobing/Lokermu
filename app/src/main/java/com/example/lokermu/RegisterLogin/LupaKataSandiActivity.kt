package com.example.lokermu.RegisterLogin

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.lokermu.Activity.ProfileActivity.Companion.setWindowFlag
import com.example.lokermu.BuildConfig
import com.example.lokermu.R
import com.example.lokermu.Utils.CustomProgressDialog
import com.example.lokermu.Utils.NetworkCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_lupa_kata_sandi.*

class LupaKataSandiActivity : AppCompatActivity() {
    private val progressDialog = CustomProgressDialog()
    private val networkCheck = NetworkCheck(this)
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_kata_sandi)

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
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            window.statusBarColor = Color.TRANSPARENT
        }

        setWindowFlag(
            this,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false
        )
        window.statusBarColor = Color.TRANSPARENT

        toolbar_lupa_kata_sandi.title = null
        setSupportActionBar(toolbar_lupa_kata_sandi)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        button_reset_password.setOnClickListener {
            reset_katasandi()
        }

    }

        private fun reset_katasandi() {
            val email = text_email.text.toString().trim()

            if (email.isEmpty()) {
                text_email.error = "Isi emailmu ya!"
                text_email.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                text_email.error = "Email di temukan, cek email untuk reset sandi"
                text_email.requestFocus()
                return
            }

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                progressDialog.show(this, "Tolong tunggu...")
                if (task.isSuccessful) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        // Dismiss progress bar after 2 seconds
                        progressDialog.dialog.dismiss()
                        Toast.makeText(this@LupaKataSandiActivity,
                            "Cek Email Anda ",
                            Toast.LENGTH_SHORT).show()
                    }, 2000)
                }else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        // Dismiss progress bar after 2 seconds
                        progressDialog.dialog.dismiss()
                        Toast.makeText(this@LupaKataSandiActivity,
                            "Email Anda Salah",
                            Toast.LENGTH_SHORT).show()
                    }, 2000)
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
            val intent = Intent(this@LupaKataSandiActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val intent = Intent(this@LupaKataSandiActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            if (item.itemId == R.id.home) {
                finish()
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
