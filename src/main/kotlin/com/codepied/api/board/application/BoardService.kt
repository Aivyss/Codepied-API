package com.codepied.api.board.application

import com.codepied.api.board.BoardFactory
import com.codepied.api.board.BoardRepository
import com.codepied.api.board.dto.BoardCreate
import com.codepied.api.api.exception.BusinessErrorCode
import com.codepied.api.api.exception.InvalidRequestExceptionBuilder.invalidRequest
import com.codepied.api.api.http.RequestContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BoardService(
        private val boardRepository: BoardRepository,
        private val requestContext: RequestContext,
) {

    fun createBoard(request: BoardCreate) {
        when (boardRepository.findBoardByName(request.name)) {
            null -> {
                boardRepository.save(BoardFactory.create(request.name));
            }
            else -> throw invalidRequest(
                    errorCode = BusinessErrorCode.DUPLICATED_BOARD_NAME,
                    debugMessage = "board name already exits",
                    httpStatus = HttpStatus.BAD_REQUEST
            )
        }
    }
}