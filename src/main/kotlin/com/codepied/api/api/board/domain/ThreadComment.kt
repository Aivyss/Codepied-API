package com.codepied.api.api.board.domain

import com.codepied.api.api.board.domain.Thread
import com.codepied.api.api.domain.Audit
import com.codepied.api.user.domain.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.Optional
import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/09
 */
@Entity
@Table(name = "THREAD_COMMENT")
@EntityListeners(AuditingEntityListener::class)
class ThreadComment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "THREAD_COMMENT_KEY")
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "THREAD_KEY", nullable = false, updatable = false)
        val thread: Thread,

        @Column(name = "CONTENT", nullable = false, updatable = true)
        var content: String,

        @OneToOne(optional = true)
        @JoinColumn(name = "THREAD_COMMENT_KEY", nullable = true, updatable = false)
        val parent: ThreadComment?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "USER_KEY", nullable = false, updatable = false)
        val user: User,

        @Embedded
        val audit: Audit = Audit(),
)