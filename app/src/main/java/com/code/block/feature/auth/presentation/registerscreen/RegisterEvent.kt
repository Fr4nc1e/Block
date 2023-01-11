package com.code.block.feature.auth.presentation.registerscreen

sealed class RegisterEvent {
    data class EnteredUsername(val username: String) : RegisterEvent()
    data class EnteredEmail(val email: String) : RegisterEvent()
    data class EnteredPassword(val password: String) : RegisterEvent()
    object ClearUsername : RegisterEvent()
    object ClearEmail : RegisterEvent()
    object TogglePasswordVisibility : RegisterEvent()
    object Register : RegisterEvent()
}
