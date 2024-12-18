package com.hoon.article.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        System.out.println("Authentication succeeded for: " + success.getAuthentication().getName());
        // 인증 성공 시 추가 작업
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failure) {
        System.out.println("Authentication failed: " + failure.getException().getMessage());
        // 인증 실패 시 추가 작업
    }
}
