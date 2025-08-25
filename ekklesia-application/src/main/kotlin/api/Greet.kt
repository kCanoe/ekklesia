package com.kcanoe.ekklesia.api

import com.kcanoe.ekklesia.models.GreetView

fun greet(): GreetView = GreetView(handle = "kCanoe", greeting = "Welcome to Ekklesia.")
