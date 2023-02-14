package com.codepied.api.api.board.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 * @author junyeong.jo .
 * @since 2023-02-13
 */
data class BoardCreate(
        @field:NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
        @field:Pattern(regexp = "[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|]*", message = "EXCEPTION.PARAMETERS.INVALID_CHARACTER")
        val name: String,
)