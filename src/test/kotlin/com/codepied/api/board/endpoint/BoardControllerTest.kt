package com.codepied.api.board.endpoint

import com.codepied.api.board.dto.BoardCreate
import com.codepied.api.test.DocumentEnum
import com.codepied.api.test.RestDocStore
import com.codepied.api.user.endpoint.AbstractBoardEndpointTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author junyeong.jo .
 * @since 2023-02-15
 */
class BoardControllerTest : AbstractBoardEndpointTest("/api/board") {

    @Test
    fun `게시판 생성 성공 `() {
        // * given
        doNothing().`when`(boardService).createBoard(any())

        // * when
        val perform = mockMvc.perform(
            post(uri)
                .header("Authorization", "Bearer $accessToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(""" { "name": "name" } """.trimMargin()))

        // * then
        perform.andExpect(status().isCreated)
            .andDo(document(DocumentEnum.BOARD_CREATE.name,
                requestHeaders(
                    headerWithName("Authorization").description("Bearer \${accessToken}"),
                ),
                requestFields(
                    fieldWithPath("name").type("String").description("게시판 이름 / 공백불가 / 특수문자 불가"),
                ),
                RestDocStore.responseSnippet(
                    fieldWithPath("data").type("Boolean").description("요청이 잘못되지 않을 경우 반드시 true")),
            ))
    }
    @Test
    fun `게시판 생성 실패 - 적합하지않은 게시판 명`() {
        // * given
        doNothing().`when`(boardService).createBoard(any())

        // * when
        val perform = mockMvc.perform(
            post(uri)
                .header("Authorization", "Bearer $accessToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(""" { "name": "!@#!#as" } """.trimMargin()))

        // * then
        perform.andExpect(status().is4xxClientError)
            .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("INVALID_CHARACTER"))
    }
}