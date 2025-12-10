package com.school.schoolbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.school")
public class SchoolBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolBackendApplication.class, args);
    }

    // ✅ DETECTIVE: This prints every URL the app knows about!
    @Bean
    public CommandLineRunner listAllEndpoints(ApplicationContext context) {
        return args -> {
            System.out.println("🔎 ===========================================================");
            System.out.println("🔎 SCANNING FOR CONTROLLERS...");

            RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
            Map<RequestMappingInfo, HandlerMethod> methods = mapping.getHandlerMethods();

            if (methods.isEmpty()) {
                System.out.println("❌ NO ENDPOINTS FOUND! Your Controller is hidden.");
            } else {
                methods.forEach((info, method) -> {
                    System.out.println("✅ FOUND: " + info + " -> " + method.getMethod().getName());
                });
            }
            System.out.println("🔎 ===========================================================");
        };
    }
}