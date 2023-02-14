package com.codepied.api.thread.dto

import com.codepied.api.user.domain.User
import javax.validation.Valid
import javax.validation.constraints.NotBlank

/**
 * @author junyeong.jo .
 * @since 2023-02-14
 */
data class ThreadInfo(
        val title: String,
        val content: String,
        val anonymous: Boolean,
        val userKey: Long,
)
