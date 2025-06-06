package app.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class CtrlPlan {

	@FXML
    private GridPane plan;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    void recherche(ActionEvent event) {
    	
    }
    

    public void initialize() {
    	for (Node cas : plan.getChildren()) {
    		System.out.println(cas);
    	}
    }
	
}
