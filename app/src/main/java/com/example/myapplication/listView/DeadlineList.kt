package com.example.myapplication.listView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.models.Deadline

@Preview
@Composable
fun DeadlineList(
    onDeadlineClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel()
) {
        Box(modifier = Modifier.fillMaxSize()) {

        val deadlines by viewModel.deadlinesLiveData.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
        )
        {
            deadlines?.let {
                items(items = it.toList(), itemContent = { item ->
                    CustomCard(deadline = item, onDeadlineClick)
                })
            }
        }
    }
}




@Composable
fun CustomCard(deadline: Deadline, onDeadlineClick: (String) -> Unit) {

    var isChecked by remember { mutableStateOf(false) }

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
                .padding(start = 5.dp, end = 5.dp)
                .clickable {
                    onDeadlineClick(deadline.id.toString())
                },
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
                contentAlignment = Center

            ) {
                Date(date = deadline.date)
            }
            Column() {
                Title(title = deadline.title)
                Info(info = deadline.info)
            }
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier
                    .scale(1.2f),

                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Red,
                    checkedColor = Color.White,
                    uncheckedColor = Color.Black

                )
            )
        }
    }
}

@Composable
private fun Date(date: String) {
    Text(

        text = date,
        color = Color.White,
        fontSize = 15.sp,
    )
}


@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 15.sp,
    )
}

@Composable
private fun Info(info: String) {
    Text(
        text = info,
        color = Color.Gray,
        fontSize = 12.sp,
    )
}




