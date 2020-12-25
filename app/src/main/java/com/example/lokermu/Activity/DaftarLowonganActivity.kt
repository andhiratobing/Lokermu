package com.example.lokermu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokermu.Adapter.AdapterDaftarLowongan
import com.example.lokermu.Data.DataDaftarLowongan
import com.example.lokermu.Models.DaftarLowongan
import com.example.lokermu.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_daftar_lowongan.*
import java.util.ArrayList

class DaftarLowonganActivity : AppCompatActivity() {
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var bottomSheetView: View
    lateinit var recyclerView: RecyclerView
    private var list_lowongan : ArrayList<DaftarLowongan> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_lowongan)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN )
            window.statusBarColor = Color.TRANSPARENT
        }

        setWindowFlag(
            this,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false
        )
        window.statusBarColor = Color.TRANSPARENT


        recyclerView = findViewById(R.id.recylerview_daftar_lowongan)
        recylerview_daftar_lowongan.setHasFixedSize(true)
        recylerview_daftar_lowongan.layoutManager = LinearLayoutManager(this)

        val listDaftarLowonganAdapter = AdapterDaftarLowongan(list_lowongan)
        recyclerView.adapter = listDaftarLowonganAdapter

        list_lowongan.addAll(DataDaftarLowongan.listData)
    }

    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
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