package com.examaple.ecommerceapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examaple.ecommerceapp.model.ProductResponse
import com.examaple.ecommerceapp.ui.AppUiState
import com.examaple.ecommerceapp.ui.AppViewModel

@Composable
fun HomeScreen(
    onAddToCart: (ProductResponse) -> Unit,
    appViewModel: AppViewModel,
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
) {
    when(appUiState){
        is AppUiState.Error ->
            Text("Error")
        is AppUiState.Loading ->
            Text("Loading")
        is AppUiState.SuccessGetProduct ->
            LazyColumn {
                items(items= appUiState.products, key={ product->product.id}) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(product.name, style = MaterialTheme.typography.bodySmall)
                            Text(product.description ?: "")
                            Text("₦${product.price}")
                            Button(onClick = { onAddToCart(product) }) {
                                Text("Add to Cart")
                            }
                        }
                    }
                }
            }
        is AppUiState.SuccessLogin -> TODO()
        is AppUiState.SuccessRegister -> TODO()
    }
}

