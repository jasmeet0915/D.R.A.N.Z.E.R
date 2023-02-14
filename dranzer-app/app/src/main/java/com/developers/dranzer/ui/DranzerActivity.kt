package com.developers.dranzer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developers.dranzer.ui.theme.DranzerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DranzerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DranzerTheme {
                DranzerMainScreen()
            }
        }
    }
}