/*
 * Copyright (C) 2023 Fauzan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package my.com.vic3.rest.app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 *
 * @author Fauzan
 */
@SpringBootApplication
public class RestApp implements CommandLineRunner{

    @Autowired
    private RestConfiguration configuration;
    
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting REST Service");
        log.info("Endpoints are available at http://localhost:8080/swagger-ui/index.html");
    }
    
    @Bean
    public OpenAPI openAPI() {
        // swagger for exposing available endpoints
        return new OpenAPI().info(new Info().title("SpringDoc rest-app")
            .description("SpringDoc application for rest-app")
            .version("v0.0.1"));
    }
}
