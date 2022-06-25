package com.ashish.nowplayingapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ashish.nowplayingapp.ui.components.RatingBarStyle
import com.ashish.nowplayingapp.ui.components.StepSize
import com.ashish.nowplayingapp.ui.theme.accent

class RatingBarConfig {
    var size: Dp = 24.dp
        private set
    var padding: Dp = 1.dp
        private set
    var style: RatingBarStyle = RatingBarStyle.Normal
        private set
    var numStars: Int = 5
        private set
    var isIndicator: Boolean = false
        private set
    var activeColor: Color = Color.Green
        private set
    var inactiveColor: Color = accent.copy(alpha = 0.5f)
        private set
    var stepSize: StepSize = StepSize.ONE
        private set
    var hideInactiveStars: Boolean = false
        private set
    fun style(value: RatingBarStyle): RatingBarConfig =
        apply { style = value }
}