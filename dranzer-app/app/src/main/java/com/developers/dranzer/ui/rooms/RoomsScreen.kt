package com.developers.dranzer.ui.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developers.dranzer.R
import com.developers.dranzer.ui.weather.WeatherCard

@Composable
@Preview
fun RoomsScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Text(
            text = "Hi, Jasmeet!",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )
        Text(
            text = "Welcome to your smart home",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
        )
        WeatherCard(
            Modifier
                .height(150.dp)
                .padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Room",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp)
            )
            Text(
                text = "View all",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(end = 12.dp, top = 12.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(12.dp)
        ) {
            items(7) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .padding(4.dp)
                        .height(150.dp),
                    elevation = 4.dp,
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            painter = painterResource(id = R.drawable.ic_light_bulb),
                            contentDescription = "Room image"
                        )
                        Column {
                            Text(
                                text = "Living room",
                                color = Color(0xff47614e),
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Text(
                                text = "8 devices",
                                color = Color(0xffadbfb3),
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}