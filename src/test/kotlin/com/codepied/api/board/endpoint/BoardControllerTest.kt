package com.codepied.api.board.endpoint

import com.codepied.api.board.application.BoardService
import com.codepied.api.board.dto.BoardCreate
import com.codepied.api.test.DocumentEnum
import com.codepied.api.test.RestDocStore
import com.codepied.api.user.endpoint.AbstractBoardEndpointTest
import com.codepied.api.user.endpoint.AbstractUserEndpointTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

/**
 * @author junyeong.jo .
 * @since 2023-02-15
 */
class BoardControllerTest : AbstractBoardEndpointTest("/api/board") {

    @Test
    fun `게시판 생성 성공 ` () {
        // * given
        doNothing(
        ).`when`(boardService).createBoard(any())

        // * when
        val perform = mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .header("Authorization", "Bearer $accessToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                    { 
                        "name": "name"
                    }
            """.trimMargin()))

        // * then
        perform.andExpect(status().isCreated)
            MockMvcRestDocumentation.document(DocumentEnum.BOARD_CREATE.name,
            HeaderDocumentation.requestHeaders(
                HeaderDocumentation.headerWithName("Authorization").description("Bearer \${accessToken}"),
            ),
            PayloadDocumentation.requestFields(
                PayloadDocumentation.fieldWithPath("name").type("String").description("게시판 이름 / 공백불가 / 특수문자 불가"),
            ),
        )


    }
}