package com.example.appberita

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.appberita.databinding.ActivityMainBinding
import com.example.appberita.databinding.NavHeaderBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BeritaAdapter
    private lateinit var trendingAdapter: TrendingAdapter

    private val profileImageUrl = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=200"
    private val logoUrl = "https://cdn-icons-png.flaticon.com/512/21/21601.png"

    private val masterList = mutableListOf(
        Berita(
            id = 1,
            foto = "https://images.unsplash.com/photo-1677442136019-21780ecad995?w=800",
            judul = "Revolusi AI di 2026: Masa Depan Coding Ada di Tangan Robot?",
            deskripsi = "AI kini bisa membantu coding aplikasi Android dengan lebih cepat dan akurat.",
            kategori = "Teknologi",
            waktu = "2 jam lalu",
            views = "1.2K",
            isiBerita = "Kecerdasan buatan (AI) terus berkembang pesat dan membawa perubahan besar di berbagai industri, termasuk dalam dunia pengembangan aplikasi Android.\n\nDengan bantuan AI, developer dapat menulis kode lebih cepat, menemukan bug lebih mudah, dan membuat aplikasi yang lebih cerdas.",
            isTrending = true,
            fotoPenulis = "https://images.unsplash.com/photo-1599566150163-29194dcaad36?w=200"
        ),
        Berita(
            id = 2,
            foto = "https://images.unsplash.com/photo-1526304640581-d334cdbbf45e?w=800",
            judul = "Suku Bunga Global Diprediksi Turun, IHSG Langsung Menghijau",
            deskripsi = "Pasar modal Indonesia merespons positif sentimen global terkait kebijakan moneter AS.",
            kategori = "Ekonomi",
            waktu = "3 jam lalu",
            views = "3.1K",
            isiBerita = "Investor menyambut baik laporan terbaru yang menunjukkan inflasi global mulai terkendali. Hal ini memberikan angin segar bagi pasar saham di negara berkembang, termasuk Indonesia.",
            isTrending = true,
            fotoPenulis = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200"
        ),
        Berita(
            id = 3,
            foto = "https://images.unsplash.com/photo-1461896704190-3213a9a81da2?w=800",
            judul = "Skuad Garuda Cetak Sejarah Baru di Kancah Asia",
            deskripsi = "Performa luar biasa ditunjukkan oleh timnas dalam pertandingan krusial semalam.",
            kategori = "Olahraga",
            waktu = "5 jam lalu",
            views = "5.7K",
            isiBerita = "Perjuangan keras para pemain timnas akhirnya membuahkan hasil. Dengan strategi yang matang dari pelatih, tim berhasil mengalahkan lawan tangguh dengan skor meyakinkan.",
            isTrending = true,
            fotoPenulis = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=200"
        ),
        Berita(
            id = 4,
            foto = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800",
            judul = "E-Commerce Lokal Tembus Pasar Eropa, Produk UMKM Jadi Primadona",
            deskripsi = "Pencapaian luar biasa startup lokal dalam memperluas jangkauan pasar internasional.",
            kategori = "Bisnis",
            waktu = "7 jam lalu",
            views = "2.4K",
            isiBerita = "Kualitas produk UMKM Indonesia semakin diakui dunia. Melalui platform digital, pengrajin lokal kini bisa mengekspor barang langsung ke konsumen di Eropa.",
            isTrending = false,
            fotoPenulis = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=200"
        ),
        Berita(
            id = 5,
            foto = "https://images.unsplash.com/photo-1550745165-9bc0b252726f?w=800",
            judul = "Apple Siapkan Fitur AI Generatif di iPhone 16",
            deskripsi = "Bocoran terbaru menyebutkan iOS akan mendapatkan integrasi AI yang sangat dalam tahun ini.",
            kategori = "Teknologi",
            waktu = "10 jam lalu",
            views = "4.2K",
            isiBerita = "Persaingan teknologi ponsel pintar semakin panas. Apple dilaporkan sedang bekerja sama dengan raksasa pencarian untuk menghadirkan kemampuan AI generatif.",
            isTrending = false,
            fotoPenulis = "https://images.unsplash.com/photo-1599566150163-29194dcaad36?w=200"
        ),
        Berita(
            id = 6,
            foto = "https://images.unsplash.com/photo-1526628953301-3e589a6a8b74?w=800",
            judul = "Kenaikan Harga Pangan Global Jadi Tantangan Ketahanan Nasional",
            deskripsi = "Pemerintah menyiapkan strategi khusus hadapi fluktuasi harga komoditas utama.",
            kategori = "Ekonomi",
            waktu = "12 jam lalu",
            views = "1.8K",
            isiBerita = "Perubahan iklim yang tidak menentu berdampak langsung pada hasil panen petani di berbagai belahan dunia.",
            isTrending = false,
            fotoPenulis = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200"
        ),
        Berita(
            id = 7,
            foto = "https://images.unsplash.com/photo-1517649763962-0c623066013b?w=800",
            judul = "Ganda Putra Indonesia Sabet Gelar Juara di Turnamen Bergengsi",
            deskripsi = "Kemenangan dramatis di partai final membuat bendera Merah Putih berkibar di podium tertinggi.",
            kategori = "Olahraga",
            waktu = "1 hari lalu",
            views = "6.1K",
            isiBerita = "Pertandingan yang menegangkan terjadi di final semalam. Pasangan ganda putra andalan Indonesia berhasil menang lewat rubber set.",
            isTrending = false,
            fotoPenulis = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=200"
        ),
        Berita(
            id = 8,
            foto = "https://images.unsplash.com/photo-1512428559087-560fa5ceab42?w=800",
            judul = "Investasi Hijau Jadi Tren Baru di Kalangan Investor Muda",
            deskripsi = "Banyak investor kini mulai melirik saham perusahaan yang ramah lingkungan.",
            kategori = "Bisnis",
            waktu = "1 hari lalu",
            views = "2.9K",
            isiBerita = "Kesadaran akan kelestarian lingkungan mulai mempengaruhi keputusan investasi. Generasi muda kini cenderung memilih instrumen investasi berbasis ESG.",
            isTrending = false,
            fotoPenulis = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=200"
        )
    )

    private val addBeritaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val newBerita = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.getSerializableExtra("NEW_BERITA", Berita::class.java)
            } else {
                @Suppress("DEPRECATION")
                data?.getSerializableExtra("NEW_BERITA") as? Berita
            }

            newBerita?.let {
                masterList.add(0, it)
                adapter.setData(ArrayList(masterList))
                binding.rvBerita.scrollToPosition(0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupRecyclerView()
        setupTrendingViewPager()
        setupNavigation()
        setupSearch()
        setupToolbarActions()
        setupChips()
        showHomePage()
    }

    private fun setupUI() {
        binding.ivLogo.load(logoUrl)
        val headerView = binding.navigationView.getHeaderView(0)
        val headerBinding = NavHeaderBinding.bind(headerView)
        headerBinding.ivUserPhoto.load(profileImageUrl) {
            transformations(CircleCropTransformation())
        }
    }

    private fun setupTrendingViewPager() {
        val trendingNews = masterList.filter { it.isTrending }
        trendingAdapter = TrendingAdapter(trendingNews) { berita ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_BERITA", berita)
            }
            startActivity(intent)
        }
        binding.vpTrending.adapter = trendingAdapter
    }

    private fun setupRecyclerView() {
        adapter = BeritaAdapter(ArrayList(masterList)) { berita ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_BERITA", berita)
            }
            startActivity(intent)
        }
        binding.rvBerita.layoutManager = LinearLayoutManager(this)
        binding.rvBerita.adapter = adapter
    }

    private fun setupNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showHomePage()
                    true
                }
                R.id.nav_add_berita -> {
                    val intent = Intent(this, AddBeritaActivity::class.java)
                    addBeritaLauncher.launch(intent)
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> false
            }
        }

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> binding.bottomNavigation.selectedItemId = R.id.nav_home
                R.id.nav_add_berita -> addBeritaLauncher.launch(Intent(this, AddBeritaActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.nav_logout -> performLogout()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun performLogout() {
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { 
                adapter.filter(s.toString()) 
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupToolbarActions() {
        binding.ivMenu.setOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }
        
        binding.ivSearchIcon.setOnClickListener {
            binding.searchSection.isVisible = !binding.searchSection.isVisible
            if (!binding.searchSection.isVisible) {
                binding.etSearch.text?.clear()
                adapter.setData(ArrayList(masterList))
            }
        }

        // PERBAIKAN: Berpindah ke halaman Notifikasi
        binding.flNotification.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        binding.ivFilter.setOnClickListener {
            Toast.makeText(this, "Fitur filter lanjutan akan segera hadir", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupChips() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                resetToolbarTitle()
                adapter.setData(ArrayList(masterList))
                return@setOnCheckedStateChangeListener
            }
            
            val chip = group.findViewById<Chip>(checkedIds[0])
            val category = chip.text.toString()
            
            // PERBAIKAN: Judul App Bar berubah sesuai kategori
            binding.tvToolbarTitle.text = category
            
            if (category == getString(R.string.category_all)) {
                resetToolbarTitle()
                adapter.setData(ArrayList(masterList))
            } else {
                adapter.filterByCategory(category)
            }
        }
    }

    private fun resetToolbarTitle() {
        binding.tvToolbarTitle.text = getString(R.string.app_name)
    }

    private fun showHomePage() {
        binding.cvTrending.isVisible = true
        binding.chipAll.isChecked = true
        resetToolbarTitle()
        adapter.setData(ArrayList(masterList))
    }
}
