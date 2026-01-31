package com.notifier.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.notifier.app.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.helloText.text = "Hello World"
        loadForecast()
    }

    private fun loadForecast() {
        lifecycleScope.launch {
            binding.temperatureText.text = getString(R.string.loading)
            try {
                val temp = withContext(Dispatchers.IO) {
                    WeatherService.getTemperatureAt6pm(65.0124, 25.4682)
                }
                binding.temperatureText.text = if (temp != null) {
                    "%.1f °C".format(temp)
                } else {
                    getString(R.string.error)
                }
            } catch (e: Exception) {
                binding.temperatureText.text = getString(R.string.error)
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
