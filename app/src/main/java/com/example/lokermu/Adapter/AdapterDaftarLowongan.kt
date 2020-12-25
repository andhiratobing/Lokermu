package com.example.lokermu.Adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lokermu.Models.DaftarLowongan
import com.example.lokermu.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.row_detail_lowongan.view.*


class AdapterDaftarLowongan(private val list_lowongan: ArrayList<DaftarLowongan>) : RecyclerView.Adapter<AdapterDaftarLowongan.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_recylerview_daftar_lowongan,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        lateinit var bottomSheetDialog: BottomSheetDialog
        lateinit var bottomSheetView: View


        val daftar_lowongan: DaftarLowongan = list_lowongan[position]
        Glide.with(holder.itemView.context)
            .load(daftar_lowongan.image)
            .apply(RequestOptions())
            .into(holder.img_card_list_lowongan)

        holder.tv_posisi.text = daftar_lowongan.jabatan
        holder.tv_instansi.text = daftar_lowongan.instansi
        holder.tv_lokasi_instansi.text = daftar_lowongan.lokasi

        val context = holder.itemView.context

        holder.cardview_list_lowongan.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.row_detail_lowongan,
                bottomSheetDialog.findViewById<LinearLayout>(R.id.bottom_sheet_container)
            )
            Glide.with(holder.itemView.context)
                .load(list_lowongan[position].image)
                .apply(RequestOptions())
                .into(bottomSheetView.image_lowongan)
            bottomSheetView.tv_jabatan.text = list_lowongan[position].jabatan
            bottomSheetView.tv_instansi.text = list_lowongan[position].instansi
            bottomSheetView.tv_lokasi.text = list_lowongan[position].lokasi
            bottomSheetView.tv_isi_status.text = list_lowongan[position].status
            bottomSheetView.tv_isi_pekerjaan.text = list_lowongan[position].Job_Qualification
            bottomSheetView.tv_isi_persyaratan.text = list_lowongan[position].Requirement
            bottomSheetView.tv_isi_benefit.text = list_lowongan[position].benefit
            bottomSheetView.tv_isi_deadline.text = list_lowongan[position].deadline
            bottomSheetView.tv_isi_tes.text = list_lowongan[position].jenis_tes
            bottomSheetView.tv_isi_contact_person.text = list_lowongan[position].kontak_person
/*
            bottomSheetView.btn_daftar_pekerjaan.setOnClickListener {
                //list_lowongan[position].kontak_person
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, daftar_lowongan.kontak_person)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Melamar Pekerjaan")
                intent.putExtra(Intent.EXTRA_TEXT, "Saya telah melampirkan berkas pendaftaran pekerjaan")

            context.startActivity(Intent.createChooser(intent, "Send feedback"))


            }


 */
            bottomSheetView.btn_daftar_pekerjaan.setOnClickListener {
                val TO = arrayOf(list_lowongan[position].kontak_person)
                val emailIntent = Intent(Intent.ACTION_SENDTO)

              //  emailIntent.data = Uri.parse("mailto:")
                emailIntent.type = "text/plain"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Melamar Pekerjaan")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "")

                try {
                    context.startActivity(Intent.createChooser(emailIntent, "Kirim Email"))
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        "There is no email client installed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

    }

    override fun getItemCount(): Int {
        return list_lowongan.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_card_list_lowongan: ImageView = itemView.findViewById(R.id.img_card_list_lowongan)
        var tv_posisi : TextView = itemView.findViewById(R.id.tv_posisi)
        var tv_instansi : TextView = itemView.findViewById(R.id.tv_instansi)
        var tv_lokasi_instansi : TextView = itemView.findViewById(R.id.tv_lokasi_instansi)
        var cardview_list_lowongan : CardView = itemView.findViewById(R.id.cardview_list_lowongan)

    }

}