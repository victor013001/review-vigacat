package com.vigacat.review.service.component.security.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VigacatSecurityContextImpl implements VigacatSecurityContext {
    @Override
    public String getUsernameAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
