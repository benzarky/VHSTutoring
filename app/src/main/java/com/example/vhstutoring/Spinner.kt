package com.example.vhstutoring

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner(
    elements: List<String>,
    label: String
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(elements[0])
    }

    ExposedDropdownMenuBox(
        expanded = expandedState,
        onExpandedChange = {
            expandedState = !expandedState
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(text = label)
            }
        )

        ExposedDropdownMenu(
            expanded = expandedState,
            onDismissRequest = {
                expandedState = false
            }
        ) {
            elements.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        selectedOption = it
                        expandedState = false
                    }
                )
            }
        }
    }
}