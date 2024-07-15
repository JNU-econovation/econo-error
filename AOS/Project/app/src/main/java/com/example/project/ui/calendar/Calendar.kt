package com.example.project.ui.calendar


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.ui.list.ListViewModel
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(calendarViewModel: CalendarViewModel = viewModel(), selectionMode: DaySelectionMode, listViewModel: ListViewModel = viewModel()) {
    val calendarUiState by calendarViewModel.uiState.collectAsState()
    val listUiState by listViewModel.uiState.collectAsState()

    val instant = Clock.System.now()
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val today = LocalDate(localDateTime.year, localDateTime.monthNumber, localDateTime.dayOfMonth)
    val month = localDateTime.monthNumber
     Box(modifier = Modifier) {
        Kalendar(currentDay = today , kalendarType = KalendarType.Firey,
        modifier = Modifier,
        showLabel = true,
        events = KalendarEvents(listUiState.calendarEvent),
        kalendarHeaderTextKonfig = KalendarTextKonfig(Color.Black,20.sp),
        kalendarColors =
        KalendarColors(
            color = List(
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),
                KalendarColor(backgroundColor = Color.White, dayBackgroundColor = Color(0xFFff9999), headerTextColor = Color(0xFFff9999)),


            )
        ),
        kalendarDayKonfig = KalendarDayKonfig(
            size = 56.dp,
            textSize = 16.sp,
            textColor = Color.Black,
            selectedTextColor = Color.White,
        ),
        daySelectionMode = selectionMode,
        dayContent = null,
        headerContent = null,
        onDayClick = {
                selectedDay, events ->
            if (selectionMode == DaySelectionMode.Single) {
                selectedDay?.let {
                    calendarViewModel.updateDay(selectedDay.toString())
                    Log.d("선택한 날짜", selectedDay.toString())
                    Log.d("day", calendarUiState.day)
                }
            } else {
                // 선택 모드가 single이 아닌 경우에 대한 로그 출력
                Log.d("선택한 날짜", "Day selection mode is not single")
            }
        },
        onRangeSelected = { selectedRange, events ->
            if (selectionMode == DaySelectionMode.Range) {
                selectedRange?.let {
                    val range = CalendarUiState.Range(selectedRange.start.toString(), selectedRange.end.toString())
                    calendarViewModel.updateRange(range = range)
                    Log.d("선택한 range", selectedRange.toString())
                    Log.d("ddd", range.start)
                    Log.d("dd", range.end)
                }
            } else {
                // 선택 모드가 single이 아닌 경우에 대한 로그 출력
                Log.d("선택한 날짜", "Day selection mode is not range")
            }
        },
        onErrorRangeSelected = { error ->
            // Handle error
        })

    }
}

fun List(
    size: KalendarColor,
    init: KalendarColor,
    kalendarColor: KalendarColor,
    kalendarColor1: KalendarColor,
    kalendarColor2: KalendarColor,
    kalendarColor3: KalendarColor,
    kalendarColor4: KalendarColor,
    kalendarColor5: KalendarColor,
    kalendarColor6: KalendarColor,
    kalendarColor7: KalendarColor,
    kalendarColor8: KalendarColor,
    kalendarColor9: KalendarColor
): List<KalendarColor> {
    return listOf(
        size,
        init,
        kalendarColor,
        kalendarColor1,
        kalendarColor2,
        kalendarColor3,
        kalendarColor4,
        kalendarColor5,
        kalendarColor6,
        kalendarColor7,
        kalendarColor8,
        kalendarColor9
    )

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun CalendarScreenPreview() {
    Calendar(selectionMode = DaySelectionMode.Single)
}