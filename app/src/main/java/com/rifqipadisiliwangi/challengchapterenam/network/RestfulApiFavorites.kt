package com.rifqipadisiliwangi.challengchapterenam.network

import com.rifqipadisiliwangi.challengchapterenam.model.FavoritesResponseItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulApiFavorites {

    @GET("favorites")
    fun getAllMovie() : Call<List<FavoritesResponseItem>>
    @POST("favorites")
    fun addNewMovie(@Body res : FavoritesResponseItem) : Call<FavoritesResponseItem>
    @DELETE("favorites/{id}")
    fun deleteMovie(@Path("id") id : Int) : Call<Int>
}