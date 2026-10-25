package org.example.project.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Pemilik: Hafidz. Tanda tangan DIBEKUKAN karena dipanggil dari App.kt (Syahrul).
 *
 * Aturan:
 * - Baca state: val state by vm.uiState.collectAsState()
 * - Tombol Simpan: enabled = state.bisaSimpan, onClick = vm::simpan
 * - Saat state.tersimpan menjadi true: vm.reset() lalu onSelesai()  (LaunchedEffect)
 * - Tombol Batal: vm.reset() lalu onBatal()
 * - Tanpa aturan bisnis di sini, hanya tampilan dan meneruskan event.
 */
@Composable
fun FormPeminjamanScreen(
    onSelesai: () -> Unit,
    onBatal: () -> Unit,
    vm: FormPeminjamanViewModel = viewModel { FormPeminjamanViewModel() }
) {
    // TODO Hafidz
    Text("TODO Hafidz: FormPeminjamanScreen")
}
