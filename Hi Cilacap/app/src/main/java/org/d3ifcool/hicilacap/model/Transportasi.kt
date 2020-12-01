package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Transportasi(
    val id: String,
    val img_transportasi: String,
    val nama_transportasi: String,
    val jenis: List<Jenis>
): Serializable