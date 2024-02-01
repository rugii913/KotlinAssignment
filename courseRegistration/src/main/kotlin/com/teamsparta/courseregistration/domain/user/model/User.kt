package com.teamsparta.courseregistration.domain.user.model

import com.teamsparta.courseregistration.domain.couseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "course_app_user")
class User(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Embedded
    var profile: Profile,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val courseApplications: MutableList<CourseApplication> = mutableListOf()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = profile.nickname,
        role = role.name
    )
}
