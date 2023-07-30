package com.ceceply.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class SimpleController {
	@FXML
	public Button button;
	@FXML
	public Label label;

	@FXML
	public void initialize() {
		button.setOnAction(actionEvent -> label.setText("Hello"));
	}
}
