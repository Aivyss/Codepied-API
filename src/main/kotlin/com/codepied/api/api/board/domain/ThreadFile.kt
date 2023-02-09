package com.codepied.api.api.board.domain

import com.codepied.api.api.domain.Audit
import com.codepied.api.file.domain.CodepiedFile
import com.codepied.api.user.domain.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/09
 */


@Entity
@Table(name = "THREAD_FILE")
@EntityListeners(AuditingEntityListener::class)
class ThreadFile (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THREAD_FILE_KEY")
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THREAD_KEY", nullable = false, updatable = false)
    val thread: Thread,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_KEY", nullable = false, updatable = false)
    val codepiedFile: CodepiedFile,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_KEY", nullable = false, updatable = false)
    val user: User,

    @Embedded
    val audit: Audit = Audit(),
)