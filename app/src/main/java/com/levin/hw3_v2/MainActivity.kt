package com.levin.hw3_v2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.levin.hw3_v2.App.Companion.curItem
import com.levin.hw3_v2.App.Companion.items
import com.levin.hw3_v2.detail.DetailFragment
import com.levin.hw3_v2.favorite.FavoriteFragment
import com.levin.hw3_v2.gallery.FilmItem
import com.levin.hw3_v2.gallery.FilmListFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FilmListFragment.OnFilmClickListener{
    companion object {
        var curFrag: Int = 0
        const val TOTAL_FAVORITES = "TOTAL_FAVORITES"
        const val fragTAG = "curFrag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalFavorites: Int = 0

        if (savedInstanceState?.getInt(TOTAL_FAVORITES, totalFavorites) == null) {
            var i = 0
            while (i < items.size) {
                items[i].fID = i
                items[i].favor = false
                i++
            }
            curFrag = 0
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

//        toolbar.title = resources.getString(R.string.menu_gallery)
        if (curFrag==2) {onFilmClick(App.curItem)}
        else useFragMan(curFrag,resources.getString(R.string.menu_gallery))
    }

    private fun useFragMan(myFrag: Int, title : String) {
        curFrag = myFrag
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = title
        invalidateOptionsMenu()
        when (curFrag) {
            0 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, FilmListFragment(), FilmListFragment.TAG)
                    .commit()
            }
            1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, FavoriteFragment(), FavoriteFragment.TAG)
                    .commit()
            }
            2 -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, DetailFragment(), FilmListFragment.TAG)
                        .commit()

            }
         }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            when (curFrag ) {
                1 -> {
                    useFragMan(0, resources.getString(R.string.menu_favorites))
                }
                2 -> {
                useFragMan(0,resources.getString(R.string.menu_gallery))
                }
                else -> super.onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        when (curFrag) {
            0 -> {
                menuInflater.inflate(R.menu.main, menu)
            }
            1 -> {
                menuInflater.inflate(R.menu.favorites, menu)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorites -> {
                useFragMan(1,resources.getString(R.string.menu_favorites))
                true
            }
            R.id.menu_gallery -> {
                useFragMan(0,resources.getString(R.string.menu_gallery))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_gallery -> {
                useFragMan(0,resources.getString(R.string.menu_gallery))
            }
            R.id.nav_favorites -> {
                useFragMan(1,resources.getString(R.string.menu_favorites))
            }
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val totalFavorites: Int = items.count { filmItem -> filmItem.favor }
        outState.putInt(TOTAL_FAVORITES, totalFavorites)
    }

    override fun onFilmClick(filmItem: FilmItem) {
        App.curItem = filmItem
        useFragMan(2,filmItem.filmname)
    }
}