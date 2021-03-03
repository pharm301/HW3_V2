package com.levin.hw3_v2

import android.app.Application
import com.levin.hw3_v2.gallery.FilmItem

class App : Application() {
    companion object {
        var items = mutableListOf(
                FilmItem(0, "Возвращение джедая", "film4", "film4", false),
                FilmItem(0, "Мандалорец", "film1", "film1", false),
                FilmItem(0, "Терминатор 2", "film3", "film3", false),
                FilmItem(0, "Игра престолов", "film2", "film2", false),
                FilmItem(0, "Чужие", "film5", "film5", false),
                FilmItem(0, "Робокоп", "film6", "film6", false),
                FilmItem(0, "Выход Дракона", "film7", "film7", false),
                FilmItem(0, "Хороший, плохой, злой", "film8", "film8", false),
                FilmItem(0, "Проект А", "film9", "film9", false),
                FilmItem(0, "Гладиатор", "film10", "film10", false),
        )
        lateinit var curItem: FilmItem
    }
}