package com.example.lokermu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.lokermu.R
import com.example.lokermu.RegisterLogin.RegisterActivity
import com.example.lokermu.Utils.CustomProgressDialog
import com.example.lokermu.Utils.NetworkCheck
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val progressDialog = CustomProgressDialog()
    private val networkCheck = NetworkCheck(this)
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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

        card_view_daftar_lowongan.setOnClickListener {
            intent = Intent(this, DaftarLowonganActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        card_view_lamaranku.setOnClickListener {
            intent = Intent(this, LamarankuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }



    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast!!.cancel()
            super.onBackPressed()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
            return
        } else {
            backToast = Toast.makeText(baseContext, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT)
            backToast!!.show()
        }
        backPressedTime = System.currentTimeMillis()
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