package com.codepied.api.api.board.domain

import com.codepied.api.api.domain.Audit
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*
import com.codepied.api.api.exception.BusinessErrorCode
import com.codepied.api.api.exception.InvalidRequestExceptionBuilder
import java.util.UUID


/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/09
 */


@Entity
@Table(name = "BOARD")
@EntityListeners(AuditingEntityListener::class)
class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "BOARD_KEY")
    val id :Long,

    @Column(name = "UUID", nullable = false, length = 34)
    val uuid: UUID,

    @Column(name = "NAME", nullable = false, length = 64)
    val name: String,

    @Embedded
    val audit: Audit = Audit(),

) {
    @OneToMany(mappedBy = "board", cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH])
    val thread: MutableList<Thread> = mutableListOf()
}

interface BoardRepository : JpaRepository<Board, Long> {

}
