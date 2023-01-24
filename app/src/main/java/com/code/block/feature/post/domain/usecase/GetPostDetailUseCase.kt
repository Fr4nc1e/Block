package com.code.block.feature.post.domain.usecase

import com.code.block.feature.post.domain.repository.PostRepository

class GetPostDetailUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String) =
        repository.getPostDetail(postId)
}
