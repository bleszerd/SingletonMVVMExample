package bleszerd.com.github.mvvmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bleszerd.com.github.mvvmsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.user.observe(this, Observer {
            Log.d("DEBUGA", it.toString())
        })

        viewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}