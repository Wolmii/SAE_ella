package app.controller;

import app.service.Data;
import app.service.Personne;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CtrlTable {

    @FXML
    private Button bnSupprimer;

    @FXML
    private Button bnDeplacer;

    @FXML
    private Label lblTable;

    @FXML
    private Button bnValider;

    @FXML
    private Button bnAjouter;

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnEnlever;
    
    @FXML
    private ListView<Personne> listGrp;

    @FXML
    void ajouter(ActionEvent event) {
    	Main.ouvrirAjouter();
    }

    @FXML
    void enlever(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

    }

    @FXML
    void deplacer(ActionEvent event) {

    }

    @FXML
    void annuler(ActionEvent event) {
    	Main.fermerTable();
    }

    @FXML
    void valider(ActionEvent event) {

    }
    
    public void initialize() {
    	listGrp = new ListView<Personne>();
    	ObservableList<Personne> groupe  =FXCollections.observableArrayList (Data.g1.getMembres());
    	listGrp.setItems(groupe);
    	for (Personne n : listGrp.getItems()) {
    		System.out.println(n);
    	}
    	bnSupprimer.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    	bnEnlever.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    	bnDeplacer.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    }

}
