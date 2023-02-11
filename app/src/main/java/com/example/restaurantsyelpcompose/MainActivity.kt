package com.example.restaurantsyelpcompose

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsyelpcompose.ViewModel.RestaurantViewModel
import com.example.restaurantsyelpcompose.ui.screens.homeScreen.HomeScreen
import com.example.restaurantsyelpcompose.ui.theme.RestaurantsYelpComposeTheme
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // this is use to request permissions to the user
    private val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        val viewModel: RestaurantViewModel by viewModels()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,
                    false) -> {
                    Log.d(
                        TAG,
                        "permissions: permissions granted: ${permissions[Manifest.permission.ACCESS_COARSE_LOCATION]}"
                    )
                    viewModel.permissionsGranted = true
                    //request location.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION,
                    false) -> {
                    //request location, not used.
                }
            }
        } else {
            val permissionsGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION]
                    ?: permissions[Manifest.permission.ACCESS_FINE_LOCATION]
                    ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainApp {
                val navController = rememberNavController()
                val viewModel: RestaurantViewModel by viewModels()

                NavHost(navController = navController,
                    startDestination = "home") {
                    composable(route = "home") {
                        HomeScreen(viewModel, navController, requestPermission)
                    }
                    composable("details") {

                    }
                }//matthew testing in forked project
            }
        }
    }
}

@Composable
fun MainApp(content: @Composable ()->Unit) {
    RestaurantsYelpComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp {
        Greeting("Android")
    }
}