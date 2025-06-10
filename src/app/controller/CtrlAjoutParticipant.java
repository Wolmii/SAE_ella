package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.service.Person;
import app.service.Table;
import app.ui.GroupColorProvider;
import app.ui.PersonListCell;   // reuse the same cell class from CtrlTable
import app.ui.SelectablePersonListCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CtrlAjoutParticipant {
	
    @FXML
    private TextField numTable;

    @FXML
    private RadioButton recherche;

    @FXML
    void annuler(ActionEvent event) {

    }
    
    private Table currentTable;
    
    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnAjouter;

    @FXML
    private ListView<Person> listPersonne;

    private ObservableList<Person> combinedList = FXCollections.observableArrayList();
    private SortedList<Person> sortedList;
    private GroupColorProvider colorProvider;

    @FXML
    public void initialize() {
        listPersonne.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colorProvider = new GroupColorProvider();

        // Wrap combinedList with sorting by group and name (like CtrlTable)
        sortedList = new SortedList<>(combinedList);
        sortedList.setComparator((p1, p2) -> {
            String group1 = Main.gala.getGroupOf(p1.getFullName());
            String group2 = Main.gala.getGroupOf(p2.getFullName());

            if (group1 == null && group2 == null)
                return p1.getFullName().compareToIgnoreCase(p2.getFullName());
            if (group1 == null)
                return 1;
            if (group2 == null)
                return -1;

            int groupCompare = group1.compareToIgnoreCase(group2);
            if (groupCompare != 0)
                return groupCompare;

            return p1.getFullName().compareToIgnoreCase(p2.getFullName());
        });

        listPersonne.setItems(sortedList);

        // Use same PersonListCell as in CtrlTable for consistent group coloring and style
        listPersonne.setCellFactory(lv -> new SelectablePersonListCell(colorProvider, listPersonne));

    }

    private void setupButtonBindings() {
        if (currentTable != null) {
            bnAjouter.disableProperty().bind(
                currentTable.availableSeatsProperty().isEqualTo(0)
                .or(Bindings.size(listPersonne.getSelectionModel().getSelectedItems()).isEqualTo(0))
            );
        } else {
            bnAjouter.setDisable(true);
        }
    }

    public void refreshList() {
        combinedList.clear();

        var allPersons = Main.gala.getPersons().values();
        var unassignedPersons = new ArrayList<Person>();

        for (Person person : allPersons) {
            Table table = Main.gala.getTableOf(person.getFullName());

            // Add person only if they are not assigned to any table
            if (table == null) {
                unassignedPersons.add(person);
            }
        }

        combinedList.addAll(unassignedPersons);
    }


    @FXML
    void ajouter(ActionEvent event) {
        Table table = Main.gala.getTableByNumber(Integer.parseInt(numTable.getText()));
    	System.out.println(table.getAvailableSeats());
    	System.out.println(table.getOccupantsMap());
        if (table == null) return;

        List<Person> selectedPersons = new ArrayList<>(listPersonne.getSelectionModel().getSelectedItems());

        int seatsNeeded = selectedPersons.size();
        int seatsAvailable = table.getAvailableSeats();

        if (seatsNeeded > seatsAvailable) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas assez de places");
            alert.setContentText("Le nombre de personnes sélectionnées dépasse les places disponibles à la table.");
            alert.showAndWait();
            return;  // cancel adding
        }

        // Assign whole groups at once
        List<String> addedGroups = new ArrayList<>();
        for (Person p : selectedPersons) {
            String group = Main.gala.getGroupOf(p.getFullName());
            if (group != null && !addedGroups.contains(group.toLowerCase())) {
                addedGroups.add(group.toLowerCase());
                Main.gala.assignerGroupe(group, table.getNumber());
            } else if (group == null) {
                Main.gala.assignerPersonne(p.getFullName(), table.getNumber());
            }
        }
        listPersonne.getSelectionModel().clearSelection();
    	System.out.println(table.getAvailableSeats());
    	System.out.println(table.getOccupantsMap());

        Main.fermerAjouter();
    }

    public void setTable(Table table) {
        this.currentTable = table;

        if (table != null) {
            numTable.setText(String.valueOf(table.getNumber()));
            numTable.setDisable(true); // Disable manual editing
        } else {
            numTable.setText(""); // or some placeholder
            numTable.setDisable(false); // Enable editing if no table is set
        }

        refreshList();
        listPersonne.getSelectionModel().clearSelection();
        setupButtonBindings();
    }
    
}
