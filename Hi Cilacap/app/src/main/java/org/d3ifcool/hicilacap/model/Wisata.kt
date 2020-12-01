package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Wisata(
    val id: String,
    val img_wisata: String,
    val nama_wisata: String,
    val detail_wisata: String,
    val lokasi_wisata: String
) : Serializable