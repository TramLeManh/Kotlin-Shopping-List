package com.example.listadder.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class shoppingItem(val name: String, val amount: Double) {
    companion object {
        @Composable
        fun Template(shoppingItem: shoppingItem, edit: () -> Unit, delete: () -> Unit) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
                        shape = RoundedCornerShape(20)
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Item: ${shoppingItem.name}")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Amount: ${shoppingItem.amount}")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Row {
                        IconButton(onClick = { edit() }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                        IconButton(onClick = { delete() }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Edit")
                        }
                    }
                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val s: shoppingItem = shoppingItem("abc", 123.0)
    shoppingItem.Template(s, {}, {});
}

