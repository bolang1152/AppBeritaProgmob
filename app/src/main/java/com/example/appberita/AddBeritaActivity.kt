package com.example.appberita

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import coil.load
import com.example.appberita.databinding.ActivityAddBeritaBinding

class AddBeritaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani jarak atas (Status Bar) agar judul tidak terlalu mepet ke atas
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupDropdownKategori()
        setupPhotoPreview()
        setupActions()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupDropdownKategori() {
        val categories = arrayOf("Teknologi", "Ekonomi", "Olahraga", "Bisnis", "Hiburan")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        binding.actvKategori.setAdapter(adapter)
    }

    private fun setupPhotoPreview() {
        binding.etFotoUrl.addTextChangedListener { text ->
            val url = text.toString().trim()
            if (url.isNotEmpty()) {
                binding.ivPreview.load(url) {
                    crossfade(true)
                    placeholder(R.drawable.ic_android_news)
                    error(R.drawable.ic_android_news)
                }
            } else {
                binding.ivPreview.setImageResource(R.drawable.ic_android_news)
            }
        }
    }

    private fun setupActions() {
        binding.btnSimpan.setOnClickListener {
            val fotoUrl = binding.etFotoUrl.text.toString()
            val judul = binding.etJudul.text.toString()
            val kategori = binding.actvKategori.text.toString()
            val deskripsi = binding.etDeskripsi.text.toString()
            val isi = binding.etIsi.text.toString()

            if (judul.isEmpty() || kategori.isEmpty() || deskripsi.isEmpty() || isi.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            } else {
                val finalFotoUrl = if (fotoUrl.isEmpty()) "https://images.unsplash.com/photo-1504711432869-5d39a110fdd7?w=800" else fotoUrl
                
                val newBerita = Berita(
                    id = (System.currentTimeMillis() / 1000).toInt(),
                    foto = finalFotoUrl,
                    judul = judul,
                    deskripsi = deskripsi,
                    kategori = kategori,
                    waktu = "Baru saja",
                    views = "0",
                    isiBerita = isi,
                    penulis = "Admin"
                )

                val resultIntent = Intent()
                resultIntent.putExtra("NEW_BERITA", newBerita)
                setResult(RESULT_OK, resultIntent)
                
                Toast.makeText(this, "Berita berhasil diterbitkan!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
