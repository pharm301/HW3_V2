package com.levin.hw3_v2.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.levin.hw3_v2.FavoriteItem
import com.levin.hw3_v2.R
import com.levin.hw3_v2.R.color
import com.levin.hw3_v2.R.layout
import com.levin.hw3_v2.UndoItem
import com.levin.hw3_v2.App.Companion.items

class FavoriteFragment : Fragment(){
    companion object {
        const val TAG = "favorite"

        var favoriteList = mutableListOf(
            FavoriteItem(0, "", "")
        )
        var undoList = mutableListOf(
            UndoItem(0,"","")
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerFavorite)

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Отменить удаление "+ undoList.size.toString() + " элементов?", Snackbar.LENGTH_LONG)
                .setActionTextColor(resources.getColor(color.yellow,null))
                .setTextColor(resources.getColor(color.white,null))
                .setAction("Отменить") { view ->
                    var ulCnt = 0
                    while (ulCnt < undoList.size){
                            favoriteList.add(FavoriteItem(fID= undoList[ulCnt].fID,
                                undoList[ulCnt].filmname, undoList[ulCnt].postername))
                        items[undoList[ulCnt].fID].favor=true
                        ulCnt++
                    }
                    undoList.clear()
                    recyclerView.adapter?.notifyDataSetChanged()
                    Toast.makeText(this.requireContext(), "Отменено", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
/*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Jn", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
*/
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        favoriteList.clear()
        undoList.clear()
        val totalFavorites= items.count { filmItem -> filmItem.favor  }
        var i = 0
        if (totalFavorites > 0){
            while (i < items.size){
                if (items[i].favor){
                    favoriteList.add(FavoriteItem(fID= items[i].fID,items[i].filmname,items[i].postername))
                }
                i++
            }
        }
        recyclerView.adapter = FavoriteAdapter(object : FavoriteAdapter.FavoriteClickListener {
            override fun onBtnRemoveClick(favoriteItem: FavoriteItem, position: Int) {
                run {
                    undoList.add(UndoItem(favoriteList[position].fID, favoriteList[position].filmname,
                        favoriteList[position].postername))
                    items[favoriteList[position].fID].favor = false
                    favoriteList.removeAt(position)
                    recyclerView.adapter?.notifyDataSetChanged()

                     fab.isVisible = true
                    // change element -> notifyChange
                }
            }
        })
    }
 }