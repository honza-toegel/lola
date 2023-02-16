package com.jto.lola

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class SpringUtils {
    companion object {
        fun getCurrentUserId(): String {
            val principal = requireNotNull(SecurityContextHolder.getContext()?.authentication?.principal) { "No user in the current spring context, its a system thread?" }
            return if (principal is UserDetails)
                principal.username
            else
                principal.toString()
        }
    }
}