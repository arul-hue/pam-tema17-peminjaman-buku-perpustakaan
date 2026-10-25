package org.example.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.model.Peminjaman
import org.example.project.model.StatusPeminjaman
import org.example.project.model.label

@Composable
fun KartuPeminjaman(
    peminjaman: Peminjaman,
    onKlik: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onKlik() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                peminjaman.buku.judul,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text("Peminjam: ${peminjaman.peminjam}", color = Color.Gray)
            Text("Tanggal pinjam: ${peminjaman.tanggalPinjam}", color = Color.Gray)
            LabelStatus(peminjaman.status)
        }
    }
}

@Composable
fun LabelStatus(status: StatusPeminjaman) {
    val warna = when (status) {
        StatusPeminjaman.Diajukan -> Color(0xFFE6E2D6)
        is StatusPeminjaman.Dipinjam -> Color(0xFFF6EDCF)
        is StatusPeminjaman.Dikembalikan -> Color(0xFFDCEFD6)
    }
    Text(
        text = status.label(),
        fontSize = 12.sp,
        color = Color.Black,
        modifier = Modifier
            .background(warna, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}