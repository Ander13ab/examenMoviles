package com.example.appexamenapi.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp

import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.appexamenapi.data.model.ProductDto
import com.example.appexamenapi.presentation.viewmodel.ProductViewModel
import com.example.appexamenapi.presentation.viewmodel.UiState

@Composable
fun ProductScreen(vm: ProductViewModel) {
    when (val state = vm.state) {

        UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Error: ${state.msg}")
                Spacer(Modifier.height(12.dp))
                Button(onClick = { vm.loadProducts() }) {
                    Text("Reintentar")
                }
            }
        }

        is UiState.Success -> LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.data, key = { it._id }) { character ->
                ProductCard(character)
            }
        }
    }
}


//private fun buildImageUrl(portraitPath: String?): String? {
//    if (portraitPath.isNullOrBlank()) return null
//
//    return if (portraitPath.startsWith("http")) {
//        portraitPath
//    } else {
//        // CDN oficial de la API
//        "https://peticiones.online/api/products/$portraitPath"
//    }
//}

@Composable
private fun ProductCard(c: ProductDto) {

    val painter = rememberAsyncImagePainter(model = c.image)


    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            // Imagen con estados
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(strokeWidth = 2.dp)
                    }
                    is AsyncImagePainter.State.Error -> {
                        Text("IMG ", style = MaterialTheme.typography.labelSmall)
                    }
                    else -> Unit
                }

                Image(
                    painter = painter,
                    contentDescription = c.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            // Texto
            Column(Modifier.weight(1f)) {
                Text(
                    text = c._id.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.description ?: "—",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.price.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.category,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.image ?: "—",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = c.active.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
//val id: Int,
//val name: String,
//val description: String,
//val price: Double,
//val category: String,
//val image: String,
//val active: Boolean,