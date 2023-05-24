package com.coditas.uimodule.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.coditas.uimodule.ui.themes.PrimaryBlue
import com.coditas.uimodule.ui.themes.SegmentControlLabel

@Composable
fun CoditasSegmentedControlView(
    modifier: Modifier = Modifier,
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    cornerRadius: Int = 10,
    @ColorRes color: Color = PrimaryBlue,
    onItemSelection: (selectedItemIndex: String) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(defaultSelectedItemIndex) }

    Row {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                modifier = modifier
                    .wrapContentWidth()
                    .offset(x = (-index).dp)
                    .zIndex(if (selectedIndex == index) 1f else 0f),
                onClick = {
                    selectedIndex = index
                    onItemSelection(items[selectedIndex])
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = cornerRadius,
                        topEndPercent = 0,
                        bottomStartPercent = cornerRadius,
                        bottomEndPercent = 0
                    )
                    items.size - 1 -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = cornerRadius,
                        bottomStartPercent = 0,
                        bottomEndPercent = cornerRadius
                    )
                    else -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 0,
                        bottomStartPercent = 0,
                        bottomEndPercent = 0
                    )
                },
                border = BorderStroke(
                    1.dp, if (selectedIndex == index) {
                        color
                    } else {
                        color.copy(alpha = 0.75f)
                    }
                ),
                colors = if (selectedIndex == index) {
                    ButtonDefaults.outlinedButtonColors(
                        backgroundColor = color
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent)
                },
            ) {
                Text(
                    text = item,
                    style = SegmentControlLabel,
                    color = if (selectedIndex == index) {
                        Color.White
                    } else {
                        color.copy(alpha = 0.9f)
                    },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CoditasSegmentedControlViewPreview() {
    CoditasSegmentedControlView(items = listOf("Option 1", "Option 2"), onItemSelection = {})
}

@Composable
@Preview(showBackground = true)
fun CoditasSegmentedControlViewPreview2() {
    CoditasSegmentedControlView(items = listOf("Option 1", "Option 2", "Option 3"), onItemSelection = {})
}
