package com.example.project.ui.home

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.remote.EventRequest
import com.example.project.ui.calendar.Calendar
import com.example.project.ui.calendar.CalendarViewModel
import com.example.project.ui.list.List
import com.himanshoe.kalendar.ui.firey.DaySelectionMode

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen( homeViewModel: HomeViewModel= viewModel()) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    val isCreatePopupShown = homeUiState.createPopupState == CreateState.PopupShown
    val isEventPopupShown = homeUiState.eventPopupState == EventState.PopupShown



    Box() {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Error", fontWeight = FontWeight.Bold,
                    color = Color(0xFFff9999),
                    fontSize = 32.sp,
                )
            }
            Calendar(selectionMode = DaySelectionMode.Single)


                List()


        }
        if (isEventPopupShown || isCreatePopupShown ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x66000000))

            )
        }
        if (isEventPopupShown) {
            EventPopup(onDismiss = {
                homeViewModel.eventTogglePopup()
            })
        }

        if (isCreatePopupShown) {
            CreatePopup(onDismiss = {
                homeViewModel.createTogglePopup()
            })
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(25.dp)
                .size(65.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                homeViewModel.createTogglePopup()

                      },
            shape = CircleShape,
            contentColor = Color(0xFFff9999),
            containerColor = Color.White,
        ) {
            Text("+", fontSize = 30.sp)

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePopup(onDismiss: () -> Unit, homeViewModel: HomeViewModel = viewModel(), calendarViewModel: CalendarViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    val calendarUiState by calendarViewModel.uiState.collectAsState()
    val startDate = calendarUiState.range.start
    val endDate = calendarUiState.range.end
    // 팝업 콘텐츠를 이곳에 추가
    Box(
        modifier = Modifier
            .clickable(onClick = onDismiss)
            .padding(16.dp)
            .fillMaxSize()
            , // 팝업 외부를 클릭하면 닫히도록 함
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .clickable {}
            .background(
                color = Color(0xFFfff5f7),
                shape = RoundedCornerShape(2)
            )

        , verticalArrangement = Arrangement.Center,) {
            // 제목 입력란

            // 캘린더
            Box(modifier = Modifier
                ) {
                Calendar(selectionMode = DaySelectionMode.Range)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier= Modifier
                .padding(5.dp)
                .height(30.dp)){
                TextField(
                    value = startTime,
                    onValueChange = { startTime = it
                        homeViewModel.updateStartTime(startTime) },
                    label = { Text(text="시작날짜", fontSize = 11.sp) },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(20.dp)
                        .weight(1f),
                    shape= RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = endTime,
                    onValueChange = { endTime = it
                        homeViewModel.updateEndTime(endTime) },
                    label = { Text(text="시작시간", fontSize = 11.sp) },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(20.dp)
                        .weight(1f),
                    shape= RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier= Modifier
                .padding(5.dp)
                .height(30.dp)){
                TextField(
                    value = startTime,
                    onValueChange = { startTime = it
                        homeViewModel.updateStartTime(startTime) },
                    label = { Text(text="종료날짜", fontSize = 11.sp) },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(20.dp)
                        .weight(1f),
                    shape= RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = endTime,
                    onValueChange = { endTime = it
                        homeViewModel.updateEndTime(endTime) },
                    label = { Text(text="종료시간", fontSize = 11.sp) },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(20.dp)
                        .weight(1f),
                    shape= RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = name,
                onValueChange = { name = it
                                homeViewModel.updateName(name) },
//                label = { Text("제목", fontSize = 10.sp) },
                modifier = Modifier
                    .padding(8.dp)
                    .height(20.dp)
                    .fillMaxWidth(),
                shape= RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                )
            )
//            MyTextField( value=name,
//                onValueChange = { name = it },
//                placeholder = "제목")

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = place,
                onValueChange = { place = it
                    homeViewModel.updatePlace(place) },
                label = { Text("장소", fontSize = 11.sp) },
                modifier = Modifier
                    .padding(8.dp)
                    .height(20.dp)
                    .fillMaxWidth(),
                shape= RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                value = info,
                onValueChange = { info = it
                    homeViewModel.updateInfo(info) },
                label = { Text("정보", fontSize = 11.sp) },
                modifier = Modifier
                    .padding(8.dp)
                    .height(20.dp)
                    .fillMaxWidth(),
                shape= RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                )
            )

            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.End){
                Button(modifier=Modifier.padding(5.dp),
                    onClick = { homeViewModel.createTogglePopup() }
                    , shape= RoundedCornerShape(10.dp)
                    , colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor=Color.Black
                    )
                ) {
                    Text("취소")
                    
                }

                val start=startDate+"T"+startTime+":00"
                val end=endDate+"T"+endTime+":00"
                Button(modifier=Modifier.padding(5.dp),
                    onClick = {
Log.d("눌렀다잉",start)
                        Log.d("e눌렀다잉",end)

                        homeViewModel.createEvent(
                        EventRequest( name, start ,end, info,place )) }
                    , shape= RoundedCornerShape(10.dp)
                    ,colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor=Color.White
                )
                ) {
                    Text("저장")
                }
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventPopup(onDismiss: () -> Unit, homeViewModel: HomeViewModel = viewModel(), calendarViewModel: CalendarViewModel = viewModel()) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    val eventInfo = homeUiState.eventInfo
    val context= LocalContext.current
    // 팝업 콘텐츠를 이곳에 추가
    Box(
        modifier = Modifier
            .clickable(onClick = onDismiss)
            .padding(vertical = 150.dp, horizontal = 30.dp)
            .fillMaxSize()
            , // 팝업 외부를 클릭하면 닫히도록 함
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .clickable {}
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10)
            )
            .padding(top = 15.dp, bottom = 45.dp, start = 45.dp, end = 45.dp)


            .align(Alignment.Center)

            , verticalArrangement = Arrangement.Center,) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), horizontalArrangement = Arrangement.End) {
            Text(text = "\uD83D\uDDD1️" , fontSize = 23.sp, modifier = Modifier
                .padding(horizontal = 6.dp)
                .clickable(onClick = {
                    homeViewModel.deleteEvent(eventInfo.eventId)
                    onDismiss()
                    Toast
                        .makeText(
                            context,
                            "${eventInfo.eventName} 일정이 삭제되었습니다.",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }) )

            Text(text = "✒️" , fontSize = 23.sp, modifier = Modifier
                .padding(horizontal = 6.dp)
                .clickable(

                    onClick =
                    {
//                        homeViewModel.createTogglePopup()
                        onDismiss
                    }) )

        Text(text = "✖️" , fontSize = 21.sp, modifier = Modifier
            .padding(start = 5.dp)
            .clickable(onClick = onDismiss) )
            }
            Text(text = "${eventInfo.eventName}", fontSize = 27.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "⏱️️ ${TimeModifier(eventInfo.eventStartDate)} ~ ${TimeModifier(eventInfo.eventStartDate)}",
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        emojiSupportMatch = EmojiSupportMatch.None
                    )/* ... */
                )
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)    )



            Canvas(modifier = Modifier.fillMaxWidth() , onDraw = {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(680f, 0f),
                    strokeWidth = 5f  )
            }

            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)    )

            if (eventInfo.eventPlace != "") {
                Text(
                    text = "\uD83D\uDEA9 ${eventInfo.eventPlace}",
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            emojiSupportMatch = EmojiSupportMatch.None
                        )/* ... */
                    )
                , fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.size(15.dp))

            if (eventInfo.eventInfo != ""){
            Column(modifier= Modifier
                .background(Color(0xFFEDF9FA), RoundedCornerShape(10.dp))) {
                Text("️${eventInfo.eventInfo}", fontSize = 20.sp)
            }
            }

        }
    }
}


@Composable
fun MyTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {

    BasicTextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color.White,
                        shape = RoundedCornerShape(size = 3.dp))
                    .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 3.dp))
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "${placeholder}:",
                        fontSize = 10.sp,
                        color = Color.Gray,
                    )
                }
                innerTextField()



            }
        },
    )


//    TextField(
//        value = string,
//        onValueChange = {onValueChange(it)},
//        label = { Text("${placeholder}") },
//        placeholder = { Text("${placeholder}") }
//    )


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen()
}