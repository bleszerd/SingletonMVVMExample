package bleszerd.com.github.mvvmsample.repository

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
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            job.complete()
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