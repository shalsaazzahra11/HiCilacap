package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Kuliner(
    val id: String,
    val img_kuliner: String,
    val nama_kuliner: String,
    val kategori: List<Kategori>

) : Serializable