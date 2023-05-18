package com.example.shared


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.tooling.preview.Preview
import com.example.shared.ui.theme.SharedTheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
class PreferencesManager(context: Context) {
    private val sharedPreferences =context.getSharedPreferences("name",0)
    fun saveName(name: String) {
        sharedPreferences.edit().putString("name", name).apply()
    }
    fun getName(): String? {
        return sharedPreferences.getString("name","")!!
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val preferencesManager = remember {
        PreferencesManager(context)
    }
    val name = preferencesManager.getName() ?: ""
    Column {
        var text by remember { mutableStateOf(name) }

        Text(text = "Data Store")
        TextField(
            value = text,
            onValueChange = {
                text = it
                preferencesManager.saveName(text.toString())
            },
        )
        Button(
            onClick = {
                val storedName = preferencesManager.getName()
            }
        ) {
            Text("Obtener nombre")
        }
    }
}




