package com.coditas.uimodule.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coditas.uimodule.ui.themes.*


@Composable
fun CoditasTextInputLayout(
    modifier: Modifier = Modifier,
    label: String,
    tertiaryLabel: String,
    placeHolder: String,
    secondaryLabel: String,
    isEnabled:Boolean = true,
    onTextChangeListener: ((String) -> Unit)
) {

    var displayText by remember { mutableStateOf("") }
    var borderColor by remember { mutableStateOf(Gray200) }
    var borderWidth by remember { mutableStateOf(1.dp) }
    var isFocused by remember { mutableStateOf(false) }
    borderWidth = if (displayText.isEmpty() && isFocused) 2.dp else 1.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier, text = label, style = PrimaryLabel)
            Text(modifier = Modifier, text = tertiaryLabel, style = SecondaryLabel)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = borderWidth, color = borderColor, shape = RoundedCornerShape(4.dp)),
            shape = RoundedCornerShape(4.dp),
        ) {
            TextField(
                modifier = Modifier
                    .onFocusChanged {
                        if (it.isFocused) {
                            isFocused = true
                            borderColor = PrimaryBlue
                        } else {
                            isFocused = false
                            borderColor = Gray200
                            borderWidth = 1.dp
                        }
                    },
                value = displayText,
                onValueChange = {
                    displayText = it
                    onTextChangeListener(displayText)
                    borderWidth = if (displayText.isEmpty() && isFocused) 2.dp else 1.dp
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = if (isEnabled) Color.White else Gray25,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.Black
                ),
                placeholder = {
                    Text(text = placeHolder, color = Gray400, fontSize = 16.sp)
                },
                enabled = isEnabled
            )
        }
        Text(modifier = Modifier.padding(top = 4.dp), text = secondaryLabel, style = SecondaryLabel)
    }
}

@Composable
@Preview(showBackground = true)
fun TextInputLayoutPreview() {
    CoditasTextInputLayout(
        onTextChangeListener = {},
        label = "Label",
        tertiaryLabel = "Tertiary Label",
        placeHolder = "Placeholder",
        secondaryLabel = "Secondary Label",
        isEnabled = true
    )
}

@Composable
@Preview(showBackground = true)
fun TextInputLayoutPreview2() {
    CoditasTextInputLayout(
        onTextChangeListener = {},
        label = "Label",
        tertiaryLabel = "Tertiary Label",
        placeHolder = "Placeholder",
        secondaryLabel = "Secondary Label",
        isEnabled = false
    )
}