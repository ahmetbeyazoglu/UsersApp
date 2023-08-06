package com.herpestes.usersapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.herpestes.usersapp.ui.theme.UsersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Anasayfa()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Anasayfa() {
    Scaffold(

        topBar = {
            TopAppBar(title = { Text(text = "Kişiler") })
        },
        content = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = colorResource(id = R.color.teal_200),
                content = {
                    androidx.compose.material.Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "",
                        tint = Color.White
                    )


                }
            )
        }


    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UsersAppTheme {
        Anasayfa()
    }
}