package com.code.block.core.utils

import com.code.block.core.domain.model.Profile

sealed class Resource<T>(
    val data: T? = null,
    val uiText: UiText? = null
) {
    class Success<T>(data: T? = null, uiText: UiText?) : Resource<T>(data, uiText)
    class Error<T>(data: T? = null, uiText: UiText?) : Resource<T>(data, uiText)
}

typealias RegisterResource = Resource<Unit>
typealias LoginResource = Resource<Unit>
typealias AuthenticationResource = Resource<Unit>
typealias CreatePostResource = Resource<Unit>
typealias ProfileResource = Resource<Profile>
