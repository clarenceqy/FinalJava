package com.tandon.finaljava;

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalJavaApplication {

    public static void main(String[] args) {
        Application.launch(ScheduleApplication.class, args);
    }

}
