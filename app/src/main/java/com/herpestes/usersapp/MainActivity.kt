package com.herpestes.usersapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.herpestes.usersapp.entity.Kisiler
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
                    SayfaGecisleri ()
                }
            }
        }
    }
}
@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa" ){
        composable("anasayfa"){
            Anasayfa(navController = navController)
        }
        composable("kisi_kayit_sayfa"){
            KisiKayitSayfa()
        }
        composable("kisi_detay_sayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){
                type = NavType.StringType }
        )){
            val json = it.arguments?.getString("kisi")
            val nesne = Gson().fromJson(json, Kisiler::class.java)
            KisiDetaySayfa(gelenKisi = nesne)
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Anasayfa(navController: NavController) {

    val aramayapiliyormu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val kisilerListesi = remember { mutableStateListOf<Kisiler>() }

    LaunchedEffect(key1 = true) {
        val k1 = Kisiler(1, "Ahmet", "11111111111")
        val k2 = Kisiler(2, "Ali", "222222222")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    if (aramayapiliyormu.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                Log.e("Kişi arama", it)
                            },
                            label = { Text(text = "Ara") },
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
                        )
                    } else {
                        Text(text = "Kişiler")
                    }
                },
                actions = {
                    if (aramayapiliyormu.value) {
                        IconButton(onClick = {
                            aramayapiliyormu.value = false
                            tf.value = ""
                        }) {
                            androidx.compose.material.Icon(
                                painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    } else {
                        IconButton(onClick = {

                        }) {
                            androidx.compose.material.Icon(
                                painter = painterResource(id = R.drawable.arama_resim),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }

                }
            )
        },
        content = {
            LazyColumn {
                items(count = kisilerListesi.count(),
                    itemContent = {
                        val kisi = kisilerListesi[it]
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.clickable {
                                val kisiJson = Gson().toJson(kisi)
                                navController.navigate("kisi_detay_sayfa/${kisiJson}")
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${kisi.kisi_ad} - ${kisi.kisi_ad}")
                                    androidx.compose.material.Icon(
                                        painter = painterResource(id = R.drawable.sil_resim),
                                        contentDescription = "",
                                        tint = Color.Gray
                                    )
                                }
                            }

                        }
                    })
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("kisi_kayit_sayfa") },
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

    }
}