package com.hackertronix.cinematic.util

import com.google.gson.Gson
import com.hackertronix.cinematic.model.Movie

object FakeDataFactory {
    fun makeMovie() = Movie(
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
        title = "Wonder Woman 1984" ,
        isFavourite = true,
        voteAverage = 6.9f,
        voteCount = 4253,
        overview = "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
    )
}