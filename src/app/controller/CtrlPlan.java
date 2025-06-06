package app.controller;

import app.service.Plan;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    	for (int i = 0; i < plan.getColumnCount(); i++) {
    		for (int j = 0; j < plan.getRowCount(); j++) {
    	    	plan.add(new Button(), i, j);
    		}
    	}
    }
	
}
