package com.example.myapplication.finishedView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinishedDeadline() {
    Column(
        Modifier
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ) {
        FinishedCard()
    }
}

@Composable
fun FinishedCard() {

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(10.dp)
            .height(65.dp)
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .width(5.dp)
        )
        Row(
            Modifier
                .padding(start = 5.dp, end = 5.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                Modifier
                    .padding(top = 2.dp, start = 10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Red)
                    .width(90.dp)
                    .height(35.dp),
                contentAlignment = Alignment.Center

            ) {
                Text(

                    text = "30/11/22",
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Column() {
                Text(
                    text = "Mobile Application",
                    fontSize = 15.sp
                )
                Text(
                    fontSize = 10.sp,
                    color = Color.Gray,
                    text = "Five steps to finish this coursework..."
                )
            }
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .scale(1.2f),
            )
        }
    }
}

@Composable
fun DeadlineCard() {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.Red)
                .width(80.dp)
                .height(35.dp)
        )
        {
            Text(

                text = "30/11/22",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
        Spacer(
            modifier = Modifier
                .width(5.dp)
        )
        Column(
            Modifier
                .padding(start = 5.dp, end = 5.dp)
        ) {
            Text(
                text = "Mobile Application",
                fontSize = 15.sp
            )
            Text(
                fontSize = 10.sp,
                color = Color.Gray,
                text = "Five steps to finish this coursework."
            )

        }
        Spacer(
            modifier = Modifier
                .size(70.dp)
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .scale(1.2f),
        )
    }
}