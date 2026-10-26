package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FormPeminjamanScreen(
    onSelesai: () -> Unit,
    onBatal: () -> Unit,
    vm: FormPeminjamanViewModel = viewModel { FormPeminjamanViewModel() }
) {
    val state by vm.uiState.collectAsState()

    // Pindah layar adalah efek samping, jadi tidak ditulis langsung di badan composable.
    // Blok ini jalan sekali setiap nilai tersimpan berubah.
    LaunchedEffect(state.tersimpan) {
        if (state.tersimpan) {
            vm.reset()
            onSelesai()
        }
    }

    // Scaffold menjaga isi form tidak tertimpa status bar
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding() // naik saat keyboard muncul
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Tambah Peminjaman", style = MaterialTheme.typography.headlineMedium)

            KolomTeks("Nama peminjam", state.peminjam, vm::ubahPeminjam, Modifier.fillMaxWidth())
            PemilihBuku(state.pilihanBuku, state.bukuDipilih, vm::ubahBuku, Modifier.fillMaxWidth())
            KolomTeks(
                "Tanggal pinjam (yyyy-MM-dd)",
                state.tanggalPinjam,
                vm::ubahTanggal,
                Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // Aktif hanya kalau form lengkap dan tidak sedang menyimpan
                Button(onClick = vm::simpan, enabled = state.bisaSimpan) {
                    Text(if (state.sedangMenyimpan) "Menyimpan" else "Simpan")
                }
                TextButton(
                    onClick = {
                        vm.reset()
                        onBatal()
                    },
                    enabled = !state.sedangMenyimpan
                ) {
                    Text("Batal")
                }
            }

            state.pesan?.let { Text(it) }
        }
    }
}
