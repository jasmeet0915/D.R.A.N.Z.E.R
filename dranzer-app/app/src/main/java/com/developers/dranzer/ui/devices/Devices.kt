package com.developers.dranzer.ui.devices

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun Devices(
    viewModel: DevicesViewModel
) {
    ConstraintLayout {
        Scaffold (
            backgroundColor = MaterialTheme.colors.primarySurface,
            bottomBar = {

            }
        ) {
            val modifier = Modifier.padding(it)
            Text(text = "Hello Dranzer")
        }
    }
}