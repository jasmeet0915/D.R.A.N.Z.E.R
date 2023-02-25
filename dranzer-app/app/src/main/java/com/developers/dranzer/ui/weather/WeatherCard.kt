package com.developers.dranzer.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developers.dranzer.R

@Composable
@Preview
fun WeatherCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        //#47614e
        backgroundColor = Color(0xff47614e),
        modifier = modifier
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_wb_cloudy_white_24dp),
                    contentDescription = "weather-image",
                    contentScale = ContentScale.None,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = "Cloudy",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    Text(
                        text = "Singosary, Malang",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth().padding(end = 24.dp),
                    contentAlignment = Alignment.TopEnd,
                ) {
                    Text(
                        text = "36°",
                        style = MaterialTheme.typography.h4,
                        color = Color(0xffecc53c),
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, bottom = 12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = "36°",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sensible",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = "48%",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Humidity",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = "0.5",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "W. Force",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = "1009 hPa",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pressure",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
            }
        }
    }
}