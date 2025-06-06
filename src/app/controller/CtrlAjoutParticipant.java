package app.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class CtrlAjoutParticipant {


	@FXML
    private Button ajouter;

    @FXML
    private ListView<?> listPersonne;
    
    @FXML
    private Button bnAnnuler;

    @FXML
    private RadioButton recherche;

    @FXML
    private TextField numTable;
    
    @FXML
    void annuler(ActionEvent event) {
    	Main.fermerAjouter();
    }
    
    public void initialize() {
    	ajouter.disableProperty().bind(
				Bindings.equal(listPersonne.getSelectionModel().selectedIndexProperty(), -1));
    }

}
