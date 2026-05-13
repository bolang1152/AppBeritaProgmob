package com.example.appberita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appberita.databinding.ItemBeritaBinding

class BeritaAdapter(
    private var listBerita: List<Berita>,
    private val onItemClick: (Berita) -> Unit
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    private var listFull = ArrayList(listBerita)

    fun setData(newData: List<Berita>) {
        listBerita = newData
        listFull = ArrayList(newData)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val filtered = if (query.isEmpty()) listFull else listFull.filter { it.judul.contains(query, true) }
        listBerita = filtered
        notifyDataSetChanged()
    }

    fun filterByCategory(kategori: String) {
        val filtered = if (kategori == "Semua") listFull else listFull.filter { it.kategori == kategori }
        listBerita = filtered
        notifyDataSetChanged()
    }

    inner class BeritaViewHolder(val binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(berita: Berita) {
            val context = binding.root.context
            
            binding.ivFoto.load(berita.foto) {
                crossfade(true)
                placeholder(R.drawable.ic_android_news)
                error(R.drawable.ic_android_news)
            }

            binding.tvJudul.text = berita.judul
            binding.tvKategoriCard.text = berita.kategori
            binding.tvDeskripsi.text = berita.deskripsi
            binding.tvWaktu.text = berita.waktu
            binding.tvViews.text = context.getString(R.string.views_format, berita.views)

            binding.root.setOnClickListener { onItemClick(berita) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        return BeritaViewHolder(ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) = holder.bind(listBerita[position])
    override fun getItemCount() = listBerita.size
}
