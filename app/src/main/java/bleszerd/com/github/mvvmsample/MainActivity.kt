package bleszerd.com.github.mvvmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bleszerd.com.github.mvvmsample.databinding.ActivityMainBinding
import bleszerd.com.github.mvvmsample.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.user.observe(this, Observer {
            val (email, username, image) = it
            val user = User(email, username, image)

            updateUiInfo(user)
        })

        viewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }

    fun updateUiInfo(user: User) {
        val (email, username, image) = user
        binding.email.text = email
        binding.username.text = username
        binding.image.text = image
    }
}