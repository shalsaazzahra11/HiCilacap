package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Jenis(
    val id: String,
    val img_transportasi: String,
    val nama_transportasi: String,
    val keberangkatan_transportasi: String,
    val jam_berangkat: String,
    val jam_datang: String,
    val harga: String,
    val kontak: String,
    val fasilitas: String
): Serializable