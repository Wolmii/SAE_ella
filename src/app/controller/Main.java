package app.controller;

import java.io.IOException;
import java.util.List;

import app.service.Data;
import app.service.Gala;
import app.service.Table;
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
	static public Gala gala = new Gala();
	
	
	public void start(Stage maFenetre) throws IOException{
		gala = Data.makeGala();
		fAjout = new FenAjouter();
		fAjout.initModality(Modality.APPLICATION_MODAL);
		fTable = new FenTable();
		fTable.initModality(Modality.APPLICATION_MODAL);
		fPlan = new FenPlan();
		fPlan.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}
	
///////////////////////
// gestion des fenêtres
///////////////////////
	
	static public void ouvrirAjouter() {
		fAjout.ctrl.setTable(null);
		fAjout.show();
	}
	
	static public void ouvrirAjouter(Table table) {
		fAjout.ctrl.setTable(table);
		fAjout.show();
	}
	
	static public void fermerAjouter() {
		fAjout.close();
	}
	
	static public void ouvrirTable(int numTable) {
	    fTable.ctrl.lblTable.setText(String.valueOf(numTable));
	    fTable.ctrl.setTable(Main.gala.getTableByNumber(numTable));
	    fTable.show();
	}
	
	
//////////////////////////////////////////
// gestion des données : les modifications
//////////////////////////////////////////
	
	static public void rechercheParNom(String fullName) {
		Table table = gala.getTableDe(fullName);
		if (table != null) {
			ouvrirTable(table.getNumber());
			fTable.ctrl.listPersonnes.getSelectionModel().select(table.getPersonByFullName(fullName));
		} else {
			Main.fPlan.ctrl.afficherErreur("la personne n'a pas été trouvée");
		}
	}

}
