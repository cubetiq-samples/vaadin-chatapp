package com.cubetiqs.chatapp.config

import com.cubetiqs.chatapp.view.LoginView
import com.vaadin.flow.spring.security.VaadinWebSecurity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager

@Configuration
class SecurityConfig : VaadinWebSecurity() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        super.configure(http)
        setLoginView(http, LoginView::class.java)
    }

    @Bean
    fun userDetailsManager(): UserDetailsManager? {
        val users = setOf("sombochea", "cubetiq", "team").stream()
            .map { name: String ->
                User.withDefaultPasswordEncoder().username(name).password("123").roles("USER").build()
            }
            .toList()

        return InMemoryUserDetailsManager(users)
    }
}