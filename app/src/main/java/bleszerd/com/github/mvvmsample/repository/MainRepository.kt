package bleszerd.com.github.mvvmsample.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import bleszerd.com.github.mvvmsample.api.RetrofitBuilder
import bleszerd.com.github.mvvmsample.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {
    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        job = Job()
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        val userResponse = RetrofitBuilder.apiService.getUser(userId)
                        val user = userResponse.body()

                        withContext(Main){
                            if(userResponse.isSuccessful){
                                value = user
                                job.complete()
                            } else {
                                job.cancel()
                                Log.d("Error", "Error")
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