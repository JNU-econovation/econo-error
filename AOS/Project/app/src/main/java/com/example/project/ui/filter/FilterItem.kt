package com.example.project.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterItem(filter: filterInfo) {
    var isSelected by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .clickable { isSelected = !isSelected }
            .background(if (isSelected) Color(android.graphics.Color.parseColor("#80${filter.filterColor.substring(1)}")) else Color.LightGray, shape = RoundedCornerShape(50.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


            Text(filter.filterName)
        Spacer(modifier = Modifier.width(8.dp))
        if (filter.filterColor.isEmpty() || filter.filterColor[0] != '#') {
            filter.filterColor= "#"

        }
        Surface(color = if (isSelected) Color(android.graphics.Color.parseColor(filter.filterColor)) else Color.Gray,
            modifier = Modifier
                .size(16.dp)
                , shape = RoundedCornerShape(50.dp)
        ){
            Text(text = "X", fontSize = 12.sp, color = Color.White, textAlign = TextAlign.Center)
        }

    }
}


