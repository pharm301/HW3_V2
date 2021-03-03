package com.levin.hw3_v2.favorite

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levin.hw3_v2.FavoriteItem
import com.levin.hw3_v2.R


class FavoriteVH( itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val filmnameTV : TextView = itemView.findViewById(R.id.filmname)
    private val filmposterIV : ImageView = itemView.findViewById(R.id.filmPoster)
    val favoriteRmvBTN: Button = itemView.findViewById(R.id.btnRemove)

    fun bind (item : FavoriteItem) {
        val resID = itemView.context.resources.getIdentifier(item.postername,"drawable",itemView.context.packageName)
        filmnameTV.text = item.filmname
        filmposterIV.background = itemView.context.resources.getDrawable(resID,null)
    }
}