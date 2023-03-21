package com.cubetiqs.chatapp.view

import com.cubetiqs.chatapp.service.ChatService
import com.vaadin.flow.component.messages.MessageInput
import com.vaadin.flow.component.messages.MessageList
import com.vaadin.flow.component.messages.MessageListItem
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.Command
import jakarta.annotation.security.PermitAll


@Route("")
@PermitAll
internal class ChatView(service: ChatService) : VerticalLayout() {
    init {
        val messageList = MessageList()
        val textInput = MessageInput()
        setSizeFull()
        add(messageList, textInput)
        expand(messageList)
        textInput.setWidthFull()
        service.join().subscribe { message ->
            val nl = ArrayList(messageList.items)
            nl.add(MessageListItem(message.text, message.time, message.username))
            ui.ifPresent { ui -> ui.access(Command { messageList.setItems(nl) } as Command?) }
        }
        textInput.addSubmitListener { event -> service.add(event.value) }
    }
}