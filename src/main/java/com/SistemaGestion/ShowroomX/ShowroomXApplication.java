package com.SistemaGestion.ShowroomX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.SistemaGestion.ShowroomX.Repository")
@ComponentScan({"com.SistemaGestion.ShowroomX.Controller", "com.SistemaGestion.ShowroomX.Service", "com.SistemaGestion.ShowroomX"})
public class ShowroomXApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShowroomXApplication.class, args);
    }

}
