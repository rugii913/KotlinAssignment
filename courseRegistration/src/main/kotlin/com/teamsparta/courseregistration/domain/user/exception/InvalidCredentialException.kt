package com.teamsparta.courseregistration.domain.user.exception

class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
