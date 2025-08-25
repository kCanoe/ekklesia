package com.kcanoe.ekklesia.models

import kotlinx.serialization.Serializable

@Serializable
data class GreetView(val handle: String, val greeting: String)
