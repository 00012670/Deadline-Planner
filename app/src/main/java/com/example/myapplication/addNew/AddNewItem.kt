package com.example.myapplication.addNew

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.network.deadline.DeadlineRequest
import com.example.myapplication.network.myResponse.MyDeadlineResponse

@Preview
@Composable
fun AddNewItem(viewModel: AddNewViewModel = AddNewViewModel()) {

    val context = LocalContext.current

    val title = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val info = remember { mutableStateOf("") }
    val steps = remember { mutableStateOf("") }

    val isProgressVisible = remember { mutableStateOf(false) }
    val response by viewModel.deadlineInsertResponse.observeAsState()


    Column(
        modifier = Modifier
            .padding(25.dp),
    ) {
        TitleInput(titleInput = title.value, onTitleChange = { title.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        DateInput(dateInput = date.value, onDateChange = { date.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        InfoInput(infoInput = info.value, onInfoChange = { info.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        StepsInput(stepsInput = steps.value, onStepsChange = { steps.value = it })
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(15.dp))

        val validationMsg = stringResource(id = R.string.validation_msg)
        AddButton {
            if (isInputValid(title.value, date.value, info.value, steps.value)) {
                viewModel.saveNewDeadlineToRemoteDb(
                    DeadlineRequest(
                        title.value,
                        date.value,
                        info.value,
                        steps.value
                    )
                )
                isProgressVisible.value = true

            } else {
                val toast = Toast.makeText(context, validationMsg, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }

    response?.let { ProgressWidget(response = it, isVisible = isProgressVisible.value, context) }

}

@Composable
private fun ProgressWidget(response: MyDeadlineResponse, isVisible: Boolean, context: Context) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 25.sp,
                text =
                if (response.status.isEmpty()) stringResource(id = R.string.in_progress_mgs) //by default status is "", so if it is empty that means network request hasn't returned a response yet
                else if (response.status == "OK") stringResource(id = R.string.successfully_saved_msg)
                else stringResource(id = R.string.failed_to_save_msg)
            )
        }

        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
    }
}

@Composable
fun TitleInput(titleInput: String, onTitleChange: (String) -> Unit) {
    Text(
        fontSize = 20.sp,
        text = stringResource(id = R.string.title),
    )

    Spacer(
        modifier = Modifier
            .height(10.dp)
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .width(370.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
        value = titleInput, onValueChange = { onTitleChange(it) },
        label = {
            Text(
                stringResource(id = com.example.myapplication.R.string.assignment_name),
                color = Color.Gray,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
fun DateInput(dateInput: String, onDateChange: (String) -> Unit) {
    Text(
        fontSize = 20.sp,
        text = stringResource(id = R.string.date),
        )

    Spacer(
        modifier = Modifier
            .height(10.dp)
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .width(370.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
        value = dateInput, onValueChange = { onDateChange(it) },
        label = {
            Text(
                stringResource(id = com.example.myapplication.R.string.dd_mm_yy),
                color = Color.Gray,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
fun InfoInput(infoInput: String, onInfoChange: (String) -> Unit) {
    Text(
        fontSize = 20.sp,
        text = stringResource(id = R.string.info),
    )

    Spacer(
        modifier = Modifier
            .height(10.dp)
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .width(370.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
        value = infoInput, onValueChange = { onInfoChange(it) },
        label = {
            Text(
                stringResource(id = com.example.myapplication.R.string.short_desc),
                color = Color.Gray,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
fun StepsInput(stepsInput: String, onStepsChange: (String) -> Unit) {
    Text(
        fontSize = 20.sp,
        text = stringResource(id = R.string.steps),
    )

    Spacer(
        modifier = Modifier
            .height(10.dp)
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .width(370.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .background(White),
        value = stepsInput, onValueChange = { onStepsChange(it) },
        label = {
            Text(
                stringResource(id = com.example.myapplication.R.string.imp_steps),
                color = Color.Gray,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
private fun AddButton(onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .width(80.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)


    ) {
        Text(
            fontSize = 15.sp,
            text = stringResource(id = R.string.add),
            color = Color.White
        )
    }
}

private fun isInputValid(
    title: String,
    date: String,
    info: String,
    steps: String
): Boolean {
    if (title.isBlank() || info.isBlank() || steps.isBlank() || date.isBlank()) return false
    return true
}