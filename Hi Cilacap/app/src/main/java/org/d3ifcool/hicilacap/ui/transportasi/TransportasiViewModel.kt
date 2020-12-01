package org.d3ifcool.hicilacap.ui.transportasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.hicilacap.model.Transportasi
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.service

class TransportasiViewModel : ViewModel() {

    private val transportasiData = MutableLiveData<List<Transportasi>>()
    private val status = MutableLiveData<ApiStatus>()

    fun getStatus(): LiveData<ApiStatus> = status
    fun getDataTransportasi(): LiveData<List<Transportasi>> = transportasiData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestData()
            }
        }
    }

    private suspend fun requestData() {
        try {
            status.postValue(ApiStatus.LOADING)
            val result = service.getData()

            transportasiData.postValue(result.transportasi)

            status.postValue(ApiStatus.SUCCESS)
        } catch (e: Exception) {
            status.postValue(ApiStatus.FAILED)
            Log.d("Transportasi", e.message.toString())
        }
    }
}