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
                    "1. Familiar with Market Research or Corporate Planning\n"+
                    "2. Market Analysis skill\n"+
                    "3. Experienced in B2B Research\n"+
                    "4. Familiar with Database and Analytics Tools\n"+
                    "5. Have Basic Intermediate IoT Knowledge (Digitalization)\n",
            "Requirement\n"+
                    "1. Candidate must possess at least Bachelor Degree from statistic,mathematic, adn informatic system (prefearably)\n"+
                    "2. At least 2 year(s) of Working experience in the relate field is required for this position\n"+
                    "3. Required skill(s) : Market research, market analyst, corporate planning\n"+
                    "4. Able to join immediately\n"+
                    "5. Preferable Staff specialized in marketing / Business Development or equivalent\n"+
                    "6. Placement in : Head Office Jakarta\n",
            "-",
            "20 Januari 2021",
            "Wawancara",


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
                daftar.dateline = aData[8]
                daftar.jenis_tes = aData[9]

                list.add(daftar)
            }
            return list
        }
}