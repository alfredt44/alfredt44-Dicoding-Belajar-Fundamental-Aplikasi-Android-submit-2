package com.dicoding.submissionbfaa2.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.submissionbfaa2.data.model.User
import com.dicoding.submissionbfaa2.data.remote.ApiClient
import com.dicoding.submissionbfaa2.data.remote.ApiService
import com.dicoding.submissionbfaa2.util.Resource

class DetailViewModel : ViewModel(){
    var username : String? = null
    private val api : ApiService by lazy { ApiClient.getApiClient() }
    fun getDetail(username : String?) = liveData<Resource<User>> {
        val response = api.searchDetail(username)
        if (response.isSuccessful){
            emit(Resource.Success(response.body()))
        }else{
            emit(Resource.Failure(response.message()))
        }
    }

    fun getListFollowers() = liveData<Resource<List<User>>> {
        val response = api.listFolowers(username)
        if (response.isSuccessful){
            emit(Resource.Success(response.body()))
        }else{
            emit(Resource.Failure(response.message()))
        }
    }
    fun getListFollowing() = liveData<Resource<List<User>>> {
        val response = api.listFollowing(username)
        if (response.isSuccessful){
            emit(Resource.Success(response.body()))
        }else{
            emit(Resource.Failure(response.message()))
        }
    }
}