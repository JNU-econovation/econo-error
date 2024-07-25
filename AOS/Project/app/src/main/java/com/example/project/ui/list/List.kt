package com.example.project.ui.list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.entity.Event
import com.example.project.ui.home.HomeViewModel
import com.example.project.ui.calendar.CalendarViewModel
import com.example.project.ui.home.DateComparer
import com.example.project.ui.home.timeModifier
import com.example.project.ui.navigation.NavItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun List(calendarViewModel: CalendarViewModel = viewModel(), listViewModel: ListViewModel = viewModel(), homeViewModel: HomeViewModel= viewModel(), navController: NavHostController = rememberNavController()) {
    val listUiState by listViewModel.uiState.collectAsState()
    val calendarUiState by calendarViewModel.uiState.collectAsState()
    val events = listUiState.events
    val day = calendarUiState.day
//    val isPopupShown = listUiState.popupState == ListScreenState.PopupShown

    LaunchedEffect(Unit) {
        Log.d("홈", "시작")
        listViewModel.renderMonthEventList()
        Log.d("홈", "나옴")
    }


    Log.d("이벤트", "${day}")

    Column {
        Text(
            "${day} 일정", fontWeight = FontWeight.Bold,
            color = Color(0xFFff9999),
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)

        )



        LazyColumn(events = events, day = day)
    }
//    if (isPopupShown) {
//        EventPopup(onDismiss = {
//            listViewModel.togglePopup()
//        }, listViewModel = listViewModel)
//    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LazyColumn(events: List<Event>, day:String) {
    var count =0

    LazyColumn {

        items(events) { item ->
            Log.d("자른 날짜", "${item.eventStartDate.slice(0..9)}")
            Log.d("자른 day", "${day}")
            Log.d("같은지", "${item.eventStartDate.slice(0..9) == day}")
            when {
                DateComparer(day, item.eventStartDate, item.eventEndDate)
                -> ListContent(event = item)

                else -> {
                    count++
                    Log.d("카운트", "${count}")
                }
            }
            if (count == events.size) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(text = "오늘은\n일정이 없습니다.",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(30.dp),
                    fontStyle = FontStyle.Italic,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

    }


    // day가 변경될 때마다 호출되는 함수
    LaunchedEffect(day) {
        // day가 변경될 때마다 LazyColumn을 다시 그리도록 함
        // 이때, LazyColumn을 다시 그리는 로직을 작성
    }
}

@Composable
fun ListContent(event: Event, listViewModel: ListViewModel= viewModel(), homeViewModel: HomeViewModel= viewModel()) {

        Row(modifier = Modifier
            .clickable {
                try {
                    homeViewModel.renderEventInfo(event.eventId)
                    homeViewModel.eventTogglePopup()
                } catch (e: Exception) {
                    Log.d("클릭", "${e}")
                }

                Log.d("클릭", "${event.eventId}")
            }
            .height(70.dp)
            .padding(horizontal = 15.dp, vertical = 5.dp))
        {
            Canvas(modifier = Modifier
                .width(5.dp)
                .fillMaxHeight(), onDraw =
            {
                drawRect(color = Color(0xFFff9999))
            }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxHeight()
                    .background(
                        color = Color.White, shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Text(text = "${event.eventName}", fontSize = 19.sp)
                Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "⏳ ${timeModifier(event.eventStartDate)} ~ ${timeModifier(event.eventEndDate)}", fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.End
                    )
//                    Text(
//                        text = "⌛ ", fontSize = 13.sp,
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.End
//                    )

        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun EventPopup(onDismiss: () -> Unit, listViewModel: ListViewModel) {
//    val listUiState = listViewModel.uiState.collectAsState()
//
//    // 팝업 콘텐츠를 이곳에 추가
//    Box(
//        modifier = Modifier
//            .clickable(onClick = onDismiss)
//            .padding(16.dp)
//            .fillMaxWidth()
//            .wrapContentHeight(), // 팝업 외부를 클릭하면 닫히도록 함
//        contentAlignment = Alignment.Center
//    ) {
//        Column(modifier = Modifier
//            .clickable {}
//            .background(
//                color = Color(0x96ff9999),
//                shape = RoundedCornerShape(10)
//            )
//            , verticalArrangement = Arrangement.Center,) {
//            // 제목 입력란
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//            ) {
//                Row(modifier = Modifier
//                    .clickable {
//                        try {
////                            listViewModel.renderEventInfo(event.eventId)
//                            listViewModel.togglePopup()
//                        } catch (e: Exception) {
//                            Log.d("클릭", "${e}")
//                        }
//
////                        Log.d("클릭", "${event.eventId}")
//                    }
//                    .height(70.dp)
//                    .padding(5.dp)) {
//                    Canvas(modifier = Modifier
//                        .width(8.dp)
//                        .fillMaxHeight(), onDraw =
//                    {
//                        drawRect(color = Color(0xFFff9999))
//                    }
//                    )
//                    Column(
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .background(
//                                color = Color.White, shape = RoundedCornerShape(10.dp)
//                            )
//                    ) {
////                        Text(text = "${event.eventName}", fontSize = 20.sp)
////                        Text(
////                            text = "${event.eventStartDate} ~ ${event.eventEndDate}", fontSize = 14.sp,
////                            modifier = Modifier.fillMaxWidth(),
////                            textAlign = TextAlign.End
////                        )
//                    }
//                }
//            }
//
//
//        }
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun ListPreview() {
    List( )
}