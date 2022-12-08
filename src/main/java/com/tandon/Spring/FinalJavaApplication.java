package com.tandon.Spring;

import com.tandon.DAO.POJOs.UserSession;
import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = "com.tandon", exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.tandon")
@EnableJpaRepositories(basePackages = "com.tandon.DAO.Repo")
@EntityScan(basePackages = "com.tandon.DAO.POJOs")
public class FinalJavaApplication {

    public static void main(String[] args) {
        Application.launch(ScheduleApplication.class, args);
    }

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }

    @Bean
    public UserSession userSession(ConfigurableApplicationContext applicationContext){
        return new UserSession();
    }

}
