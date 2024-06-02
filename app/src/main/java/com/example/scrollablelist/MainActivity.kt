package com.example.scrollablelist

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose.ScrollableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(){
    val nav = rememberNavController()
    NavHost(
        navController = nav,
        startDestination = "halamanAwal",
        builder = {
            composable("halamanAwal"){
                AnimeApp(nav)
            }
            composable("halaman2"+"/{judul}"+"/{poster}"+"/{description}"){navBackStack ->
                val judul = navBackStack.arguments?.getString("judul")
                val poster = navBackStack.arguments?.getString("poster")
                val description = navBackStack.arguments?.getString("description")

                Detail(
                    judul!!,
                    poster!!,
                    description!!
                    )
            }
        }
    )
}


