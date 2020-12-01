package org.d3ifcool.hicilacap

import org.d3ifcool.hicilacap.model.Wisata

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data:List<Wisata>)
}