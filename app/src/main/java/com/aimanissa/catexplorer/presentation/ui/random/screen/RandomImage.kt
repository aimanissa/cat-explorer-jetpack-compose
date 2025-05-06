package com.aimanissa.catexplorer.presentation.ui.random.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.aimanissa.catexplorer.presentation.ui.random.model.BreedHeader
import com.aimanissa.catexplorer.presentation.ui.random.toBreedSections

@Composable
fun RandomImage(
    state: RandomImageState.Success,
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        var isImageVisible by remember { mutableStateOf(true) }
        if (isImageVisible) {
            AsyncImage(
                modifier = Modifier
                    .clickable(onClick = onImageClicked)
                    .fillMaxWidth()
                    .height(300.dp),
                onState = { state ->
                    if (state is AsyncImagePainter.State.Error) {
                        isImageVisible = false
                    }
                },
                model = state.catImage.url,
                contentDescription = "cat image",
                contentScale = ContentScale.FillBounds
            )

            state.catImage.breed?.let { breed ->
                val breedSections = breed.toBreedSections()

                LazyColumn(modifier = Modifier.fillMaxWidth().padding(18.dp)) {
                    items(breedSections) { section ->
                        val header = section.header
                        Text(text = header.value, style = TextStyle(fontSize = 16.sp))

                        if (header == BreedHeader.Wikipedia) {
                            val uriHandler = LocalUriHandler.current

                            val annotatedString = buildAnnotatedString {
                                append("Read more here ")
                                val link = LinkAnnotation.Url(
                                    section.text,
                                    TextLinkStyles(SpanStyle(color = Color.Blue))
                                ) { link ->
                                    val url = (link as LinkAnnotation.Url).url
                                    uriHandler.openUri(url)
                                }
                                withLink(link) { append(header.value) }
                            }
                            Text(text = annotatedString)
                        } else {
                            Text(text = section.text)
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }

        }
    }
}