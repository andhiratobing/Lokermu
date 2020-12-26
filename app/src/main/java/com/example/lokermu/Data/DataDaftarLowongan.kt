package com.example.lokermu.Data

import com.example.lokermu.Models.DaftarLowongan

object DataDaftarLowongan {
    private var data = arrayOf(
        arrayOf(
            "https://sewatama.com/app/wp-content/uploads/2017/10/sewatama.jpg",
            "Market Data Analyst",
            "PT Sumberdaya Sewatama",
            "Jakarta Selatan, Indonesia",
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
        ),
        arrayOf(
            "data:image/png;base64,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",
            "Software Engineer-Android",
            "Traveloka",
            "Jakarta Raya, Indonesia",
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
            "30 Januari 2021",
            "~Wawancara",
            "traveloka@gmail.com"
        ),
        arrayOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJY1jKTKkZDftYmTyQueODz_yyqi0zFEP-iw&usqp=CAU",
            "Senior Android Engineer-GoPlay",
            "Gojek",
            "Jakarta Raya, Indonesia",
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
            "02 Februari 2021",
            "~Wawancara",
            "gojek@gmail.com"
        ),
        arrayOf(
            "data:image/jpeg;base64,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",
            "React Native Developer",
            "PT Diksha Teknologi Indonesia",
            "Area DKI Jakarta, Indonesia",
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
            "29 Februari 2021",
            "~Wawancara",
            "Diksha@gmail.com"
        ),
        arrayOf(
            "https://res-5.cloudinary.com/crunchbase-production/image/upload/c_lpad,h_170,w_170,f_auto,b_white,q_auto:eco/gwcgxuqsfo9g2n1wp5de",
            "UI/UX Designer",
            "Paper.id",
            "Jakarta Raya, Indonesia",
            "Part Time",
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
            "29 Februari 2021",
            "~Wawancara",
            "Paper@gmail.com"
        ),
        arrayOf(
            "https://media-exp1.licdn.com/dms/image/C510BAQEYR-NmXh2pIw/company-logo_200_200/0/1570777926398?e=1617235200&v=beta&t=U-zRm-wvZQ8rtMvQPrSKHvx9fRofZhdbXmNckHZMRTg",
            "UI/UX Designer",
            "PT Smartfren Telecom Tbk",
            "Jakarta Raya, Indonesia",
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
            "29 Februari 2021",
            "~Wawancara",
            "smartfren@gmail.com"
        ),
        arrayOf(
            "https://media-exp1.licdn.com/dms/image/C560BAQEIu6Alk0BuZA/company-logo_200_200/0/1544679117747?e=1617235200&v=beta&t=W2wjgHLebpZ4vyilm_8EStZpl5lhcp5CtltvFb1g0KU",
            "Backend Enginer",
            "Ruangguru.com",
            "Jakarta Raya, Indonesia",
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
            "29 April 2021",
            "~Wawancara",
            "ruangguru@gmail.com"
        ),
        arrayOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJY1jKTKkZDftYmTyQueODz_yyqi0zFEP-iw&usqp=CAU",
            "Data Scientist - GoMerchant",
            "Gojek",
            "Jakarta Raya, Indonesia",
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
            "01 April 2021",
            "~Wawancara",
            "gojek@gmail.com"
        ),
        arrayOf(
            "https://media-exp1.licdn.com/dms/image/C510BAQEoOK0z9HZcQA/company-logo_200_200/0/1551151781846?e=1617235200&v=beta&t=_v7ybfNS5PNCn8-yTQBqZHSFNxfg5lFl-IN-2g3e3iI",
            "Software Developer In Test Speacialist",
            "LinkAja",
            "Jakarta Raya, Indonesia",
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
            "01 April 2021",
            "~Wawancara",
            "linkaja@gmail.com"
        ),
        arrayOf(
            "https://media-exp1.licdn.com/dms/image/C510BAQEYxNmNMsS-gQ/company-logo_200_200/0/1548209473653?e=1617235200&v=beta&t=wJJX8TPbTQ6ut-bqQtlztWHoNJGq82A-F6zLangtMps",
            "Software Engineer",
            "cermati.com",
            "Jakarta Raya, Indonesia",
            "Part Time",
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
            "01 April 2021",
            "~Wawancara",
            "cermati@gmail.com"
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