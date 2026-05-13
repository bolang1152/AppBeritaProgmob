package com.example.appberita

import java.io.Serializable

data class Berita(
    val id: Int,
    val foto: String, // Menggunakan String untuk URL gambar
    val judul: String,
    val deskripsi: String,
    val kategori: String,
    val waktu: String,
    val views: String,
    val isiBerita: String = "",
    val kutipan: String = "", // Tambahkan field untuk kutipan
    val penulis: String = "Rangga Dinatha",
    val fotoPenulis: String = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=200&auto=format&fit=crop", // Menggunakan String untuk URL
    var isFavorite: Boolean = false,
    val isTrending: Boolean = false
) : Serializable