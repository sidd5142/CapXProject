package com.example.capxtask.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capxtask.models.CryptoCurrency
import com.example.capxtask.models.MarketModel
import com.example.capxtask.server.RetrofitInstance
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoCurrency>>()
    val cryptoList: LiveData<List<CryptoCurrency>> get() = _cryptoList

    private val _searchResult = MutableLiveData<List<CryptoCurrency>>(emptyList())
    val searchResult: LiveData<List<CryptoCurrency>> get() = _searchResult

    private val _notFound = MutableLiveData<Boolean>()
    val notFound: LiveData<Boolean> get() = _notFound

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchCryptos()
    }

    private fun fetchCryptos() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCryptos()
                if (response.isSuccessful) {
                    // Access the crypto list from the response
                    _isLoading.value = false
                    val cryptoListResponse = response.body()
                    if (cryptoListResponse != null && cryptoListResponse.data.cryptoCurrencyList.isNotEmpty()) {
                        _cryptoList.value = cryptoListResponse.data.cryptoCurrencyList
                        _searchResult.value = cryptoListResponse.data.cryptoCurrencyList
                        Log.d(TAG, "Data fetched successfully: ${cryptoListResponse.data.cryptoCurrencyList}")
                    } else {
                        _cryptoList.value = emptyList()
                        _searchResult.value = emptyList()
                        Log.e(TAG, "No data found.")
                    }
                } else {
                    _cryptoList.value = emptyList()
                    _searchResult.value = emptyList()
                    Log.e(TAG, "Failed to fetch data: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                _cryptoList.value = emptyList()
                _searchResult.value = emptyList()
                Log.e(TAG, "Error occurred: ${e.message}", e)
            }
        }
    }




    fun searchCrypto(query: String) {
        val result = _cryptoList.value?.filter {
            it.name.contains(query, ignoreCase = true) || it.symbol.contains(query, ignoreCase = true)
        }
        if (result.isNullOrEmpty()) {
            _notFound.value = true
        } else {
            _searchResult.value = result
            _notFound.value = false
        }
    }
}
