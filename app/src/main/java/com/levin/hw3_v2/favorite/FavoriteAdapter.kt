package com.levin.hw3_v2.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levin.hw3_v2.favorite.FavoriteFragment.Companion.favoriteList
import com.levin.hw3_v2.FavoriteItem
import com.levin.hw3_v2.R


class FavoriteAdapter (private val clickListener: FavoriteClickListener): RecyclerView.Adapter<FavoriteVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favorite,parent,false)
        return FavoriteVH(view)
    }

    override fun onBindViewHolder(holder: FavoriteVH, position: Int) {
        val item = favoriteList[position]
        holder.bind(item)

        holder.favoriteRmvBTN.setOnClickListener {
            clickListener.onBtnRemoveClick(item, position)
        }

    }

    override fun getItemCount() = favoriteList.size

    interface FavoriteClickListener {
        fun onBtnRemoveClick(favoriteItem: FavoriteItem, position: Int)
    }

}