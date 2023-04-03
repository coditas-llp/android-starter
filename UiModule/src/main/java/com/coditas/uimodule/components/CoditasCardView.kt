package com.coditas.uimodule.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coditas.uimodule.R
import com.coditas.uimodule.ui.themes.CardPrimaryLabel
import com.coditas.uimodule.ui.themes.CardSecondaryLabel
import com.coditas.uimodule.ui.themes.SegmentControlLabel

@Composable
fun CoditasCardView(
    modifier: Modifier = Modifier,
    title: String,
    subheader: String,
    description: String,
    primaryButtonText: String,
    secondaryButtonText: String,
    @DrawableRes imageResource: Int?,
    onClickPrimaryButtonListener: () -> Unit,
    onClickSecondaryButtonListener: () -> Unit
) {
    Card(
        modifier = modifier
            .widthIn(max = 302.dp)
            .wrapContentHeight(),
        backgroundColor = Color.White
    ) {
        Column(Modifier.fillMaxWidth()) {
            imageResource?.let {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = title,
                maxLines = 1,
                style = CardPrimaryLabel
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = subheader.uppercase(),
                maxLines = 1,
                style = SegmentControlLabel
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = description,
                style = CardSecondaryLabel
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CoditasSecondaryButtonView(
                    modifier = Modifier.padding(end = 16.dp),
                    buttonText = primaryButtonText,
                    onClickListener = onClickSecondaryButtonListener
                )
                CoditasPrimaryButtonView(
                    buttonText = secondaryButtonText,
                    onClickListener = onClickPrimaryButtonListener
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CoditasCardViewPreview() {
    CoditasCardView(
        title = "Title",
        subheader = "Subheader",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        primaryButtonText = "Button",
        secondaryButtonText = "Button",
        onClickPrimaryButtonListener = {},
        onClickSecondaryButtonListener = {},
        imageResource = R.drawable.ic_default_image
    )
}

@Composable
@Preview(showBackground = true)
fun CoditasCardViewPreview2() {
    CoditasCardView(
        title = "Title",
        subheader = "Subheader",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        primaryButtonText = "Button",
        secondaryButtonText = "Button",
        onClickPrimaryButtonListener = {},
        onClickSecondaryButtonListener = {},
        imageResource = null
    )
}