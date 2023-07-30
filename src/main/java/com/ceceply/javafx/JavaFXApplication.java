package com.ceceply.javafx;

import com.ceceply.javafx.event.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavaFXApplication extends Application {
	private ConfigurableApplicationContext context;

	@Override
	public void init() {
		ApplicationContextInitializer<GenericApplicationContext> initializer =
			applicationContext -> applicationContext.registerBean(Application.class, () -> JavaFXApplication.this);

		context = new SpringApplicationBuilder()
			.sources(MainApplication.class)
			.initializers(initializer)
			.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage stage) {
		context.publishEvent(new StageReadyEvent(stage));
	}

	@Override
	public void stop() {
		context.stop();
		Platform.exit();
	}
}
