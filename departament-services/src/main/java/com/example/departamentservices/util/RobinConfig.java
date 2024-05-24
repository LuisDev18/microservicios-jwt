package com.example.departamentservices.util;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobinConfig {

    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule(); // Utiliza la estrategia de Round Robin para el balanceo de carga
        // O utiliza otras estrategias como RandomRule, WeightedResponseTimeRule, etc.
    }
}
