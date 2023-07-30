package com.ceceply.javafx.listener;

import com.ceceply.javafx.event.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
	private final String applicationTitle;
	private final Resource fxml;
	private final ApplicationContext context;

	StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
								@Value("classpath:/main.fxml") Resource resource,
								ApplicationContext context) {
		this.applicationTitle = applicationTitle;
		this.fxml = resource;
		this.context = context;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(fxml.getURL());
			loader.setControllerFactory(context::getBean);

			Scene scene = new Scene(loader.load(), 600, 600);

			Stage stage = event.getStage();
			stage.setScene(scene);
			stage.setTitle(applicationTitle);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
