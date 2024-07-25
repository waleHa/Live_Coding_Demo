package com.toyota.livecodingdemo.data

import com.toyota.livecodingdemo.data.model.HouseModel
import retrofit2.Response
import retrofit2.http.GET

interface HouseService {
    @GET("info.0.json")
    suspend fun fetchHouse(): Response<HouseModel>
}