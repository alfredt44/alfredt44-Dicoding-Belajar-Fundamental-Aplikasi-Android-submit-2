package com.dicoding.submissionbfaa2.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String = "",
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("public_repos")
    val publicRepos: String = "0",
) : Parcelable