package app.ui;

import java.io.File;
import java.io.IOException;

import app.controller.CtrlPlan;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenPlan extends Stage {
	private CtrlPlan ctrl;

<<<<<<< HEAD
	public FenPlan() throws IOException {
		this.setTitle("Plan"); 
=======
	private CtrlPlan ctrl;

	public FenPlan() throws IOException {
		this.setTitle("plan"); 
>>>>>>> a5598e751a13f0b7125904820280dc06f4c297d8
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
