package app.ui;

import app.service.Person;
import app.service.Table;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonListCell extends ListCell<Person> {

    private final Table table;
    private final GroupColorProvider colorProvider;
    private final javafx.scene.control.ListView<Person> parentList;

    public PersonListCell(Table table, GroupColorProvider colorProvider, javafx.scene.control.ListView<Person> parentList) {
        this.table = table;
        this.colorProvider = colorProvider;
        this.parentList = parentList;

        this.addEventFilter(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
    }

    @Override
    protected void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (empty || person == null) {
            setText(null);
            setBackground(null);
            setTextFill(Color.BLACK);
            return;
        }
        setText(person.toString());

        Color baseColor = colorProvider.getColorForGroup(table.getGroupOf(person));
        if (isSelected()) {
            Color blended = blendColors(baseColor, Color.BLUE, 0.4);
            setBackground(new Background(new BackgroundFill(blended, CornerRadii.EMPTY, Insets.EMPTY)));
            setTextFill(Color.WHITE);
        } else {
            setBackground(new Background(new BackgroundFill(baseColor, CornerRadii.EMPTY, Insets.EMPTY)));
            setTextFill(Color.BLACK);
        }
    }

    private void handleMousePressed(MouseEvent event) {
        if (isEmpty() || table == null) return;
        Person clickedPerson = getItem();
        String groupName = table.getGroupOf(clickedPerson);
        var selectionModel = parentList.getSelectionModel();
        var items = parentList.getItems();

        event.consume();

        if (event.getButton() == MouseButton.PRIMARY) {
            if (groupName != null) {
                List<Person> groupMembers = table.getOccupantsMap().entrySet().stream()
                    .filter(e -> groupName.equals(e.getValue()) && items.contains(e.getKey()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
                groupMembers.forEach(p -> {
                    int idx = items.indexOf(p);
                    if (!selectionModel.isSelected(idx)) selectionModel.select(idx);
                });
            } else {
                int idx = items.indexOf(clickedPerson);
                if (!selectionModel.isSelected(idx)) selectionModel.select(idx);
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            if (groupName != null) {
                List<Person> groupMembers = table.getOccupantsMap().entrySet().stream()
                    .filter(e -> groupName.equals(e.getValue()) && items.contains(e.getKey()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
                groupMembers.forEach(p -> {
                    int idx = items.indexOf(p);
                    if (selectionModel.isSelected(idx)) selectionModel.clearSelection(idx);
                });
            } else {
                int idx = items.indexOf(clickedPerson);
                if (selectionModel.isSelected(idx)) selectionModel.clearSelection(idx);
            }
        }
    }

    private Color blendColors(Color base, Color blend, double alpha) {
        double r = base.getRed() * (1 - alpha) + blend.getRed() * alpha;
        double g = base.getGreen() * (1 - alpha) + blend.getGreen() * alpha;
        double b = base.getBlue() * (1 - alpha) + blend.getBlue() * alpha;
        double a = base.getOpacity() * (1 - alpha) + blend.getOpacity() * alpha;
        return new Color(r, g, b, a);
    }
}
