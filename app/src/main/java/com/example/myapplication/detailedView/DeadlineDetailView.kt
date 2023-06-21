package com.example.myapplication.detailedView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun DeadlineDetailView(
    deadlineId: String,
    viewModel: DetailedViewModel = DetailedViewModel(deadlineId)
) {
    val deadline by viewModel.deadlineLiveData.observeAsState()
    if (deadline != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
        ) {
            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.title),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .width(370.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))

            ) {
                Title(title = deadline!!.title)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.date),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .width(370.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))

            ) {
                Date(date = deadline!!.date)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.info),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .width(370.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))

            ) {
                Info(info = deadline!!.info)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.steps),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .width(370.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))

            ) {
                Steps(steps = deadline!!.steps)
            }
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(top = 11.dp, start = 10.dp)
    )
}

@Composable
private fun Date(date: String) {
    Text(

        text = date,
        color = Color.Red,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(top = 11.dp, start = 10.dp)
    )
}

@Composable
private fun Info(info: String) {
    Text(
        text = info,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(top = 11.dp, start = 10.dp)
    )
}

@Composable
private fun Steps(steps: String) {
    Text(
        text = steps,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(top = 11.dp, start = 10.dp)
    )
}








