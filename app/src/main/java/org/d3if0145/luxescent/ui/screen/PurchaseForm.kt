package org.d3if0145.luxescent.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.d3if0145.luxescent.model.parfum
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseForm(
    parfum: parfum,
    onSubmit: (String, String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Beli ${parfum.nama_parfum}") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it
                        nameError = if (it.isEmpty()) {
                            "Nama tidak boleh kosong"
                        } else {
                            null
                        } },
                    label = { Text("Nama") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = nameError != null,
                    supportingText = {
                        nameError?.let { error ->
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it
                        phoneError = if (it.isEmpty()) {
                            "Nomor Telepon tidak boleh kosong"
                        } else {
                            null
                        } },
                    label = { Text("Nomor Telepon") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = phoneError != null,
                    supportingText = {
                        phoneError?.let { error ->
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotEmpty() && phone.isNotEmpty()) {
                        onSubmit(name, phone)
                        onDismissRequest()
                    } else {
                        if (name.isEmpty()) {
                            nameError = "Nama tidak boleh kosong"
                        }
                        if (phone.isEmpty()) {
                            phoneError = "Nomor Telepon tidak boleh kosong"
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }
    )
}
