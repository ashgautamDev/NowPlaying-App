package com.ashish.nowplayingapp.ui.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ashish.nowplayingapp.R


@Composable
fun PopularityDropDown(index: Int) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Most Popular", "All NowPlaying")
    var selectedIndex by remember { mutableStateOf(index) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {

        IconButton(onClick = { expanded = true }) {
            Icon(painterResource(id = R.drawable.filter_ic), contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colors.primaryVariant
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s, color = MaterialTheme.colors.onPrimary)
                }
            }
        }
    }
}

@Composable
fun ListDropDown(index: Int) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Favorite Movies", "Now Playing")
    var selectedIndex by remember { mutableStateOf(index) }

    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {

            Text(
                text = buildString { append(items[selectedIndex]) },
                modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 2.dp),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2
            )
            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .rotate(rotationState),
                onClick = {
                    expanded = !expanded
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colors.primaryVariant
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s, color = MaterialTheme.colors.onPrimary)
                }
            }
        }
    }
}