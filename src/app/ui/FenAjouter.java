package app.ui;

import java.io.File;
import java.io.IOException;

import app.controller.CtrlAjoutParticipant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenAjouter extends Stage{
	public CtrlAjoutParticipant ctrl;

	public FenAjouter() throws IOException {
		this.setTitle("Ajouter Personne"); 
		this.setResizable(false);
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
		this.sizeToScene();
	}
	
	private Pane creerSceneGraph() throws IOException {
		File f = new File("assets/menu_ajout_participant.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
}
