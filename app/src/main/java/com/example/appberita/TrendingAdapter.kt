package com.example.appberita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appberita.databinding.ItemTrendingBinding

class TrendingAdapter(
    private val items: List<Berita>,
    private val onItemClick: (Berita) -> Unit
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Berita) {
            binding.ivTrendingBg.load(item.foto) {
                crossfade(true)
                placeholder(R.drawable.ic_android_news)
                error(R.drawable.ic_android_news)
            }
            binding.tvTrendingTitle.text = item.judul
            binding.tvTrendingDesc.text = item.deskripsi
            binding.tvTrendingLabel.text = item.kategori
            
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}