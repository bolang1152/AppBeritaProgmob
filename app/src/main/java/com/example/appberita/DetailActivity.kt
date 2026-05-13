package com.example.appberita

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.appberita.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        val berita = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_BERITA", Berita::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_BERITA") as? Berita
        }

        berita?.let {
            setupDetailView(it)
            setupBacaJuga(it)
        }

        setupClickListeners(berita)
    }

    private fun setupDetailView(it: Berita) {
        // Menggunakan Coil untuk memuat gambar dari URL
        binding.ivDetailFoto.load(it.foto) {
            crossfade(true)
            placeholder(R.drawable.ic_android_news)
            error(R.drawable.ic_android_news)
        }
        
        binding.tvDetailKategori.text = it.kategori
        binding.tvDetailJudul.text = it.judul
        binding.tvDetailDeskripsi.text = it.deskripsi
        binding.tvIsiBerita.text = it.isiBerita
        binding.tvPenulis.text = it.penulis
        
        // Setup Kutipan/Quote
        if (it.kutipan.isNotEmpty()) {
            binding.llQuote.isVisible = true
            binding.tvQuote.text = it.kutipan
        } else {
            binding.llQuote.isVisible = false
        }
        
        // Memuat foto penulis dari URL menggunakan Coil
        binding.ivPenulis.load(it.fotoPenulis) {
            crossfade(true)
            transformations(CircleCropTransformation())
            placeholder(R.mipmap.ic_launcher_round)
            error(R.mipmap.ic_launcher_round)
        }

        binding.tvDetailMeta.text = "${it.waktu}  •  ${it.views} views"
        updateFavIcon(it.isFavorite)
    }

    private fun setupBacaJuga(currentBerita: Berita) {
        // Update URL gambar pada data dummy
        val dummyRelated = listOf(
            Berita(101, "https://images.unsplash.com/photo-1485827404703-89b55fcc595e?q=80&w=800", "5 Library AI untuk Android Dev", "AI kini hadir di mana-mana...", "Teknologi", "3 jam lalu", "890", isTrending = false),
            Berita(102, "https://images.unsplash.com/photo-1550745165-9bc0b252726f?q=80&w=800", "Masa Depan AI di Mobile", "Bagaimana AI merubah cara kita memakai HP...", "Teknologi", "1 hari lalu", "1.1K", isTrending = false)
        )

        binding.rvBacaJuga.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBacaJuga.adapter = BacaJugaAdapter(dummyRelated) { selected ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_BERITA", selected)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun setupClickListeners(berita: Berita?) {
        binding.toolbar.setNavigationOnClickListener { finish() }
        
        binding.btnFavDetail.setOnClickListener {
            berita?.let {
                it.isFavorite = !it.isFavorite
                updateFavIcon(it.isFavorite)
                val msg = if (it.isFavorite) "Ditambahkan ke favorit" else "Dihapus dari favorit"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, berita?.judul)
                putExtra(Intent.EXTRA_TEXT, "Baca berita menarik ini: ${berita?.judul}\n\n${berita?.deskripsi}")
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
        }

        binding.btnMoreDetail.setOnClickListener {
            Toast.makeText(this, "Opsi lainnya", Toast.LENGTH_SHORT).show()
        }

        binding.llAuthor.setOnClickListener {
            Toast.makeText(this, "Profil Penulis: ${berita?.penulis}", Toast.LENGTH_SHORT).show()
        }

        binding.btnPrevNews.setOnClickListener {
            Toast.makeText(this, "Membuka Berita Sebelumnya", Toast.LENGTH_SHORT).show()
        }

        binding.btnNextNews.setOnClickListener {
            Toast.makeText(this, "Membuka Berita Selanjutnya", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFavIcon(isFav: Boolean) {
        val icon = if (isFav) android.R.drawable.btn_star_big_on else R.drawable.ic_favorite
        binding.btnFavDetail.setImageResource(icon)
    }
}
