package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.addNew.AddNewItem
import com.example.myapplication.permission.SinglePermission
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SinglePermission()
                AddNewItem()
                MainScreen()
            }
        }
    }
}


