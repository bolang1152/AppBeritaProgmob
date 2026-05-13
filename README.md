# AppBerita - Android News Application

AppBerita adalah aplikasi berita Android sederhana yang menampilkan daftar berita trending menggunakan arsitektur modern Android. Aplikasi ini menggunakan **RecyclerView** untuk list data dan **Coil** untuk pemuatan gambar secara efisien.

## 🚀 Fitur Utama
- **Daftar Berita Trending**: Menampilkan berita terkini dengan gambar, judul, dan kategori.
- **Image Loading**: Menggunakan library Coil untuk handling gambar yang ringan dan cepat.
- **View Binding**: Interaksi komponen layout yang lebih aman dan ringkas.
- **Responsive Layout**: Menggunakan ConstraintLayout untuk tampilan yang fleksibel.

## 🛠️ Tech Stack & Library
- **Bahasa**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: Android Jetpack (ConstraintLayout, RecyclerView)
- **View Binding**: Untuk menggantikan `findViewById`.
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) (v2.6.0)
- **Core KTX**: Untuk penulisan kode Kotlin yang lebih ringkas.

## 📦 Struktur Folder Penting
- `TrendingAdapter.kt`: Adapter untuk mengatur tampilan item berita di list trending.
- `ItemTrendingBinding`: Layout binding yang digunakan untuk menampilkan data per baris.

## ⚙️ Cara Menjalankan Project
```
1. Clone repository ini:
   git clone [https://github.com/bolang1152/nama-repository.git](https://github.com/bolang1152/nama-repository.git)
2. Buka project di **Android Studio (Ladybug atau versi terbaru)**.
3. Tunggu proses **Gradle Sync** selesai.
4. Jalankan aplikasi di Emulator atau Device fisik.

## 📝 Contoh Kode (TrendingAdapter)

Berikut adalah cuplikan implementasi `TrendingAdapter` menggunakan Coil:

inner class TrendingViewHolder(private val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        binding.tvNewsTitle.text = news.title
        binding.ivNewsThumbnail.load(news.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
        }
    }
}

```

## ⏳ Roadmap Selanjutnya

* [ ] Pengembangan Aplikasi Sisi Pembaca (News Reader App).
* [ ] Integrasi Database / API Real-time.
* [ ] Fitur Push Notification untuk berita terbaru.

## 👨‍💻 Developer

* **bolang1152** - [GitHub Profile](https://github.com/bolang1152)

---

*Project ini dikembangkan untuk tugas pemrograman mobile (Progmob).*

```

```
