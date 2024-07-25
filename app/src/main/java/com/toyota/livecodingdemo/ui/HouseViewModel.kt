package com.toyota.livecodingdemo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toyota.livecodingdemo.data.HouseRepository
import com.toyota.livecodingdemo.data.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//UseCase

@HiltViewModel
class HouseViewModel @Inject constructor(private val repository: HouseRepository,) : ViewModel() {
    private val _houseSuccess = MutableStateFlow<HouseModel?>(null)
    val houseSuccess = _houseSuccess.asStateFlow()
    val _loading = MutableStateFlow<Boolean>(true)
    val _error = MutableStateFlow<String>("")

    init {
        fetchHouse()
        Log.i("TAG:", fetchHouse().toString())
    }

    fun fetchHouse() {
        viewModelScope.launch (Dispatchers.IO){
           _loading.emit(true)
            try {
                val response = repository.fetchHouse()
                if (response.isSuccessful){
                    _houseSuccess.emit(response.body())
                }else{
                    _error.emit(response.message())
                }
            }catch (e:Exception){
                _error.emit(e.localizedMessage)
            }finally {
                _loading.emit(false)
            }
        }
    }
}