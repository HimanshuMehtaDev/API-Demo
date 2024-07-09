package com.live.tasknew.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.live.tasknew.ImageResponseClass
import com.live.tasknew.R
import com.live.tasknew.databinding.ItemHorizontalBinding

class HorizontalAdapter (private val list: List<ImageResponseClass.ImageResponseClassItem>):
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(list[position].avatar_url)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(var binding: ItemHorizontalBinding): RecyclerView.ViewHolder(binding.root)
}