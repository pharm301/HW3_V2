package com.levin.hw3_v2.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.levin.hw3_v2.App
import com.levin.hw3_v2.gallery.FilmItem
import com.levin.hw3_v2.R


class DetailFragment : Fragment() {

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collapsingToolbar = view.findViewById(R.id.collapsing_toolbarFilmname) as CollapsingToolbarLayout
        collapsingToolbar.title = App.curItem.filmname
        val filmdetailTV : TextView = view.findViewById(R.id.textDetail)
        val filmposterIV : ImageView = view.findViewById(R.id.imagePoster)

        var resID = view.context.resources.getIdentifier(
            App.curItem.postername,
            "drawable",
            view.context.packageName
        )
        filmposterIV.background = ResourcesCompat.getDrawable(view.resources, resID, null)
        resID = view.context.resources.getIdentifier(
            App.curItem.filmdetail,
            "string",
            view.context.packageName
        )
        filmdetailTV.text = view.context.getString(resID)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_detail)

        if (App.curItem.favor) {
            fab.setImageDrawable(view.resources.getDrawable(R.drawable.is_favor, null))
            fab.backgroundTintList = view.resources.getColorStateList (R.color.yellow,null)
        }
        else {
            fab.setImageDrawable(view.resources.getDrawable(R.drawable.no_favor, null))
           fab.backgroundTintList = view.resources.getColorStateList (R.color.teal_200,null)
        }
        fab.setOnClickListener { view ->
            if (App.curItem.favor) {
                App.curItem.favor = false
                fab.setImageDrawable(view.resources.getDrawable(R.drawable.no_favor, null))
                fab.backgroundTintList = view.resources.getColorStateList (R.color.teal_200,null)
                Toast.makeText(this.requireContext(), "Не нравится :(", Toast.LENGTH_SHORT).show()
            } else {
                App.curItem.favor = true
                fab.setImageDrawable(view.resources.getDrawable(R.drawable.is_favor, null))
                fab.backgroundTintList = view.resources.getColorStateList (R.color.yellow,null)
                Toast.makeText(this.requireContext(), "Нравится !", Toast.LENGTH_SHORT).show()
            }

        }
    }
}