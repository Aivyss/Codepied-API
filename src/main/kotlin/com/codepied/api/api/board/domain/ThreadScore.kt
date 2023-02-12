package com.codepied.api.api.board.domain

import com.codepied.api.api.domain.Audit
import com.codepied.api.user.domain.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/10
 */


@Entity
@Table(name = "THREAD_SCORE")
@EntityListeners(AuditingEntityListener::class)
class ThreadScore(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "THREAD_KEY")
        val id: Long,

        @Column(name = "SCORE", nullable = false, updatable = true)
        var score: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "THREAD_KEY", nullable = false, updatable = false)
        val thread: Thread,

        @Embedded
        val audit: Audit = Audit(),
)