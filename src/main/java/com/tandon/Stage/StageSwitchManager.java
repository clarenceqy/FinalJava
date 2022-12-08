package com.tandon.Stage;

import com.tandon.controllers.HomeVC;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageSwitchManager implements ApplicationListener<StageSwitchEvent> {
    private final ApplicationContext applicationContext;

    public StageSwitchManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageSwitchEvent event) {
        Stage stage = event.getStage();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HomeVC.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
