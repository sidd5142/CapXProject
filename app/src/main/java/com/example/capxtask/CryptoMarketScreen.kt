package com.example.capxtask

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.sp
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.capxtask.models.CryptoCurrency
import com.example.capxtask.viewModel.CryptoViewModel

@Composable
fun CryptoMarketScreen(viewModel: CryptoViewModel = viewModel()) {
    val cryptoList by viewModel.searchResult.observeAsState(emptyList())
    val notFound by viewModel.notFound.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(false)
    var searchQuery by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff597adc)), // Dark blue background
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            label = { Text("Search Crypto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 32.dp, end = 10.dp, bottom = 10.dp)
                .height(48.dp)
                .background(Color(0xff839ce5), shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp), // Inner padding for the TextField
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xff839ce5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.searchCrypto(searchQuery)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon", tint = Color.White)
                }
            }
        )

        // Updated button with rounded corners and bold text
//        Button(
//            onClick = {
//                viewModel.searchCrypto(searchQuery)
//            },
//            modifier = Modifier
//                .padding(vertical = 16.dp)
//                .background(Color(0xff839ce5), shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)) // Rounded button
//        ) {
//            Text(
//                text = "Search",
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//        }

        when {
            isLoading -> { // Show loader while fetching data
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            notFound -> { // Show "Not Found" UI if no data is found
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = "Not Found",
                        modifier = Modifier.size(150.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text("No Results Found", color = Color.Red, modifier = Modifier.padding(16.dp))
                }
            }
            else -> { // Show the list when data is available
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cryptoList) { crypto ->
                        CryptoListItem(crypto = crypto)
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CryptoListItem(crypto: CryptoCurrency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xff839ce5), shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)) // Light blue background for the item
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter("https://s2.coinmarketcap.com/static/img/coins/64x64/${crypto.id}.png"),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp)) // Reduced spacer width

        Column(modifier = Modifier.weight(1f)) {
            Text(text = crypto.name, style = MaterialTheme.typography.h6.copy(fontSize = 14.sp))
            Text(text = crypto.symbol, style = MaterialTheme.typography.subtitle2.copy(fontSize = 12.sp))
        }
        Column(horizontalAlignment = Alignment.End) {
            val price = crypto.quotes.firstOrNull()?.price ?: 0.0
            val change = crypto.quotes.firstOrNull()?.percentChange24h ?: 0.0

            Text(text = "$${String.format("%.2f", price)}", style = MaterialTheme.typography.body1.copy(fontSize = 14.sp)) // Adjust font size
            Text(
                text = "${String.format("%.2f", change)}%",
                color = if (change > 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.body2.copy(fontSize = 12.sp) // Adjust font size
            )
        }
    }
}
