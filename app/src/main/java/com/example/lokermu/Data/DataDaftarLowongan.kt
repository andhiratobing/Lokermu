package com.example.lokermu.Data

import com.example.lokermu.Models.DaftarLowongan

object DataDaftarLowongan {
    private var data = arrayOf(
        arrayOf(
            "https://sewatama.com/app/wp-content/uploads/2017/10/sewatama.jpg",
            "Market Data Analyst",
            "PT Sumberdaya Sewatama",
            "Kota Jakarta Selatan",
            "Full Time",
            "Job Qualification\n"+
                    "~ Familiar with Market Research or Corporate\n"+
                    "   Planning\n"+
                    "~ Market Analysis skill\n"+
                    "~ Experienced in B2B Research\n"+
                    "~ Familiar with Database and Analytics Tools\n"+
                    "~ Have Basic Intermediate IoT Knowledge\n"+
                    "   (Digitalization)\n",
            "Requirement\n"+
                    "~ Candidate must possess at least Bachelor Degree\n"+
                    "   from statistic,mathematic, adn informatic system\n"+
                    "   (prefearably)\n"+
                    "~ At least 2 year(s) of Working experience in the relate\n"+
                    "   field is required for this position\n"+
                    "~ Required skill(s) : Market research, market analyst,\n"+
                    "   corporate planning\n"+
                    "~ Able to join immediately\n"+
                    "~ Preferable Staff specialized in marketing/Business\n"+
                    "   Development or equivalent\n"+
                    "~ Placement in : Head Office Jakarta\n",
            "-",
            "20 Januari 2021",
            "~Wawancara",
            "sumberdaya@gmail.com"
        )
    )
    val listData: ArrayList<DaftarLowongan>
        get() {
            val list = ArrayList<DaftarLowongan>()
            for (aData in data) {
                val daftar = DaftarLowongan()
                daftar.image = aData[0]
                daftar.jabatan = aData[1]
                daftar.instansi = aData[2]
                daftar.lokasi = aData[3]
                daftar.status = aData[4]
                daftar.Job_Qualification = aData[5]
                daftar.Requirement = aData[6]
                daftar.benefit = aData[7]
                daftar.deadline = aData[8]
                daftar.jenis_tes = aData[9]
                daftar.kontak_person = aData[10]

                list.add(daftar)
            }
            return list
        }
}