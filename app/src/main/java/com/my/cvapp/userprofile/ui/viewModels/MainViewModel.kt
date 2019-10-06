package com.my.cvapp.userprofile.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.my.cvapp.userprofile.model.ProfileDetail
import com.my.cvapp.userprofile.model.UserInfo
import com.my.cvapp.userprofile.data.repository.MainRepository

class MainViewModel : ViewModel() {
    private var list: List<ProfileDetail> = emptyList()
    private val _isfetchdata: MutableLiveData<Boolean> = MutableLiveData()
    private val _profilelist = MutableLiveData<List<ProfileDetail>>()
    val profilelist: LiveData<List<ProfileDetail>>
        get() = _profilelist

    private val _userInfo: MutableLiveData<UserInfo> = Transformations
        .switchMap(_isfetchdata) {
            MainRepository.getUser()

        } as MutableLiveData<UserInfo>
    val userInfo: LiveData<UserInfo>
        get() = _userInfo


    init {
        _isfetchdata.value = true
    }


    private fun cancelJobs() {
        MainRepository.cancelJobs()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJobs()
    }

//tried to utilize a power of kotlin inbuilt functions if we need to make user
    fun makeProfileList() {

        val strEducation =
            _userInfo.value?.About?.split("/")?.toMutableList()?.get(0)?.split(" ")?.toMutableList()
        val strExperience =
            _userInfo.value?.About?.split("/")?.toMutableList()?.get(1)?.split(" ")?.toMutableList()
        strExperience?.get(0)?.let { strEducation?.add(it) }
        strExperience?.get(1)?.let { strEducation?.add(it) }

        for (i in 0 until (strEducation!!.size)) {
            val type = with(strEducation) { elementAt(i).split("-") }[0]
            val heading = with(strEducation) { elementAt(i).split("-") }[1]
            val subheading = with(strEducation) { elementAt(i).split("-") }[2]
            val date = with(strEducation) { elementAt(i).split("-") }[3]
            list = list + listOf(ProfileDetail(type, heading, subheading, date))
        }
        _profilelist.value = this.list
    }


}
















