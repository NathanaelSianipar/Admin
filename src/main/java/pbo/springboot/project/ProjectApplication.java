package pbo.springboot.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
        try {

            Runtime.getRuntime().exec(
                    "rundll32 url.dll,FileProtocolHandler http://localhost:8080/");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}