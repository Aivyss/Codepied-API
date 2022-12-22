package com.codepied.api.user.endpoint

import com.codepied.api.api.http.SuccessResponse
import com.codepied.api.api.security.*
import com.codepied.api.endpoint.dto.EmailUserCreate
import com.codepied.api.user.dto.EmailUserLogin
import com.codepied.api.user.dto.SocialUserLogin
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api/users/auths")
class UserAuthController(
    private val socialLoginService: SocialLoginService,
    private val emailLoginService: EmailLoginService,
) {
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login", params = ["type=EMAIL"])
    fun loginEmailAccount(
        @RequestBody @Valid request: EmailUserLogin,
        httpServletResponse: HttpServletResponse,
    ) = SuccessResponse(emailLoginService.login(request).also { responseProcess(httpServletResponse, it) })

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signupEmailAccount(
        @RequestBody @Valid request: EmailUserCreate,
    ) = SuccessResponse(emailLoginService.signup(request), HttpStatus.CREATED)

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    fun loginSocialAccount(
        @RequestBody @Valid request: SocialUserLogin,
        @RequestParam params: Map<String, String>,
        httpServletResponse: HttpServletResponse,
    ): SuccessResponse<LoginInfo> {
        val socialType = SocialType.matches(params["type"])

        return SuccessResponse(socialLoginService.login(socialType, request.authorizationCode).also { responseProcess(httpServletResponse, it) })
    }

    private fun responseProcess(httpServletResponse: HttpServletResponse, loginInfo: LoginInfo) {
        httpServletResponse.setHeader("Authorization", "Bearer ${loginInfo.getAccessToken()}")
        httpServletResponse.setHeader("refresh", "Bearer ${loginInfo.getRefreshToken()}")
    }
}