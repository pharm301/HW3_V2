package com.levin.hw3_v2.gallery

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.levin.hw3_v2.R

class FilmVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val filmnameTV : TextView = itemView.findViewById(R.id.filmname)
    val filmdetailTV : TextView = itemView.findViewById(R.id.filmdetail)
    val filmposterIV : ImageView = itemView.findViewById(R.id.filmPoster)
    val filmfavorIV : ImageView = itemView.findViewById(R.id.favor)

    fun bind (item : FilmItem) {
        filmnameTV.text = item.filmname
        var resID = itemView.context.resources.getIdentifier(item.postername,"drawable",itemView.context.packageName)
        filmposterIV.background = ResourcesCompat.getDrawable(itemView.resources,resID,null)
        resID = itemView.context.resources.getIdentifier(item.filmdetail,"string",itemView.context.packageName)
        filmdetailTV.text = itemView.context.getString(resID).substring(0,80)+" ......"
        //itemView.context.resources.getDrawable(resID,null)
 //       val drawable = ResourcesCompat.getDrawable(itemView.resources,R.drawable.ic_launcher_background,null)
//        filmposterIV.background = item.context.resources.getDrawable(resID,null)

        if (item.favor)  filmfavorIV.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.is_favor,null)
        else filmfavorIV.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.no_favor,null)

    }
}