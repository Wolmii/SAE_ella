package app.controller;

import java.io.IOException;

import app.service.Data;
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
	
	
	public void start(Stage maFenetre) throws IOException{
		Data.main(null);
		fAjout = new FenAjouter();
<<<<<<< HEAD
		fAjout.initModality(Modality.APPLICATION_MODAL);
		fTable = new FenTable();
		fTable.initModality(Modality.APPLICATION_MODAL);
		fPlan = new FenPlan();
		fTable.show();
=======
		fPlan.show();
>>>>>>> a5598e751a13f0b7125904820280dc06f4c297d8
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
