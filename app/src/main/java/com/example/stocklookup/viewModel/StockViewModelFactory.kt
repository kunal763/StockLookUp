package com.example.stocklookup.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocklookup.api.StockApi

class StockViewModelFactory(private val stockApi: StockApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return StockViewModel(stockApi) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
    }
}
