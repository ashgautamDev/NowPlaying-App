package com.ashish.nowplayingapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ashish.nowplayingapp.utils.RatingBarConfig

@Composable
fun RatingBarView(averageVote: Float) {

    val rating: Float by remember {
        mutableStateOf(averageVote)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = rating.toString(),
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.width(16.dp))

        CustomRatingBar(
            value = rating,
            config = RatingBarConfig().style(RatingBarStyle.HighLighted)
        )
    }
}
