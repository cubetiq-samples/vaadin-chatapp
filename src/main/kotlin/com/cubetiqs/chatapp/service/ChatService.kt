package com.cubetiqs.chatapp.service

import com.cubetiqs.chatapp.model.Message
import com.vaadin.flow.spring.security.AuthenticationContext
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.time.Instant

@Service
class ChatService(
    private val authenticationCtx: AuthenticationContext,
) {
    private val messages: Sinks.Many<Message> = Sinks.many().multicast().onBackpressureBuffer()
    private val messagesFlux = messages.asFlux()

    fun join(): Flux<Message> {
        return messagesFlux
    }

    fun add(text: String) {
        val msg = Message(
            username = authenticationCtx.principalName.orElse("anonymous"),
            text = text,
            time = Instant.now(),
        )

        messages.tryEmitNext(msg)
    }
}