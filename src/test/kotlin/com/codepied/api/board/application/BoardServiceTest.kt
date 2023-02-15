package com.codepied.api.board.application

import com.codepied.api.api.exception.BusinessErrorCode
import com.codepied.api.api.exception.CodepiedBaseException
import com.codepied.api.api.http.RequestContext
import com.codepied.api.board.Board
import com.codepied.api.board.BoardRepository
import com.codepied.api.board.dto.BoardCreate
import com.codepied.api.test.AbstractServiceTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.doReturn

/**
 * @author junyeong.jo .
 * @since 2023-02-15
 */
internal class BoardServiceTest : AbstractServiceTest() {
    @Mock
    private lateinit var requestContext: RequestContext

    @InjectMocks
    private lateinit var service: BoardService

    @Mock
    private lateinit var boardRepository: BoardRepository

    @BeforeEach
    fun init() {
        Mockito.lenient().doReturn(1L).`when`(requestContext).userKey
    }

    @AfterEach
    fun clean() {
        Mockito.lenient().doReturn(-1L).`when`(requestContext).userKey
    }

    @Test
    fun `게시판 생성 실패 - 이미 존재하는 게시판 이름` () {
        // * given
        val board = Mockito.mock(Board::class.java)
        doReturn(board).`when`(boardRepository).findBoardByName(anyString())

        val boardName = "TEST123";
        val boardCreate = BoardCreate(boardName)

        // * when
        val throwable = catchThrowable { service.createBoard(boardCreate) }

        // * then
        Assertions.assertThat(throwable is CodepiedBaseException.InvalidRequestException).isTrue
        val exception = throwable as CodepiedBaseException.InvalidRequestException
        Assertions.assertThat(exception.errorCode).isEqualTo(BusinessErrorCode.DUPLICATED_BOARD_NAME)
    }

    @Test
    fun `게시판 생성 성공` () {
        // * given
        val boardTitle = "게시판 제목";
        val boardCreate = BoardCreate(boardTitle);

        // * when
        service.createBoard(boardCreate)
    }
}