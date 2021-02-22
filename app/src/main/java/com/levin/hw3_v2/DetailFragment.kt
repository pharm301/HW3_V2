package com.levin.hw3_v2


import android.content.res.ColorStateList
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
import com.google.android.material.snackbar.Snackbar


class DetailFragment(private val filmItem: FilmItem) : Fragment() {
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
        collapsingToolbar.title = filmItem.filmname
        val filmdetailTV : TextView = view.findViewById(R.id.textDetail)
        val filmposterIV : ImageView = view.findViewById(R.id.imagePoster)

        var resID = view.context.resources.getIdentifier(
            filmItem.postername,
            "drawable",
            view.context.packageName
        )
        filmposterIV.background = ResourcesCompat.getDrawable(view.resources, resID, null)
        resID = view.context.resources.getIdentifier(
            filmItem.filmdetail,
            "string",
            view.context.packageName
        )
        filmdetailTV.text = view.context.getString(resID)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_detail)

        if (filmItem.favor) {
            fab.setImageDrawable(view.resources.getDrawable(R.drawable.is_favor, null))
            fab.backgroundTintList = view.resources.getColorStateList (R.color.yellow,null)
        }
        else {
            fab.setImageDrawable(view.resources.getDrawable(R.drawable.no_favor, null))
           fab.backgroundTintList = view.resources.getColorStateList (R.color.teal_200,null)
        }
        fab.setOnClickListener { view ->
            if (filmItem.favor) {
                filmItem.favor = false
                fab.setImageDrawable(view.resources.getDrawable(R.drawable.no_favor, null))
                fab.backgroundTintList = view.resources.getColorStateList (R.color.teal_200,null)
                Toast.makeText(this.requireContext(), "Не нравится :(", Toast.LENGTH_SHORT).show()
            } else {
                filmItem.favor = true
                fab.setImageDrawable(view.resources.getDrawable(R.drawable.is_favor, null))
                fab.backgroundTintList = view.resources.getColorStateList (R.color.yellow,null)
                Toast.makeText(this.requireContext(), "Нравится !", Toast.LENGTH_SHORT).show()
            }

        }
    }
}