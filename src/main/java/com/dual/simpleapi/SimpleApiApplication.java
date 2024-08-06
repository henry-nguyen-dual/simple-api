package com.dual.simpleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
@ControllerAdvice
public class SimpleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApiApplication.class, args);
    }

    @PostMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> test(@RequestBody Map<String, Object> body) {
        if (body == null) {
            throw new IllegalArgumentException("Body is null");
        }

        Object age = body.get("age");
        if (age == null) {
            throw new IllegalArgumentException("Age is null");
        }

        if (!(age instanceof Integer)) {
            throw new IllegalArgumentException("Age is not an integer");
        }

        return Map.of("isAdult", (int) age >= 18);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }
}
