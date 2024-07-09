package com.live.tasknew

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.live.tasknew.roomDb.AppDatabase
import com.live.tasknew.roomDb.Course
import com.live.tasknew.adapter.HorizontalAdapter
import com.live.tasknew.adapter.VerticalAdapter
import com.live.tasknew.databinding.ActivityMainBinding
import com.live.tasknew.network.ProgressDialog
import com.live.tasknew.viewmodel.SharedPreferencesUtil
import com.live.tasknew.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private val progressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (internetAvailability(this))
        {
            getImages()
        }
        else
        {
            // Retrieve saved image response from SharedPreferences
            val savedImageResponse = SharedPreferencesUtil.getImageResponse(this)
            savedImageResponse?.let {
                val horizontalAdapter = HorizontalAdapter(it)
                binding.rvHorizontal.adapter = horizontalAdapter
                val verticalAdapter = VerticalAdapter(it)
                binding.rvVertical.adapter = verticalAdapter
            }
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getImages()
    {
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        viewModel.getImages()

        viewModelSetupAndResponse()
    }

    private fun viewModelSetupAndResponse() {

        viewModel.progressDialogData().observe(this) { isShowProgress ->
            if (isShowProgress) {
                progressDialog.show()
            } else {
                progressDialog.hide()
            }
        }
        viewModel.onGetImages().observe(this) { response ->
            response?.let {
                val horizontalAdapter = HorizontalAdapter(it)
                binding.rvHorizontal.adapter = horizontalAdapter
                val verticalAdapter = VerticalAdapter(it)
                binding.rvVertical.adapter = verticalAdapter
                SharedPreferencesUtil.clearSharedPreferences(this)
                SharedPreferencesUtil.saveImageResponse(this, it)
            }
        }
    }

    private fun internetAvailability(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
