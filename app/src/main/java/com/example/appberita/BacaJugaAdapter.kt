package com.example.appberita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appberita.databinding.ItemBacaJugaBinding

class BacaJugaAdapter(
    private val listBerita: List<Berita>,
    private val onItemClick: (Berita) -> Unit
) : RecyclerView.Adapter<BacaJugaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBacaJugaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBacaJugaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val berita = listBerita[position]
        val context = holder.binding.root.context
        
        // Menggunakan Coil untuk memuat gambar dari internet
        holder.binding.ivFoto.load(berita.foto) {
            crossfade(true)
            placeholder(R.drawable.ic_android_news)
            error(R.drawable.ic_android_news)
        }

        holder.binding.tvJudul.text = berita.judul
        holder.binding.tvMeta.text = context.getString(R.string.news_info_format, berita.waktu, berita.views)

        holder.binding.root.setOnClickListener { onItemClick(berita) }
    }

    override fun getItemCount(): Int = listBerita.size
}