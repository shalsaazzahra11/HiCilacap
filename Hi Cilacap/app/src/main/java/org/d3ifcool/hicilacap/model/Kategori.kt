package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Kategori(
    val id: Long,
    val img_kuliner: String,
    val nama_kuliner: String,
    val detail_kuliner: String,
    val alamat_kuliner: String,
    val lokasi_kuliner: String,
    val kontak: String

) : Serializable