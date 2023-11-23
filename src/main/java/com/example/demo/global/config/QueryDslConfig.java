package com.example.demo.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {
    @PersistenceContext private EntityManager em;

    @Bean
    public JPAQueryFactory query() {
        return new JPAQueryFactory(em);
    }
}