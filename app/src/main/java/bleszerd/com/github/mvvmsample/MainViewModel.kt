package bleszerd.com.github.mvvmsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bleszerd.com.github.mvvmsample.model.User
import bleszerd.com.github.mvvmsample.repository.MainRepository

class MainViewModel : ViewModel() {
    private val userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations
        .switchMap(userId) {
            MainRepository.getUser(it)
        }

    fun setUserId(userId: String) {
        val update = userId
        if (this.userId.value == update) {
            return
        }

        this.userId.value = update
    }

    fun cancelJobs() {
        MainRepository.cancelJobs()
    }
}