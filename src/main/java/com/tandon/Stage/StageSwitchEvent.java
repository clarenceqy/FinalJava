package com.tandon.Stage;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageSwitchEvent extends ApplicationEvent {
    public Stage getStage() {
        return (Stage) getSource();
    }

    public StageSwitchEvent(Stage source) {
        super(source);
    }
}
