package com.dicoding.submissionbfaa2.ui.main

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.submissionbfaa2.data.model.User
import com.dicoding.submissionbfaa2.data.remote.ApiClient
import com.dicoding.submissionbfaa2.data.remote.ApiService
import com.dicoding.submissionbfaa2.util.Resource

class MainViewModel : ViewModel() {
    private val api : ApiService by lazy { ApiClient.getApiClient() }
    fun searchUser(query : String?) = liveData <Resource<List<User>>>{
        val response = api.searchUser(query)
        if (response.isSuccessful){
            emit(Resource.Success(response.body()!!.items))
        }else{
            emit(Resource.Failure(response.message()))
        }
    }
}