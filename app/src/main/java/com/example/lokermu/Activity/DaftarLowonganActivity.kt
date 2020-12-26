package com.example.lokermu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokermu.Adapter.AdapterDaftarLowongan
import com.example.lokermu.BuildConfig
import com.example.lokermu.Data.DataDaftarLowongan
import com.example.lokermu.Models.DaftarLowongan
import com.example.lokermu.R
import com.example.lokermu.Utils.NetworkCheck
import kotlinx.android.synthetic.main.activity_daftar_lowongan.*
import java.util.*

class DaftarLowonganActivity : AppCompatActivity() {
    private val networkCheck = NetworkCheck(this)
    lateinit var recyclerView: RecyclerView
    private var list_lowongan : ArrayList<DaftarLowongan> = arrayListOf()
    private var tampilkan_list_lowongan : ArrayList<DaftarLowongan> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_lowongan)
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
        toolbar_daftar_lowongan.title = null
        setSupportActionBar(toolbar_daftar_lowongan)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val pencarian_daftar_lowongan = findViewById<SearchView>(R.id.pencarian_daftar_lowongan)
        pencarian_daftar_lowongan.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    tampilkan_list_lowongan.clear()
                    val pencarian = newText.toLowerCase(Locale.getDefault())
                    list_lowongan.forEach {
                        if (it.jabatan.toLowerCase(Locale.getDefault()).contains(pencarian)) {
                            tampilkan_list_lowongan.add(it)
                        }
                        recylerview_daftar_lowongan.adapter!!.notifyDataSetChanged()
                    }

                } else {
                    tampilkan_list_lowongan.clear()
                    tampilkan_list_lowongan.addAll(DataDaftarLowongan.listData)
                   // recylerview_daftar_lowongan.adapter?.notifyDataSetChanged()

                }
                return true

            }

        })
        recyclerView = findViewById(R.id.recylerview_daftar_lowongan)
        recylerview_daftar_lowongan.setHasFixedSize(true)
        recylerview_daftar_lowongan.layoutManager = LinearLayoutManager(this)

        val listDaftarLowonganAdapter = AdapterDaftarLowongan(list_lowongan)
        recyclerView.adapter = listDaftarLowonganAdapter
        list_lowongan.addAll(DataDaftarLowongan.listData)
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
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, HomeActivity::class.java)
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