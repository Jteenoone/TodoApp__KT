package com.example.todoapp.ui.compose

import android.graphics.fonts.Font
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.utils.Background
import java.time.LocalDate


@Composable
fun AreaListDateCard(
    selectedDate: LocalDate,
    onDateSelected: (date: LocalDate) -> Unit,
    modifier: Modifier= Modifier
) {
    val today = LocalDate.now()
    val centerIndex = Int.MAX_VALUE / 2

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = centerIndex - 2
    )

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(count = Int.MAX_VALUE, key = {index-> index}) { index->
            val offset = index.toLong() - centerIndex
            val date = today.plusDays(offset)

            DateCard(
                date = date,
                isSelected = date == selectedDate,
                onClick = {
                    onDateSelected(date)
                }
            )
        }
    }

}

@Composable
fun DateCard(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listMonth= listOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    val listDay= listOf(
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",
        "Sat",
        "Sun"
    )
    Card(
        modifier = modifier.width(84.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if(!isSelected) Color.White else Color(0xFF5F33E1),
            contentColor = if(!isSelected) Color.Black else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text= listMonth[date.monthValue - 1],
                fontSize = 14.sp,
            )
            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = listDay[date.dayOfWeek.value - 1],
                fontSize = 14.sp
            )
        }
    }

}

@Preview(
    showBackground = true
)

@Composable
fun DateCardPreview(){
    Background {
        Row(modifier = Modifier.height(100.dp)) {
            DateCard(
                date = LocalDate.now(),
                isSelected = true,
                onClick = {},
                modifier = Modifier.fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(12.dp))
            DateCard(
                date = LocalDate.now(),
                isSelected = false,
                onClick = {},
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}