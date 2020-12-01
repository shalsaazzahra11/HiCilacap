package org.d3ifcool.hicilacap.ui.wisata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.hicilacap.model.Wisata
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.service

class WisataViewModel: ViewModel() {

    private val wisataData = MutableLiveData<List<Wisata>>()
    private val status = MutableLiveData<ApiStatus>()

    fun getStatus(): LiveData<ApiStatus> = status
    fun getDataWisata(): LiveData<List<Wisata>> = wisataData

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

            wisataData.postValue(result.wisata)

            status.postValue(ApiStatus.SUCCESS)

        } catch (e: Exception) {
            status.postValue(ApiStatus.FAILED)
            Log.d("Wisata", e.message.toString())
        }
    }
}