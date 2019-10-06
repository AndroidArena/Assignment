package com.my.cvapp.userprofile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserInfo(

    @Expose
    @SerializedName("Name")
    val Name: String? = null,

    @Expose
    @SerializedName("Description")
    val Description: String? = null,

    @Expose
    @SerializedName("About")
    val About: String? = null,

    @Expose
    @SerializedName("avatar")
    val avatar: String? = null,

    @Expose
    @SerializedName("post")
    val post: String? = null,

    @Expose
    @SerializedName("address")
    val address: String? = null,

    @Expose
    @SerializedName("Technology")
    val Technology: String? = null,

    @Expose
    @SerializedName("mobile")
    val mobile: String? = null,

    @Expose
    @SerializedName("Certification")
    val Certification: String? = null

    )
{
    override fun toString(): String {
        return "UserInfo(Name=$Name, Description=$Description, avatar=$avatar , About=$About, post=$post , address =$address, Technology=$Technology , mobile=$mobile , avatar = $avatar , Certification = $Certification)"
    }
}
