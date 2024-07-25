package com.toyota.livecodingdemo.data

import com.toyota.livecodingdemo.data.model.HouseModel
import retrofit2.Response
import javax.inject.Inject

class HouseRepository @Inject constructor(private val service: HouseService){
    suspend fun fetchHouse(): Response<HouseModel> {
        return service.fetchHouse()
    }
}