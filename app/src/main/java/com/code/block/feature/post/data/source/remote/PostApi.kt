package com.code.block.feature.post.data.source.remote

import com.code.block.core.data.dto.CreatePostResponse
import com.code.block.core.domain.model.Post
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PostApi {

    @GET("/api/post/follow/get")
    suspend fun getPostForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    @Multipart
    @POST("/api/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postContent: MultipartBody.Part
    ): CreatePostResponse

    companion object {
        const val BASE_URL = "http://10.0.2.2:8081/"
    }
}
