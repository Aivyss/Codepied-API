package com.codepied.api.api.board.domain

import com.codepied.api.api.domain.Audit
import com.codepied.api.user.domain.User
import javax.persistence.*

/**
 * @author Nairobi
 * @since 2023/02/08
 * @LastModifiedAt 2023/02/09
 */
@Entity
@Table(name = "SUBSCRIBER")
class Subscriber(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "SUBSCRIBER_KEY")
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "BOARD_KEY", nullable = false, updatable = false)
        val board: Board,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "USER_KEY", nullable = false, updatable = false)
        val user: User,

        @Embedded
        val audit: Audit = Audit(),
)
