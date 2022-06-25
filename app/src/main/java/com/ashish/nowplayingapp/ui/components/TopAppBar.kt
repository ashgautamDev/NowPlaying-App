package com.ashish.nowplayingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ashish.nowplayingapp.R

@Composable
fun TopAppBar(tagLineRes : Int,titleRes : Int, icon : ImageVector, onFavNavigate : ()-> Unit ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement =Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically) {


                Text(
                    text = stringResource(titleRes),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface
                )
               IconButton(onClick = {
                    onFavNavigate()
                }, modifier = Modifier.align(Alignment.Bottom)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Fav Button",
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = tagLineRes),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}