package com.example.project.ui.home

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.project.MyApplication
import com.example.project.R
import com.example.project.data.remote.EventRequest
import com.example.project.ui.calendar.Calendar
import com.example.project.ui.calendar.CalendarViewModel
import com.example.project.ui.filter.FilterCategory
import com.example.project.ui.filter.FilterItem
import com.example.project.ui.filter.FilterViewModel
import com.example.project.ui.filter.filterInfo
import com.example.project.ui.list.List
import com.example.project.ui.navigation.NavItem
import com.himanshoe.kalendar.ui.firey.DaySelectionMode

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel= viewModel(),
               navController: NavHostController) {
val filterViewModel: FilterViewModel = viewModel()
    val homeUiState by homeViewModel.uiState.collectAsState()
    val filterUiState by filterViewModel.uiState.collectAsState()
    val isCreatePopupShown = homeUiState.createPopupState == CreateState.PopupShown
    val isEventPopupShown = homeUiState.eventPopupState == EventState.PopupShown
    val isFilterShown = homeUiState.filterState == FilterState.BSShown


    val bottomSheetState = rememberModalBottomSheetState()

    val scope= rememberCoroutineScope()

    val filters = filterUiState.filterInfos


    LaunchedEffect(Unit){
        if(false) {
            navController.navigate(NavItem.Login.screenRoute)
        }
        filterViewModel.renderFilter();
    }

    Box(modifier=Modifier.background(Color.White)) {
        Column(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Error", fontWeight = FontWeight.Bold,
                    color = Color(0xFFff9999),
                    fontSize = 32.sp,
                )

                loginBar(navController)


            }
            Calendar(selectionMode = DaySelectionMode.Single)


                List()


        }
//        if (isFilterShown) {
//            FilterBottomSheet(
//                filters = filters,
//                onDismiss = {
//                    homeViewModel.popFilter()
//                }
//            )
//        }
        if (isEventPopupShown || isCreatePopupShown ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)

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

        if (isFilterShown) {
            FilterBottomSheet(
                filters = filters, onDismiss = {
                    homeViewModel.popFilter()
                }
            )
        }

        var expanded by remember { mutableStateOf(false) }

        ExtendedFloatingActionButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .size(56.dp),
            shape= CircleShape,
            containerColor = Color(0xFFff9999),
            contentColor = Color.White
        ) {
            if (expanded) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }


        if (expanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 70.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                ) {

                    FloatingActionButton(
                        shape= CircleShape,
                        containerColor = Color(0xFFff9999),
                        contentColor = Color.White,
                        onClick = {
                            homeViewModel.popFilter()
                            filterViewModel.renderFilter();


                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(45.dp)
                            .align(Alignment.End),
                    ) {
                        Text(text="필터", fontWeight = FontWeight.Bold)
                    }

                    FloatingActionButton(
                        shape= CircleShape,
                        contentColor = Color(0xFFff9999),
                        containerColor = Color.White,
                        onClick = {
                            homeViewModel.createTogglePopup()
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(45.dp)
                            .align(Alignment.End),
                    ) {
                        Text(text="일정\n생성", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(filters: List<filterInfo>, onDismiss: () -> Unit, homeViewModel: HomeViewModel = viewModel() ) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        shape = RoundedCornerShape(10.dp),
        contentColor = Color.White,
        dragHandle = null,
        tonalElevation = 30.dp,
        scrimColor = Color(0x66000000)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text("일정 필터", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            FilterCategory.values().forEach { category ->

                Row() {


                    Text(
                        when (category.name) {
                            "PUBLIC" -> "에코노 캘린더"
                            "GROUP" -> "그룹 캘린더"
                            "PRIVATE" -> "개인 캘린더"
                            else -> "Unknown"
                        }, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(20.dp)
                            .clickable {
                                // 필터 생성
                            }
                            .background(Color(0xFFff9999), shape = RoundedCornerShape(50.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {


                        Text("+")


                    }
                }



                LazyRow(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(filters.filter { it.filterCategory== category.name }) { filter ->
                        FilterItem(filter)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun loginBar(navController:NavHostController){
    if(MyApplication.prefs.getAToken()=="") {
        Button(
            onClick = {
                navController.navigate(NavItem.Login.screenRoute)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFff9999),
                contentColor = Color.White
            )
        ) {
            Text("로그인")
        }
    }



    else{
//        Image(
//            painter = painterResource(id = R.drawable.profile),
//            contentDescription = "프로필 버튼",
//            contentScale = ContentScale.FillHeight,
//            modifier = Modifier
//                .height(50.dp)
//                .clickable {
////                    navController.navigate(NavItem.Profile.screenRoute)
//                }
//        )
        val painter = rememberAsyncImagePainter("https://econo-calendar.com/seed0141.png")

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(50.dp), // 원하는 Modifier를 설정하세요
            contentScale = ContentScale.FillHeight // 이미지 크기 조절 방식 설정
        )

    }

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePopup(onDismiss: () -> Unit, homeViewModel: HomeViewModel = viewModel(), calendarViewModel: CalendarViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("") }
    var startHour by remember { mutableStateOf("") }
    var startMinute by remember { mutableStateOf("") }
    var endHour by remember { mutableStateOf("") }
    var endMinute by remember { mutableStateOf("") }
    val calendarUiState by calendarViewModel.uiState.collectAsState()
    var startDate = calendarUiState.range.start
    var endDate = calendarUiState.range.end
    var scope by remember { mutableStateOf("PUBLIC") }
    // 팝업 콘텐츠를 이곳에 추가
    Box(
        modifier = Modifier
            .clickable(onClick = onDismiss)

            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            , // 팝업 외부를 클릭하면 닫히도록 함
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .clickable {}
            .border(
                width = 1.dp,
                color = Color(0xFFff9999),
                shape = RoundedCornerShape(10)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10)
            )


        , verticalArrangement = Arrangement.Center,) {
            // 제목 입력란

            // 캘린더
            Box(modifier = Modifier
                ) {
                Calendar(selectionMode = DaySelectionMode.Range)
            }
            Row(modifier = Modifier
                .padding(horizontal = 50.dp)
                .wrapContentHeight()
                .fillMaxWidth()) {
                Button(
                    onClick = { scope = "PUBLIC"; homeViewModel.updateScope(scope) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (scope == "PUBLIC") Color(0xFFff9999) else Color.Gray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("에코노", fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { scope = "GROUP"; homeViewModel.updateScope(scope) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (scope == "GROUP") Color(0xFFff9999) else Color.Gray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("그룹", fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { scope = "PRIVATE"; homeViewModel.updateScope(scope) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (scope == "PRIVATE") Color(0xFFff9999) else Color.Gray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("개인", fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier= Modifier
                .height(30.dp)) {
//                TextField(
//                    value = startDate,
//                    onValueChange = { startDate = it
//                        homeViewModel.updateStartTime(startDate) },
//                    label = { Text(text="시작날짜", fontSize = 11.sp) },
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .height(20.dp)
//                        .weight(1f),
//                    shape= RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White,
//                    )
//                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )
                Box(modifier=Modifier.weight(1f)) {
                    MyTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        placeholder = "시작날짜",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(modifier=Modifier.weight(1f)) {
                    TimeInputFields(
                        selectedHour = startHour,
                        selectedMinute = startMinute,
                        onHourChange = { newHour -> startHour = newHour },
                        onMinuteChange = { newMinute -> startMinute = newMinute }
                    )
//                    TimeDropdownMenus( selectedHour= startHour, selectedMinute = startMinute,
//                        onHourSelected = { startHour = it },
//                        onMinuteSelected = { startMinute = it }
//                    )
                }

//
//
//                TextField(
//                    value = startTime,
//                    onValueChange = { startTime = it
//                        homeViewModel.updateEndTime(startTime) },
//                    label = { Text(text="시작시간", fontSize = 11.sp) },
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .height(20.dp)
//                        .weight(1f),
//                    shape= RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White,
//                    )
//                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )
            }



            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier= Modifier
                .height(30.dp)){
//                TextField(
//                    value = endDate,
//                    onValueChange = { endDate = it
//                        homeViewModel.updateStartTime(endDate) },
//                    label = { Text(text="종료날짜", fontSize = 11.sp) },
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .height(20.dp)
//                        .weight(1f),
//                    shape= RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White,
//                    )
//                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )
                Box(modifier=Modifier.weight(1f)) {
                    MyTextField(
                        value = endDate,
                        onValueChange = { endDate = it },
                        placeholder = "종료날짜",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(modifier=Modifier.weight(1f)) {


//                        MyTextField(
//                            value = if (endHour.toInt()>24 || endHour.toInt()<0) "00" else endHour,
//                            onValueChange = { endHour = it },
//                            placeholder = "종료시간",
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                        )
                        TimeInputFields(
                            selectedHour = endHour,
                            selectedMinute = endMinute,
                            onHourChange = { newHour -> endHour = newHour },
                            onMinuteChange = { newMinute -> endMinute = newMinute }
                        )

//                        MyTextField(
//                            value = if (endMinute.toInt()>60 || endMinute.toInt()<0) "00" else endMinute,
//                            onValueChange = { endMinute = it },
//                            placeholder = "종료시간",
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                        )
                    }

//                TextField(
//                    value = endTime,
//                    onValueChange = { endTime = it
//                        homeViewModel.updateEndTime(endTime) },
//                    label = { Text(text="종료시간", fontSize = 11.sp) },
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
//                        .height(20.dp)
//                        .weight(1f),
//                    shape= RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White,
//                    )
//                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )
            }
            Spacer(modifier = Modifier.height(8.dp))

//            TextField(
//                value = name,
//                onValueChange = { name = it
//                                homeViewModel.updateName(name) },
//                label = { Text("제목", fontSize = 10.sp) },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .height(20.dp)
//                    .fillMaxWidth(),
//                shape= RoundedCornerShape(10.dp),
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                )
//            )

            MyTextField( value=name,
                onValueChange = { name = it },
                placeholder = "제목", keyboardOptions = KeyboardOptions.Default)

            Spacer(modifier = Modifier.height(8.dp))

            MyTextField( value=place,
                onValueChange = { place = it },
                placeholder = "장소", keyboardOptions = KeyboardOptions.Default)
//            TextField(
//                value = place,
//                onValueChange = { place = it
//                    homeViewModel.updatePlace(place) },
//                label = { Text("장소", fontSize = 11.sp) },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .height(20.dp)
//                    .fillMaxWidth(),
//                shape= RoundedCornerShape(10.dp),
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                )
//            )
            Spacer(modifier = Modifier.height(8.dp))
            MyTextField( value=info,
                onValueChange = { info = it },
                placeholder = "정보", keyboardOptions = KeyboardOptions.Default)
//            TextField(
//                value = info,
//                onValueChange = { info = it
//                    homeViewModel.updateInfo(info) },
//                label = { Text("정보", fontSize = 11.sp) },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .height(20.dp)
//                    .fillMaxWidth(),
//                shape= RoundedCornerShape(10.dp),
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                )
//            )

            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.Center){
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

                val start=startDate+"T"+"${startHour}" +":"+ "${ startMinute }" +":00"
                val end=endDate+"T"+"${endHour}"+ ":"+"${endMinute}"+":00"
                Button(modifier=Modifier.padding(5.dp),
                    onClick = {
Log.d("눌렀다잉",start)
                        Log.d("e눌렀다잉",end)

                        homeViewModel.createEvent(
                        EventRequest( name, start ,end, info,place, scope, EventRequest.filterId(12) )) }
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
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .fillMaxSize()
            , // 팝업 외부를 클릭하면 닫히도록 함
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .clickable {}
            .width(300.dp)
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
                text = "⏱️️ ${timeModifier(eventInfo.eventStartDate)} ~ ${timeModifier(eventInfo.eventStartDate)}",
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
fun TimeInputFields(
    selectedHour: String,
    selectedMinute: String,
    onHourChange: (String) -> Unit,
    onMinuteChange: (String) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Box(modifier = Modifier.width(100.dp)) {
            // 시간 입력 필드
            MyTextField(
                value = selectedHour,
                onValueChange = { newValue ->
                    // 시간 입력값이 빈 문자열이거나 0~23 사이의 숫자인 경우만 허용
                    if (newValue.isEmpty() || newValue.toIntOrNull() in 0..23) {
                        onHourChange(newValue)
                    }
                },
                placeholder = "시간",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Box(modifier = Modifier.width(100.dp)) {
        // 분 입력 필드
        MyTextField(
            value = selectedMinute,
            onValueChange = { newValue ->
                // 분 입력값이 빈 문자열이거나 0~59 사이의 숫자인 경우만 허용
                if (newValue.isEmpty() || newValue.toIntOrNull() in 0..59) {
                    onMinuteChange(newValue)
                }
            },
            placeholder = "분",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }}
}


@Composable
fun MyTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, keyboardOptions: KeyboardOptions?) {

    BasicTextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(size = 3.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 3.dp)
                    )
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

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
//    HomeScreen()
}