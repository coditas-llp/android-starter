package com.coditas.uimodule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coditas.uimodule.ui.themes.*

@Composable
fun CoditasSecondaryButtonView(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClickListener: () -> Unit,
    isEnabled: Boolean = true
) {
    val isPressed by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = modifier
            .background(color = Color.Transparent)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                enabled = isEnabled,
                onClick = onClickListener
            )
            .clip(shape = RoundedCornerShape(4.dp)),
        backgroundColor = if (isEnabled) SecondaryBlue else Gray100
    ) {
        Row(modifier = Modifier.wrapContentSize()) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 30.dp),
                style = CoditasButton,
                color = if (isEnabled) {
                    if (isPressed) Blue600 else PrimaryBlue
                } else {
                    Gray400
                },
                text = buttonText
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CoditasSecondaryButtonViewPreview() {
    CoditasSecondaryButtonView(buttonText = "Button", onClickListener = {}, isEnabled = true)
}

@Composable
@Preview(showBackground = true)
fun CoditasSecondaryButtonViewPreview2() {
    CoditasSecondaryButtonView(buttonText = "Button", onClickListener = {}, isEnabled = false)
}
