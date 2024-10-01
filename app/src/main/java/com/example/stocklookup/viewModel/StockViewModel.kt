package com.example.stocklookup.viewModel



import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocklookup.R
import com.example.stocklookup.api.StockApi
import com.example.stocklookup.model.StockResponse
import kotlinx.coroutines.launch

class StockViewModel(private val stockApi: StockApi) : ViewModel() {
    private val _stockData = MutableLiveData<StockResponse?>()
    val stockData: LiveData<StockResponse?> get() = _stockData

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchStockData(symbol: String,apiKey:String) {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {

                val response = stockApi.getStockData(symbol, apiKey)
                _stockData.value = response
            } catch (e: Exception) {
                _error.value = "Failed to fetch stock data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
