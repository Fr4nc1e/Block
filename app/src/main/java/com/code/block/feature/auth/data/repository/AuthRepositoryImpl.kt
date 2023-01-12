package com.code.block.feature.auth.data.repository

import com.code.block.R
import com.code.block.core.utils.Resource
import com.code.block.core.utils.SimpleResource
import com.code.block.core.utils.UiText
import com.code.block.feature.auth.data.remote.AuthApi
import com.code.block.feature.auth.data.remote.request.CreateAccountRequest
import com.code.block.feature.auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        val request = CreateAccountRequest(
            email,
            username,
            password
        )

        return try {
            val response = api.register(request)
            if (response.successful) {
                response.message?.let {
                    Resource.Success(uiText = UiText.CallResponseText(it))
                } ?: Resource.Success(uiText = null)
            } else {
                response.message?.let {
                    Resource.Error(uiText = UiText.CallResponseText(it))
                } ?: Resource.Error(uiText = UiText.StringResource(R.string.unknown_error))
            }
        } catch (e: IOException) {
            Resource.Error(uiText = UiText.StringResource(R.string.read_write_error))
        } catch (e: HttpException) {
            Resource.Error(uiText = UiText.StringResource(R.string.fail_to_connect))
        }
    }
}
