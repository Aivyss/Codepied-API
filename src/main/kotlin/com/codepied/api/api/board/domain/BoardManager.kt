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
@Table(name = "BOARD_MANAGER")
@EntityListeners(AuditingEntityListener::class)
class BoardManager (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "BOARD_MANAGER_KEY")
    val id :Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_KEY", nullable = false, updatable = false)
    val board: Board,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_KEY", nullable = false, updatable = true)
    var user: User,

    @Embedded
    val audit: Audit = Audit(),
)
