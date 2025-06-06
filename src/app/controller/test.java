package app.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class test extends Stage {
	private CtrlPlan ctrl;

	public test() throws IOException {
		this.setTitle("plan"); 
		this.setResizable(false);
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
		this.sizeToScene();
	}
	
	private Pane creerSceneGraph() throws IOException {
		File f = new File("assets/plan.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
}
