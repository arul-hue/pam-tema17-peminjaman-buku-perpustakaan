package org.example.project.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Pemilik: Hafidz. Composable TANPA state: menerima nilai, melapor lewat onValueChange.
 * Tanda tangan DIBEKUKAN. Tampilan boleh disempurnakan (error, keyboard, dll).
 */
@Composable
fun KolomTeks(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
    )
}
