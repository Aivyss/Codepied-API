package com.codepied.api.board.domain

import com.codepied.api.api.domain.Audit
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*
import java.util.UUID


/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/10
 */
@Entity
@Table(name = "BOARD")
@EntityListeners(AuditingEntityListener::class)
class Board(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "BOARD_KEY")
        val id: Long,

        @Column(name = "UUID", nullable = false, updatable = false)
        val uuid: UUID,

        @Column(name = "NAME", nullable = false, updatable = false, length = 64)
        val name: String,

        @Embedded
        val audit: Audit = Audit(),
)

interface BoardRepository : JpaRepository<Board, Long> {
    fun findBoardByName (name: String) : Board?
    fun findBoardById (id: Long) : Board?
}

object BoardFactory {
    fun create(
            name: String,
    ): Board {
        return Board(
                id = 0L,
                uuid = UUID.randomUUID(),
                name = name
        )
    }
}