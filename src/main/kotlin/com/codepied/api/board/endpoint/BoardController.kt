package com.codepied.api.board.endpoint

import com.codepied.api.board.application.BoardService
import com.codepied.api.board.dto.BoardCreate
import com.codepied.api.api.http.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/board")
class BoardController(private val service: BoardService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBoard(
            @RequestBody
            @Valid request: BoardCreate,
    ) = SuccessResponse(service.createBoard(request), HttpStatus.CREATED)
}