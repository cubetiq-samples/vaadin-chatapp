package com.cubetiqs.chatapp.view

import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("login")
internal class LoginView : VerticalLayout() {
    init {
        val form = LoginForm()
        form.action = "login"
        add(form)
    }
}