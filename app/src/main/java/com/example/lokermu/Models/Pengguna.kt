package com.example.lokermu.Models

class Pengguna(
    val id_pengguna: String?,
    var image_profile: String?,
    var nama_lengkap: String?,
    var email: String?,
    var no_hp: String?,
    var kata_sandi: String?,
    var alamat: String,
    var timestamp_pendaftaran_akun: String?
){
    constructor() : this(
        "",
        "default",
        "",
        "",
        "",
        "",
        "",
        "")
}