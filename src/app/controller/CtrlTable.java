package app.controller;

import app.service.Person;
import app.service.Table;
import app.service.Gala;
import app.ui.GroupColorProvider;
import app.ui.PersonListCell;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CtrlTable {

    @FXML
    private Button bnSupprimer, bnDeplacer, bnAjouter,bnEnlever;

    @FXML
    public Label lblTable;

    @FXML
    public ListView<Person> listPersonnes;

    private Table table;
    private Gala gala = Main.gala; // Assuming Main.gala is accessible here

    private GroupColorProvider colorProvider;

    @FXML
    public void initialize() {
        listPersonnes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colorProvider = new GroupColorProvider();
    }

    public void setTable(Table table) {
        this.table = table;

        ObservableMap<Person, String> occupantsMap = table.getOccupantsMap();

        ObservableList<Person> observableList = FXCollections.observableArrayList(occupantsMap.keySet());

        occupantsMap.addListener((MapChangeListener<Person, String>) change -> {
            if (change.wasRemoved()) observableList.remove(change.getKey());
            if (change.wasAdded()) observableList.add(change.getKey());
        });

        SortedList<Person> sortedList = new SortedList<>(observableList);
        sortedList.setComparator((p1, p2) -> {
            String group1 = table.getGroupOf(p1);
            String group2 = table.getGroupOf(p2);

            if (group1 == null && group2 == null) {
                // Neither in a group, compare by name
                return p1.getFullName().compareToIgnoreCase(p2.getFullName());
            }
            if (group1 == null) return 1;  // Put persons without group after those with group
            if (group2 == null) return -1;

            int groupCmp = group1.compareToIgnoreCase(group2);
            if (groupCmp != 0) return groupCmp;

            // Same group: sort by name
            return p1.getFullName().compareToIgnoreCase(p2.getFullName());
        });


        listPersonnes.setItems(sortedList);

        listPersonnes.setCellFactory(lv -> new PersonListCell(table, colorProvider, listPersonnes));

        lblTable.setText("Table " + table.getNumber());

        setupButtonBindings();
    }

    private void setupButtonBindings() {
        // Disable buttons depending on selection or state
    	bnAjouter.disableProperty().bind(
    		    table.availableSeatsProperty().isEqualTo(0)
    		);
        bnEnlever.disableProperty().bind(Bindings.size(listPersonnes.getSelectionModel().getSelectedItems()).isEqualTo(0));
        bnSupprimer.disableProperty().bind(Bindings.size(listPersonnes.getSelectionModel().getSelectedItems()).isEqualTo(0));
        bnDeplacer.disableProperty().bind(Bindings.size(listPersonnes.getSelectionModel().getSelectedItems()).isEqualTo(0));

    }

    @FXML
    private void ajouter(ActionEvent event) {
    	Main.ouvrirAjouter(table);
    }

    @FXML
    private void enlever(ActionEvent event) {
        List<Person> selected = new ArrayList<>(listPersonnes.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            ArrayList<String> groups = new ArrayList<>();
            for (Person p : selected) {
                String group = table.getGroupOf(p);
                if (group != null && !groups.contains(group)) {
                    groups.add(group);
                    gala.enleverGroupe(group);
                } else if (group == null) {
                    gala.enleverPersonne(p.getFullName());
                }
            }
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        List<Person> selected = new ArrayList<>(listPersonnes.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            ArrayList<String> groups = new ArrayList<>();
            for (Person p : selected) {
                String group = table.getGroupOf(p);
                if (group != null && !groups.contains(group)) {
                    groups.add(group);
                    gala.supprimerGroupe(group);
                } else if (group == null) {
                    gala.supprimerPersonne(p.getFullName());
                }
            }
        }
    }

    @FXML
    private void deplacer(ActionEvent event) {
        List<Person> selected = new ArrayList<>(listPersonnes.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Déplacer");
            dialog.setHeaderText("Déplacer la personne");
            dialog.setContentText("Entrez le numéro de la table où déplacer:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    int newTableNumber = Integer.parseInt(result.get());
                    if (selected.size() > gala.getTableByNumber(newTableNumber).getAvailableSeats()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Table pleine");
                        alert.setContentText("Veuillez entrer un numero de table non pleine");
                        alert.showAndWait();
                        return;
                    }

                    ArrayList<String> groups = new ArrayList<>();
                    for (Person p : selected) {
                        String group = table.getGroupOf(p);
                        if (group != null && !groups.contains(group)) {
                            groups.add(group);
                            gala.deplacerGroupe(group, newTableNumber);
                        } else if (group == null) {
                            gala.deplacerPersonne(p.getFullName(), newTableNumber);
                        }
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Numéro invalide");
                    alert.setContentText("Veuillez entrer un nombre entier valide.");
                    alert.showAndWait();
                }
            }
        }
    }
}
