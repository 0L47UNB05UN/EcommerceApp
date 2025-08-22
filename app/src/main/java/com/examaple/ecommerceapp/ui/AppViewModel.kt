package com.examaple.ecommerceapp.ui

import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.examaple.ecommerceapp.EcommerceApplication
import com.examaple.ecommerceapp.data.NetworkRepository
import com.examaple.ecommerceapp.model.AuthResponse
import com.examaple.ecommerceapp.model.ProductResponse
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AppUiState {
    data class SuccessGetProduct(val products: List<ProductResponse>) : AppUiState
    data class SuccessLogin(val response: AuthResponse) : AppUiState
    data class SuccessRegister(val response: AuthResponse) : AppUiState
    object Error: AppUiState
    object Loading: AppUiState}

class AppViewModel( val networkRepository: NetworkRepository ): ViewModel(){

    var products by mutableStateOf<List<ProductResponse>>(emptyList())
    var uiState: AppUiState by mutableStateOf(AppUiState.Loading)

    fun getProducts(){
        viewModelScope.launch {
            uiState = AppUiState.Loading
            uiState = try{
                AppUiState.SuccessGetProduct(
                    networkRepository.getProducts()
                )
            } catch(e: IOException){
                AppUiState.Error
            }catch (e: HttpException ){
                AppUiState.Error
            }
        }

    }

    fun login(username: String, password: String){}

    fun register(username: String, password: String){}

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[AndroidViewModelFactory.APPLICATION_KEY]) as EcommerceApplication
                AppViewModel(application.container.networkRepository)
            }
        }
    }
}