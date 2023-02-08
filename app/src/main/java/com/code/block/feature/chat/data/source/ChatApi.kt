package com.code.block.feature.chat.data.source

import com.code.block.feature.chat.data.source.response.ChatDto
import com.code.block.feature.chat.data.source.response.MessageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatApi {

    @GET("/api/chats")
    suspend fun getChatsForUser(): List<ChatDto>

    @GET("/api/chat/messages")
    suspend fun getMessagesForChat(
        @Query("chatId") chatId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): List<MessageDto>
}
