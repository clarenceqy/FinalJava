package com.tandon.Spring;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FinalJavaApplication {

    public static void main(String[] args) {
        Application.launch(ScheduleApplication.class, args);
    }

}
