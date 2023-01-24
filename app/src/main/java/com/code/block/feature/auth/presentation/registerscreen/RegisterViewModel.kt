package com.code.block.feature.auth.presentation.registerscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.block.core.domain.resource.Resource
import com.code.block.core.domain.state.PasswordTextFieldState
import com.code.block.core.domain.state.TextFieldState
import com.code.block.core.presentation.components.Screen
import com.code.block.core.util.ui.UiEvent
import com.code.block.core.util.ui.UiText
import com.code.block.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _usernameState = mutableStateOf(TextFieldState())
    val usernameState: State<TextFieldState> = _usernameState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _usernameState.value = _usernameState.value.copy(
                    text = event.username
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.email
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )
            }
            is RegisterEvent.ClearUsername -> {
                _usernameState.value = TextFieldState()
            }
            is RegisterEvent.ClearEmail -> {
                _emailState.value = TextFieldState()
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }
            is RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            _registerState.value = _registerState.value
                .copy(isLoading = true)
            registerUseCase(
                email = emailState.value.text,
                username = usernameState.value.text,
                password = passwordState.value.text
            ).also { registerResult ->
                registerResult.registerError?.let {
                    it.emailError?.let {
                        _emailState.value = emailState.value.copy(
                            error = registerResult.registerError.emailError
                        )
                    }
                    it.usernameError?.let {
                        _usernameState.value = _usernameState.value.copy(
                            error = registerResult.registerError.usernameError
                        )
                    }
                    it.passwordError?.let {
                        _passwordState.value = _passwordState.value.copy(
                            error = registerResult.registerError.passwordError
                        )
                    }
                }
                when (registerResult.result) {
                    is Resource.Success -> {
                        _eventFlow.emit(
                            UiEvent.Navigate(Screen.LoginScreen.route)
                        )
                        initial()
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(
                            UiEvent.SnackBarEvent(
                                uiText = registerResult.result.uiText ?: UiText.unknownError()
                            )
                        )
                        _registerState.value = _registerState.value
                            .copy(isLoading = false)
                    }
                    null -> {
                        _registerState.value = _registerState.value
                            .copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun initial() {
        _registerState.value = _registerState.value
            .copy(isLoading = false)
        _emailState.value = TextFieldState()
        _usernameState.value = TextFieldState()
        _passwordState.value = PasswordTextFieldState()
    }
}
