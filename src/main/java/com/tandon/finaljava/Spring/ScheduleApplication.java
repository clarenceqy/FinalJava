package com.tandon.finaljava;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Yichao Qin
 */
public class ScheduleApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        System.out.println("Started");
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(ScheduleApplication.class).run();
        System.out.println("Started");
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    class StageReadyEvent extends ApplicationEvent {
        public Stage getStage() {
            return (Stage) getSource();
        }

        public StageReadyEvent(Stage source) {
            super(source);
        }
    }
}
