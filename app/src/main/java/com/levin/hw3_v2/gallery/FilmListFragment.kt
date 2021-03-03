package com.levin.hw3_v2.gallery

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levin.hw3_v2.R

class FilmListFragment : Fragment() {
    companion object {
        const val TAG = "gallery"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filmslist, container, false)
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_film)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        recyclerView.adapter = FilmAdapter(object : FilmAdapter.FilmClickListener {

            override fun onFavoriteClick(filmItem: FilmItem, position: Int) {
                // change element -> notifyChange
                filmItem.favor = !filmItem.favor
                recyclerView.adapter?.notifyItemChanged(position)
                if (filmItem.favor)
                    Toast.makeText(requireContext(), "Добавлено",Toast.LENGTH_SHORT).show()
                else Toast.makeText(requireContext(), "Удалено",Toast.LENGTH_SHORT).show()
            }

            override fun onItemClick(filmItem: FilmItem, position: Int) {
                // change element -> notifyDelete
                (activity as? OnFilmClickListener)?.onFilmClick(filmItem)
            }
        })
    }
    interface OnFilmClickListener {
        fun onFilmClick(filmItem: FilmItem)
    }
}