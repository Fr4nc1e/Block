package com.code.block.domain.model

data class User(
    val profilePictureUrl: Int,
    val username: String,
    val description: Int,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)
