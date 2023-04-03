package com.coditas.uimodule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coditas.uimodule.R
import com.coditas.uimodule.ui.themes.*

@Composable
fun CoditasPrimaryButtonView(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClickListener: (() -> Unit),
    isEnabled: Boolean = true,
    leadingIconId: Int? = null,
    trailingIconId: Int? = null
) {
    val isEnable by remember { mutableStateOf(isEnabled) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val startDp = when {
        leadingIconId == null && trailingIconId == null -> 30.dp
        leadingIconId != null -> 0.dp
        else -> 20.dp
    }

    val endDp = when {
        leadingIconId == null && trailingIconId == null -> 30.dp
        trailingIconId != null -> 0.dp
        else -> 20.dp
    }



    Card(
        modifier = modifier
            .background(color = Color.Transparent)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = { if (isEnable) onClickListener.invoke() })
            .clip(shape = RoundedCornerShape(4.dp)),
        backgroundColor = if (isEnable) {
            if (isPressed) Blue700 else PrimaryBlue
        } else Gray100
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
        ) {
            if (leadingIconId != null) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(start = 20.dp, end = 12.dp),
                    painter = painterResource(id = leadingIconId),
                    contentDescription = null,
                    tint = if (isEnable) Color.White else Gray400
                )
            }
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = startDp, end = endDp),
                style = CoditasButton,
                color = if (isEnable) Color.White else Gray400,
                text = buttonText
            )
            if (trailingIconId != null) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(start = 20.dp, end = 12.dp),
                    painter = painterResource(id = trailingIconId),
                    contentDescription = null,
                    tint = if (isEnable) Color.White else Gray400
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CoditasPrimaryButtonViewPreview() {
    CoditasPrimaryButtonView(
        buttonText = "Button",
        onClickListener = { },
        isEnabled = true
    )
}

@Composable
@Preview(showBackground = true)
fun CoditasPrimaryButtonViewDisabledPreview() {
    CoditasPrimaryButtonView(
        buttonText = "Button",
        onClickListener = { },
        isEnabled = false,
        trailingIconId = R.drawable.ic_download_disabled
    )
}

@Composable
@Preview(showBackground = true)
fun CoditasPrimaryButtonViewLeadingIconOnlyPreview() {
    CoditasPrimaryButtonView(
        buttonText = "Button",
        onClickListener = { },
        isEnabled = true,
        leadingIconId = R.drawable.ic_download
    )
}

@Composable
@Preview(showBackground = true)
fun CoditasPrimaryButtonViewTrailingIconOnlyPreview() {
    CoditasPrimaryButtonView(
        buttonText = "Button",
        onClickListener = { },
        isEnabled = true,
        trailingIconId = R.drawable.ic_download
    )
}
