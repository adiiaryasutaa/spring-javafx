package com.ceceply.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		javafx.application.Application.launch(JavaFXApplication.class, args);
	}
}
