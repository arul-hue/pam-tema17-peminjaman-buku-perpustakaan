package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DaftarPeminjamanScreen(
    onTambah: () -> Unit,
    vm: DaftarPeminjamanViewModel = viewModel { DaftarPeminjamanViewModel() }
) {
    val daftar by vm.daftar.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onTambah) {
                Text("Tambah Peminjaman")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            // bottom 88.dp, ruang untuk FAB supaya kartu terakhir tidak tertutup
            contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Peminjaman Buku Perpustakaan",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "${daftar.size} peminjaman",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            items(daftar, key = { it.id }) { peminjaman ->
                KartuPeminjaman(peminjaman)
            }
        }
    }
}