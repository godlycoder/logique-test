package com.example.logique_test.core.uikit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.logique_test.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardUser(
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {

    val image = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )
    Card(
        modifier = modifier.aspectRatio(6f / 7f),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
@Preview("Card Preview")
fun NewsCardPreview() {
    CardUser(
        url = "https://www.google.com",
        title = "My News Story",
        description = "This is a description of my news story."
    )
}