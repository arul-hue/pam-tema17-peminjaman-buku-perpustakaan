package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
// agar saat data berhasil disimpan, form dikosongkan lalu pindah layar
    if (state.tersimpan) {
        vm.reset()
        onSelesai()
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KolomTeks("Nama peminjam", state.peminjam, vm::ubahPeminjam)
        PemilihBuku(state.pilihanBuku, state.bukuDipilih, vm::ubahBuku)
        KolomTeks("Tanggal pinjam (yyyy-MM-dd)", state.tanggalPinjam, vm::ubahTanggal) // agar saat tombol Simpan diklik data tersimpan, dan tombolnya hanya aktif kalau form sudah lengkap
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = vm::simpan, enabled = state.bisaSimpan) {
                Text(if (state.sedangMenyimpan) "Menyimpan" else "Simpan")
            }
            TextButton(onClick = {
                vm.reset()
                onBatal()
            }) {
                Text("Batal")
            }
        }
        state.pesan?.let { Text(it) }
    }
}