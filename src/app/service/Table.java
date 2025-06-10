package app.service;

import java.util.*;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class Table {
    private final int number;
    private final int capacity;

    // Map of person -> groupName (null if ungrouped)
    private final ObservableMap<Person, String> occupants = FXCollections.observableHashMap();

    // Observable property for available seats
    private final ReadOnlyIntegerWrapper availableSeats = new ReadOnlyIntegerWrapper();

    public Table(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;

        // Initialize availableSeats with capacity at start
        updateAvailableSeats();

        // Add listener to occupants to update availableSeats when occupants change
        occupants.addListener((MapChangeListener<Person, String>) change -> {
            updateAvailableSeats();
        });
    }

    private void updateAvailableSeats() {
        availableSeats.set(capacity - occupants.size());
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Person> getOccupants() {
        return Collections.unmodifiableSet(occupants.keySet());
    }

    public ObservableMap<Person, String> getOccupantsMap() {
        return occupants;
    }

    // New observable property getter
    public ReadOnlyIntegerProperty availableSeatsProperty() {
        return availableSeats.getReadOnlyProperty();
    }

    public int getAvailableSeats() {
        return availableSeats.get();
    }

    public boolean addPerson(Person p) {
        if (occupants.containsKey(p) || getAvailableSeats() <= 0) return false;
        occupants.put(p, null); // ungrouped
        return true;
    }

    public boolean addGroup(List<Person> group, String groupName) {
        if (getAvailableSeats() < group.size()) return false;
        if (group.stream().anyMatch(occupants::containsKey)) return false;
        group.forEach(p -> occupants.put(p, groupName));
        return true;
    }

    public boolean removePerson(Person p) {
        return occupants.remove(p) != null;
    }

    public boolean removeGroup(String groupName) {
        boolean modified = false;
        Iterator<Map.Entry<Person, String>> it = occupants.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            if (groupName.equals(entry.getValue())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean containsPerson(String fullName) {
        return occupants.keySet().stream()
                .anyMatch(p -> p.getFullName().equalsIgnoreCase(fullName));
    }

    public boolean hasGroup(String groupName) {
        return occupants.values().stream()
                .anyMatch(name -> groupName.equalsIgnoreCase(name));
    }

    public Person getPersonByFullName(String fullName) {
        return occupants.keySet().stream()
                .filter(p -> p.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }

    public String getGroupOf(Person p) {
        return occupants.get(p); // may be null
    }

    public void clear() {
        occupants.clear();
    }

    @Override
    public String toString() {
        return "Table " + number + " (" + occupants.size() + "/" + capacity + ")";
    }
}
