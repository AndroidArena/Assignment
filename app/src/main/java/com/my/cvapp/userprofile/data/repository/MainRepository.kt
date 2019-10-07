package com.my.cvapp.userprofile.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.my.cvapp.api.MyRetrofitBuilder
import com.my.cvapp.userprofile.model.UserInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.HttpException

//Repository file is responsible for data and notify to viewmodel
object MainRepository {

   private var job: CompletableJob? = null

    fun getUser(): LiveData<UserInfo>{
        job = Job()
        return object: LiveData<UserInfo>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {

                            val user = MyRetrofitBuilder.apiService.getUser()
                            withContext(Main) {

                                try {
                                    value = user
                                    theJob.complete()
                                } catch (e: HttpException) {
                                    //toast("Exception ${e.message}")
                                } catch (e: Throwable) {
                                   // toast("Ooops: Something else went wrong")
                                }
                            }
                    }

                }

            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }

}

















