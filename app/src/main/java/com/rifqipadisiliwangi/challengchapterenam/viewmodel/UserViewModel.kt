package com.rifqipadisiliwangi.challengchapterenam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rifqipadisiliwangi.challengchapterenam.datastore.UserPreferencesRepository
import kotlinx.coroutines.launch


class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository = UserPreferencesRepository(application)
    val dataUser = repository.readData.asLiveData()

    fun editData(email : String, username : String, password : String, session : String) = viewModelScope.launch{
        repository.saveData(email, username, password, session)
    }

    fun editSession(session: String) = viewModelScope.launch{
        repository.saveSession(session)
    }

    fun clearData() = viewModelScope.launch {
        repository.deleteData()
    }

}