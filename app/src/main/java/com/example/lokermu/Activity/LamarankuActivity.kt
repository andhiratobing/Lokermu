package com.example.lokermu.Activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lokermu.BuildConfig
import com.example.lokermu.R
import com.example.lokermu.RegisterLogin.LoginActivity
import kotlinx.android.synthetic.main.activity_daftar_lowongan.*
import kotlinx.android.synthetic.main.activity_lamaranku.*


class LamarankuActivity : AppCompatActivity() {

    val textKet =
            """
            Keterangan:
            1. Pengirim akan dialihkan ke email
            2. Berkas akan dikirimkan ke email perusahaan yang sudah tertaut
            3. Informasi lebih lanjut akan di informasikan melalui email pengirim
            """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lamaranku)

        //val keterangan: String = keterangan.text.toString()
        keterangan.text =  textKet
        println(textKet)

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
        toolbar_lamaranku.title = null
        setSupportActionBar(toolbar_lamaranku)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        img_kirim_email.setOnClickListener {
            val TO = arrayOf("")
            val emailIntent = Intent(Intent.ACTION_SENDTO)

            //  emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Melamar Pekerjaan")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            try {
                startActivity(Intent.createChooser(emailIntent, "Kirim Email"))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "There is no email client installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



    override fun onBackPressed() {
        val intent = Intent(this@LamarankuActivity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@LamarankuActivity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)

        if (item.itemId == R.id.home) {
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