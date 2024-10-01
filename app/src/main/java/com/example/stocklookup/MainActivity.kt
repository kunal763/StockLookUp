package com.example.stocklookup


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.stocklookup.api.RetrofitClient
import com.example.stocklookup.databinding.ActivityMainBinding
import com.example.stocklookup.viewModel.StockViewModel
import com.example.stocklookup.viewModel.StockViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val stockApi= RetrofitClient.api
    private val stockViewModel: StockViewModel by viewModels {
        StockViewModelFactory(stockApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide loading indicator at first
        binding.loadingIndicator.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE

        // Set up search button
        binding.searchButton.setOnClickListener {
            val apikey=getString(R.string.api_key)
            val symbol = binding.stockSymbolEditText.text.toString().trim()
            if (symbol.isNotEmpty()) {
                // Show loading indicator
                binding.loadingIndicator.visibility = View.VISIBLE
                stockViewModel.fetchStockData(symbol,apikey)
            } else {
                Toast.makeText(this, "Please enter a stock symbol", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe ViewModel data
        stockViewModel.stockData.observe(this, Observer { stock ->
            if (!stock!!.isEmpty()) {
                // Hide loading indicator
                binding.loadingIndicator.visibility = View.GONE

                // Update UI with stock data
                binding.stockNameTextView.visibility = View.VISIBLE
                binding.stockPriceTextView.visibility = View.VISIBLE
                binding.stockChangeTextView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE

                binding.stockNameTextView.text = stock[0].name
                binding.stockPriceTextView.text = "Price: $${stock[0].price}"
                binding.stockChangeTextView.text = "Change: ${stock[0].change}%"
            }
            else{
                binding.errorTextView.visibility=View.VISIBLE
                binding.errorTextView.text = "The Stock Symbol is Invalid"
                binding.stockNameTextView.visibility = View.GONE
                binding.stockPriceTextView.visibility = View.GONE
                binding.stockChangeTextView.visibility = View.GONE
            }
        })

        // Observe error state
        stockViewModel.error.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                // Hide loading indicator and display error
                binding.loadingIndicator.visibility = View.GONE
                binding.stockNameTextView.visibility = View.GONE
                binding.stockPriceTextView.visibility = View.GONE
                binding.stockChangeTextView.visibility = View.GONE

                binding.errorTextView.visibility = View.VISIBLE
                binding.errorTextView.text = "Internet Connection Error"
            }
        })

        // Observe loading state

        stockViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.GONE
            }
        })
    }
}
