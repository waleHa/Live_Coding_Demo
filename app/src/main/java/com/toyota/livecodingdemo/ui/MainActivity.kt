package com.toyota.livecodingdemo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.toyota.livecodingdemo.data.model.HouseModel
import com.toyota.livecodingdemo.ui.theme.LiveCodingDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveCodingDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    House(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun House(modifier: Modifier, viewModel: HouseViewModel = hiltViewModel()) {
    val house by viewModel.houseSuccess.collectAsState()
    val loading by viewModel._loading.collectAsState()
    val error by viewModel._error.collectAsState()
    Box {
        when {
            loading -> {
                CircularProgressIndicator()
            }
            error.isNotEmpty() -> {
                Text(text = error)
            }
            else ->{
                ShowUI(modifier, house)
            }
        }
    }

}

@Composable
private fun ShowUI(
    modifier: Modifier,
    house: HouseModel?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        house?.img?.let {
            AsyncImage(
                model = it,
                contentDescription = "House Image",
                modifier = Modifier.size(350.dp)
            )
        }
        house?.month?.let {
            Log.i("TAG:", it)
            Text(
                it,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFDDD222)),
                textAlign = TextAlign.Center,

                )
        }
        house?.year?.let {
            Text(
                it,
                Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFDDD222)),
                textAlign = TextAlign.Center,

                )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    LiveCodingDemoTheme {
        House(modifier = Modifier)
    }
}