package com.rifqipadisiliwangi.challengchapterenam.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rifqipadisiliwangi.challengchapterenam.model.ResponMovieItem
import com.rifqipadisiliwangi.challengchapterenam.network.RestfulMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(var api : RestfulMovie): ViewModel(){

    var liveDataVidio: MutableLiveData<ResponMovieItem>
    var liveDataMovie: MutableLiveData<ResponMovieItem> = MutableLiveData()
    var updateUser : MutableLiveData<ResponMovieItem> = MutableLiveData()
    var loading = MutableLiveData<Boolean>()

    init {
        liveDataVidio = MutableLiveData()
    }

    fun getliveDataVidio() :MutableLiveData<ResponMovieItem>{
        return liveDataVidio
    }

    fun CallApiVidio(){
        api.getAllMovie()
            .enqueue(object : Callback<ResponMovieItem>{
                override fun onResponse(
                    call: Call<ResponMovieItem>,
                    response: Response<ResponMovieItem>
                ) {
                    if (response.isSuccessful){
                        liveDataVidio.postValue(response.body())
                        Log.d("data",response.body()?.results.toString())
                    }else{
                        Log.d("data",response.body()?.results.toString())
                    }
                }

                override fun onFailure(call: Call<ResponMovieItem>, t: Throwable) {
                    Log.d("data",call.toString())
                }

            })
    }

}