package com.example.lokermu.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lokermu.Models.DaftarLowongan
import com.example.lokermu.R

class AdapterDaftarLowongan(private val list_lowongan : ArrayList<DaftarLowongan>) : RecyclerView.Adapter<AdapterDaftarLowongan.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_recylerview_daftar_lowongan, parent,false )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val daftar_lowongan: DaftarLowongan = list_lowongan[position]
        Glide.with(holder.itemView.context)
            .load(daftar_lowongan.image)
            .apply(RequestOptions())
            .into(holder.img_card_list_lowongan)

        holder.tv_posisi.text = daftar_lowongan.jabatan
        holder.tv_instansi.text = daftar_lowongan.instansi
        holder.tv_lokasi_instansi.text = daftar_lowongan.lokasi

    }

    override fun getItemCount(): Int {
        return list_lowongan.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_card_list_lowongan: ImageView = itemView.findViewById(R.id.img_card_list_lowongan)
        var tv_posisi : TextView = itemView.findViewById(R.id.tv_posisi)
        var tv_instansi : TextView = itemView.findViewById(R.id.tv_instansi)
        var tv_lokasi_instansi : TextView = itemView.findViewById(R.id.tv_lokasi_instansi)

    }

}