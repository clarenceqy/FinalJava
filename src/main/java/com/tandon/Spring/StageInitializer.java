package com.tandon.Spring;

import com.tandon.controllers.LoginVC;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * @author Yichao Qin
 */
@Component
public class StageInitializer implements ApplicationListener<ScheduleApplication.StageReadyEvent> {
    private final String applicationTitle;
    private final ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext ac) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = ac;
    }

    @Override
    public void onApplicationEvent(ScheduleApplication.StageReadyEvent event) {
        Stage stage = event.getStage();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginVC.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(this.applicationTitle);
        stage.show();
    }


}
