package com.tandon.finaljava;

import com.tandon.finaljava.Controller.LoginController;
import com.tandon.finaljava.ScheduleApplication;
import org.springframework.context.ApplicationListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

/**
 * @author Yichao Qin
 */
@Component
public class StageInitializer implements ApplicationListener<ScheduleApplication.StageReadyEvent> {
    private final String applicationTitle;
    private final FxWeaver fxWeaver;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }
    @Override
    public void onApplicationEvent(ScheduleApplication.StageReadyEvent event) {
        Stage stage = event.getStage();
        stage.setTitle(applicationTitle);
        stage.setScene(new Scene(fxWeaver.loadView(LoginController.class), 800, 600));
        stage.show();
    }


}
