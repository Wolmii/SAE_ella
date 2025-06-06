package app.controller;

import java.io.IOException;

<<<<<<< HEAD
import app.service.Gala;
import app.service.Plan;
=======
import app.service.Data;
>>>>>>> 529c85f14f91ab3e1b6391d1ca919bcb7251407f
import app.ui.FenAjouter;
import app.ui.FenPlan;
import app.ui.FenTable;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	static private FenTable fTable;
	static private FenAjouter fAjout;
	static private FenPlan fPlan;
	static public Gala gala = new Gala("le gala", null, "lannion");
	static public Plan plan;
	
	
	public void start(Stage maFenetre) throws IOException{
		Data.main(null);
		fAjout = new FenAjouter();
		fAjout.initModality(Modality.APPLICATION_MODAL);
		fTable = new FenTable();
		fTable.initModality(Modality.APPLICATION_MODAL);
		fPlan = new FenPlan();
		fTable.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}
	
///////////////////////
// gestion des fenêtres
///////////////////////
	
	static public void ouvrirAjouter() {
		fAjout.show();
	}
	
	static public void fermerAjouter() {
		fAjout.close();
	}
	
	static public void ouvrirTable() {
		fTable.show();
	}
	
	static public void fermerTable() {
		fTable.close();
	}
	
	
//////////////////////////////////////////
// gestion des données : les modifications
//////////////////////////////////////////
	
	
}
