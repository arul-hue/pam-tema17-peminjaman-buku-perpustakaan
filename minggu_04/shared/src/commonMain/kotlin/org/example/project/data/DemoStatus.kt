package org.example.project.data

import org.example.project.model.label

suspend fun demoStatus() {
    val aliran = SumberPeminjamanPalsu().pantau(1)
    println("Sebelum collect")
    aliran.collect { println(it.status.label()) }
    println("Sesudah collect")
}