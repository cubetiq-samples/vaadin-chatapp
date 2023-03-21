package com.cubetiqs.chatapp.model

import java.time.Instant

data class Message(
    val username: String,
    val text: String,
    val time: Instant,
) : java.io.Serializable