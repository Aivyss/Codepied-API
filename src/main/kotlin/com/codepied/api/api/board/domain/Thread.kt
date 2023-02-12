package com.codepied.api.api.board.domain

import com.codepied.api.api.domain.Audit
import com.codepied.api.user.domain.User

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import com.codepied.api.api.board.domain.Thread as Thread;


/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/10
 */
@Entity
@Table(name = "THREAD")
@EntityListeners(AuditingEntityListener::class)
class Thread(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "THREAD_KEY")
        val id: Long,

        @Column(name = "TITLE", nullable = false, updatable = true, length = 128)
        var title: String,

        @Column(name = "UUID", nullable = false, updatable = false)
        val uuid: UUID,

        @Column(name = "CONTENT", nullable = false, updatable = true, length = 3026)
        var content: String,

        @Column(name = "ANONYMOUS", nullable = false, updatable = false)
        val anonymous: Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "BOARD_KEY", nullable = false, updatable = false)
        val board: Board,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "USER_KEY", nullable = false, updatable = false)
        val user: User,

        @Embedded
        val audit: Audit = Audit(),
)

interface ThreadRepository : JpaRepository<Thread, Long> {

}

object ThreadFactory {
    fun create(
            title: String,
            content: String,
            anonymous: Boolean,
            board: Board,
            user: User,
    ): Thread {
        return Thread(
                id = 0L,
                title = title,
                uuid = UUID.randomUUID(),
                board = board,
                user = user,
                anonymous = anonymous,
                content = content,
        )
    }
}
