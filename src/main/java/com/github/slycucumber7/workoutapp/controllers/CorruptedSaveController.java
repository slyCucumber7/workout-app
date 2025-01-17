package com.github.slycucumber7.workoutapp.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CorruptedSaveController {
	@FXML
	Button closeB;

	public CorruptedSaveController() {
	}

	public void closeB(ActionEvent e) {
		Platform.exit();
	}
}
