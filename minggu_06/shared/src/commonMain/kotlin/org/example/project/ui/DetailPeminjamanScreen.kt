package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.model.Peminjaman

/**
 * Pemilik: Jundi. Layar tidak membuat NavController; tombol Kembali memanggil [onKembali].
 * ViewModel dibuat per id (key), jadi membuka kartu lain tidak memakai state kartu sebelumnya.
 */
@Composable
fun DetailPeminjamanScreen(
    id: Int,
    onKembali: () -> Unit,
    vm: DetailPeminjamanViewModel = viewModel(key = "detail-$id") { DetailPeminjamanViewModel(id) }
) {
    val state by vm.uiState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(onClick = onKembali) { Text("Kembali") }

            when (val s = state) {
                DetailUiState.Memuat -> Column(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

                is DetailUiState.Ditemukan -> IsiDetail(s.peminjaman)

                is DetailUiState.TidakDitemukan -> Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Peminjaman tidak ditemukan", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "Tidak ada peminjaman dengan ID ${s.id}. Kembali ke daftar lalu pilih kartu yang lain.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun IsiDetail(peminjaman: Peminjaman) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(peminjaman.buku.judul, style = MaterialTheme.typography.headlineMedium)

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BarisDetail("Penulis", peminjaman.buku.penulis)
                BarisDetail("Tahun terbit", peminjaman.buku.tahunTerbit.toString())
                BarisDetail("Peminjam", peminjaman.peminjam)
                BarisDetail("Tanggal pinjam", peminjaman.tanggalPinjam)
                BarisDetail(
                    "Tanggal kembali",
                    peminjaman.tanggalKembali ?: "Belum dikembalikan"
                )
            }
        }

        Text("Status", fontWeight = FontWeight.Bold)
        // label() sudah memuat info tambahan: petugas (Dipinjam) atau catatan (Dikembalikan)
        LabelStatus(peminjaman.status)
    }
}

@Composable
private fun BarisDetail(label: String, nilai: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(nilai, fontWeight = FontWeight.Medium)
    }
}
