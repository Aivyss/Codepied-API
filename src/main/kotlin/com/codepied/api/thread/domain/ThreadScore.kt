package com.codepied.api.thread.domain

import com.codepied.api.api.domain.Audit
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
        @Column(name = "THREAD_KEY", nullable = false)
        val id: Long,

        @OneToOne(fetch = FetchType.LAZY)
        @MapsId
        @JoinColumn(name = "THREAD_KEY", nullable = false, updatable = false)
        val thread: Thread,

        @Column(name = "SCORE", nullable = false, updatable = true)
        var score: Long,

        @Embedded
        val audit: Audit = Audit(),
)