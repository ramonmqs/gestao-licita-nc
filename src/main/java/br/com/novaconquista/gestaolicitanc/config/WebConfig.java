package br.com.novaconquista.gestaolicitanc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera para todos os endpoints da API
                .allowedOriginPatterns("*") // Permite requisições do seu localhost e do Vercel
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // O passe VIP para o DELETE!
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}