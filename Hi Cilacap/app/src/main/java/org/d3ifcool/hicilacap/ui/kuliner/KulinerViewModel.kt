package org.d3ifcool.hicilacap.ui.kuliner

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.hicilacap.model.Kategori
import org.d3ifcool.hicilacap.model.Kuliner
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.service

class KulinerViewModel: ViewModel() {

    private val kulinerData = MutableLiveData<List<Kuliner>>()
    private val status = MutableLiveData<ApiStatus>()

    fun getStatus(): LiveData<ApiStatus> = status
    fun getDataKuliner(): LiveData<List<Kuliner>> = kulinerData


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

            kulinerData.postValue(result.kuliner)

            status.postValue(ApiStatus.SUCCESS)

        } catch (e: Exception) {
            status.postValue(ApiStatus.FAILED)
            Log.d("Kuliner", e.message.toString())
        }
    }
}