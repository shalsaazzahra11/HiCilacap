package org.d3ifcool.hicilacap.model

import java.io.Serializable

data class Data (
    val kuliner: List<Kuliner>,
    val wisata: List<Wisata>,
    val transportasi: List<Transportasi>
): Serializable