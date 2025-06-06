package app.controller;

import Plan.Main;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ListView<?> listGrp;

    @FXML
    void 4b4884(ActionEvent event) {

    }

    @FXML
    void 4b4884(ActionEvent event) {

    }

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
    	bnSupprimer.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    	bnEnlever.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    	bnDeplacer.disableProperty().bind(
				Bindings.equal(listGrp.getSelectionModel().selectedIndexProperty(), -1));
    }

}
