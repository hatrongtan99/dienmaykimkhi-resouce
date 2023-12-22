package com.hatrongtan99.app.config;

import com.hatrongtan99.app.utils.AuthenticationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
//        return AuthenticationUtils::getCurrentAuditing;
        return () -> {
            return Optional.of(1l);
        };
    }
}
