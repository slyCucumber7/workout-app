package com.github.slycucumber7.workoutapp.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Scene3Controller {
	private com.github.slycucumber7.workoutapp.models.Model model;
	private com.github.slycucumber7.workoutapp.models.singleEntry currentEntry;
	private HBox entryNode;
	@FXML
	TextField entryBox;
	@FXML
	Button confirmB, cancelB;

	public Scene3Controller() {

	}
	
	public void cancel() {
		Stage stage = (Stage) cancelB.getScene().getWindow();
		stage.close();
	}
	
	public void confirm() {
		model.getGraphics().removeEntry(currentEntry, entryNode);
		cancel();
	}

	public void setCurrentEntry(com.github.slycucumber7.workoutapp.models.singleEntry e) {
		this.currentEntry = e;
		entryBox.setText(currentEntry.toString());
	}

	public com.github.slycucumber7.workoutapp.models.singleEntry getCurrentEntry() {
		return currentEntry;
	}

	public void setModel(com.github.slycucumber7.workoutapp.models.Model model) {
		this.model = model;
	}

	public com.github.slycucumber7.workoutapp.models.Model getModel() {
		if (this.model != null) {
			return this.model;
		}
		return null;
	}

	public void setEntryNode(HBox hb) {
		this.entryNode = hb;
	}

	public HBox getEntryNode() {
		if (this.entryNode != null) {
			return this.entryNode;
		}
		return null;
	}
}
