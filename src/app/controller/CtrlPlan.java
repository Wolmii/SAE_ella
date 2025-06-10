package app.controller;

import app.service.Gala;
import app.service.Person;
import app.service.Table;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

/**
 * Contrôleur principal pour l'affichage du plan de tables du gala.
 * Permet l'affichage graphique des tables sous forme d'icônes circulaires
 * et gère la recherche de personnes ainsi que les interactions utilisateur.
 */
public class CtrlPlan {

    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private GridPane grillePlan;
    @FXML private Label labelErreur;

    // Icônes pour les tables pleines ou disponibles
    private final Image iconeTablePleine = new Image(getClass().getResource("/table_full.png").toExternalForm());
    private final Image iconeTableDisponible = new Image(getClass().getResource("/table_not_full.png").toExternalForm());

    @FXML
    public void initialize() {
        afficherPlan();
    }

    private double saturationLevel = -0.25;

    private void afficherPlan() {
        grillePlan.getChildren().clear();

        List<Table> tables = Main.gala.getTables();
        List<Table> tablesDisponibles = Main.gala.getTablesAvecPlacesLibres();

        final int colonnes = 5;
        int ligne = 0, colonne = 0;

        for (Table table : tables) {
        	int number = table.getNumber();
            boolean disponible = tablesDisponibles.contains(table);

            ImageView icone = new ImageView();
            icone.setFitWidth(64);
            icone.setFitHeight(64);
            icone.setPreserveRatio(true);
            icone.setPickOnBounds(true);
            icone.setCursor(Cursor.HAND);

            // Bind the imageProperty based on table availability
            icone.imageProperty().bind(Bindings.createObjectBinding(
                () -> table.getAvailableSeats() > 0 ? iconeTableDisponible : iconeTablePleine,
        		table.availableSeatsProperty()
            ));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(saturationLevel);
            icone.setEffect(colorAdjust);

            icone.setOnMouseClicked(e -> ouvrirTable(number));

	         // Label for capacity overlay (e.g., "10/12")
            Label labelCapacite = new Label();
	
	         // Bind the text property to reflect live changes
	         labelCapacite.textProperty().bind(Bindings.createStringBinding(
	             () -> table.getOccupants().size() + "/" + table.getCapacity(),
	             table.getOccupantsMap() // ObservableMap triggers update when occupants change
	         ));

            labelCapacite.setStyle("""
                -fx-background-color: rgba(0, 0, 0, 0.6);
                -fx-text-fill: white;
                -fx-font-size: 10px;
                -fx-padding: 2px 4px;
                -fx-background-radius: 4px;
            """);
            StackPane.setAlignment(labelCapacite, Pos.BOTTOM_RIGHT); // Overlay position
            labelCapacite.setTranslateX(-80);  // shift right a bit inside icon
            labelCapacite.setTranslateY(-4); // shift up a bit inside icon

            // Label for table number (above icon)
            Label labelNumero = new Label("Table " + number);
            labelNumero.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
            labelNumero.setAlignment(Pos.CENTER);

            // Stack icon + capacity
            StackPane iconStack = new StackPane(icone, labelCapacite);
            iconStack.setAlignment(Pos.CENTER);

            // VBox for number + icon stack
            VBox vbox = new VBox(labelNumero, iconStack);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(4);

            // Final cell
            StackPane cellule = new StackPane(vbox);
            cellule.setPrefSize(80, 80);
            cellule.setAlignment(Pos.CENTER);

            grillePlan.add(cellule, colonne, ligne);

            colonne++;
            if (colonne >= colonnes) {
                colonne = 0;
                ligne++;
            }
        }
    }

    /**
     * Ouvre la vue d'une table spécifique.
     * @param numero Numéro de la table à ouvrir
     */
    private void ouvrirTable(int numero) {
        Main.ouvrirTable(numero);
    }

    /**
     * Affiche un message d’erreur temporaire.
     * Le message disparaît automatiquement après un court instant.
     * @param message Le texte de l'erreur à afficher
     */
    public void afficherErreur(String message) {
        if (labelErreur != null) {
            labelErreur.setText(message);
            labelErreur.setVisible(true);
            labelErreur.setOpacity(1.0);

            FadeTransition transition = new FadeTransition(Duration.seconds(1), labelErreur);
            transition.setFromValue(1.0);
            transition.setToValue(0.0);
            transition.setDelay(Duration.seconds(0.5));
            transition.setOnFinished(e -> labelErreur.setVisible(false));
            transition.play();
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton de recherche.
     * Tente de trouver une table correspondant au nom et prénom donnés.
     */
    @FXML
    private void rechercher(MouseEvent event) {
        Main.rechercheParNom(
        		(new Person(champNom.getText(), champPrenom.getText()))
        		.getFullName());
    }
}
