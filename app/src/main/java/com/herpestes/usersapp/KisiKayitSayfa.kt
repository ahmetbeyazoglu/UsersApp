package com.herpestes.usersapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun KisiKayitSayfa() {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    Scaffold(

        topBar = {
            TopAppBar(title = { Text(text = "Kişi Kayıt") })
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = tfKisiAd.value, onValueChange = { tfKisiAd.value = it }, label = {
                    Text(text = "Kisi Ad")})
                TextField(value = tfKisiTel.value, onValueChange = { tfKisiTel.value = it }, label = {
                    Text(text = "Kisi tel")})
                Button(onClick = {
                    val kisi_ad = tfKisiAd.value
                    val kisi_tel = tfKisiTel.value
                    Log.e("Kişi kayıt", "$kisi_ad - $kisi_tel")

                    localFocusManager.clearFocus()

                }, modifier = Modifier.size(250.dp,50.dp)) {
                    Text(text = "Kaydet")
                    
                }
            }

        },


        )
}