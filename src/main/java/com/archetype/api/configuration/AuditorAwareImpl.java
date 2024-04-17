package com.archetype.api.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Objects;
import java.util.Optional;
public class AuditorAwareImpl implements AuditorAware<String> {
    @Autowired(required = false)
    private HttpServletRequest request;
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            String username = request.getHeader("username");
            return Optional.of(Objects.requireNonNullElse(username, "anonymous"));
        }catch(Exception ex) {
            return Optional.of("internal-process");
        }
    }

}