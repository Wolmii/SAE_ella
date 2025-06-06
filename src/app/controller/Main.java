package app.controller;

<<<<<<< HEAD
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
=======

import java.io.IOException;

import app.ui.FenAjouter;
import app.ui.FenPlan;
import app.ui.FenTable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	static private FenTable fTable;
	static private FenAjouter fAjout;
	static private FenPlan fPlan;
	
	public void start(Stage maFenetre) throws IOException{
		fPlan = new FenPlan();
		fTable = new FenTable();
		fAjout = new FenAjouter();
		fPlan.show();
	}
>>>>>>> 3a912cd35bd964469fbe75460531701b926630af

	public static void main(String[] args) {
		Application.launch();
	}
<<<<<<< HEAD

	@Override
	public void start(Stage arg0) throws Exception {
		test fentest = new test();
		fentest.show();
		
	}
}
=======
	
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

>>>>>>> 3a912cd35bd964469fbe75460531701b926630af
