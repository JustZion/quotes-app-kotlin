package com.example.quotesapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quotesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getQuotes()

        binding.refreshButton.setOnClickListener {
            getQuotes()
        }


    }

    private fun setProgressBar(progress: Boolean) {
        if (progress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.refreshButton.visibility = View.GONE
        }
        else {
            binding.refreshButton.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

        }
    }

    private fun showQuotes(data: DataResponse) {
        binding.quotesText.text =  data.q
        binding.quotesTextExtra.text =  data.a
    }

    private fun getQuotes() {
        setProgressBar(true)
        GlobalScope.launch {
            try {
                val response = RetrofitInstance.quoteApi.getRandomQuotes()
                runOnUiThread {
                    setProgressBar(false)
                    response.body()?.first()?.let {
                        showQuotes(it)
                    }
                }
            } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Something went wrong, please check your internet connection", Toast.LENGTH_LONG).show()

                    }
            }
        }
    }
}