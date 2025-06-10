package app.service;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Gala {
	private final ObservableMap<String, Person> persons = FXCollections.observableHashMap();
	public final ObservableMap<String, Group> groups = FXCollections.observableHashMap();
    private final List<Table> tables = new ArrayList<>();

    public Gala() {
        for (int i = 1; i <= 20; i++) tables.add(new Table(i, 12));
        for (int i = 21; i <= 30; i++) tables.add(new Table(i, 6));
    }

    // === AJOUTER ===
    public boolean ajouterPersonne(Person p) {
        return persons.putIfAbsent(p.getFullName().toLowerCase(), p) == null;
    }

    public boolean ajouterGroupe(Group g) {
        if (groups.containsKey(g.getGroupName().toLowerCase())) return false;
        g.getMembers().forEach(this::ajouterPersonne);
        groups.put(g.getGroupName().toLowerCase(), g);
        return true;
    }

    // === SUPPRIMER DU GALA ===
    public boolean supprimerPersonne(String fullName) {
        Person p = findPerson(fullName);
        if (p != null) {
            enleverPersonne(fullName);
            persons.remove(p.getFullName().toLowerCase());
            return true;
        }
        return false;
    }

    public boolean supprimerGroupe(String groupName) {
        Group g = groups.remove(groupName.toLowerCase());
        if (g != null) {
            g.getMembers().forEach(m -> {
                persons.remove(m.getFullName().toLowerCase());
            });
            enleverGroupe(groupName);
            return true;
        }
        return false;
    }

    // === ASSIGNER ===
    public boolean assignerPersonne(String fullName, int tableNum) {
        Person p = findPerson(fullName);
        if (p == null) return false;

        // Do not assign if already at a table
        if (getTableDe(fullName) != null) return false;

        Table t = getTableByNumber(tableNum);
        return t != null && t.addPerson(p);
    }


    public boolean assignerGroupe(String groupName, int tableNum) {
        Group g = groups.get(groupName.toLowerCase());
        if (g == null) return false;

        // Check if the group is already assigned by group name
        if (isGroupAssigned(groupName)) return false;

        // **New: check if any member is assigned individually or with another group**
        if (isAnyMemberAssigned(g)) return false;

        Table t = getTableByNumber(tableNum);
        return t != null && t.addGroup(g.getMembers(), groupName);
    }



    // === ENLEVER DE TABLE ===
    public boolean enleverPersonne(String fullName) {
        Person p = findPerson(fullName);
        if (p == null) return false;
        for (Table t : tables) {
            if (t.removePerson(p)) return true;
        }
        return false;
    }

    public boolean enleverGroupe(String groupName) {
        boolean success = false;
        for (Table t : tables) {
            success |= t.removeGroup(groupName);
        }
        return success;
    }

    // === DEPLACER ===
    public boolean deplacerPersonne(String fullName, int newTableNum) {
        Person p = findPerson(fullName);
        if (p == null) return false;
        Table newTable = getTableByNumber(newTableNum);
        if (newTable == null || newTable.getAvailableSeats() < 1 || newTable.getOccupants().contains(p))
            return false;

        enleverPersonne(fullName);
        return newTable.addPerson(p);
    }

    public boolean deplacerGroupe(String groupName, int newTableNum) {
        Group g = groups.get(groupName.toLowerCase());
        if (g == null) return false;

        Table newTable = getTableByNumber(newTableNum);
        List<Person> members = g.getMembers();

        if (newTable == null || newTable.getAvailableSeats() < members.size())
            return false;
        if (members.stream().anyMatch(newTable.getOccupants()::contains))
            return false;

        enleverGroupe(groupName);
        return newTable.addGroup(members, groupName);
    }

    // === AFFICHAGE / RECHERCHE ===
    public Table getTableDe(String fullName) {
        for (Table t : tables) {
            if (t.containsPerson(fullName)) return t;
        }
        return null;
    }

    public List<Table> getTablesAvecPlacesLibres() {
        List<Table> libres = new ArrayList<>();
        for (Table t : tables) {
            if (t.getAvailableSeats() > 0) libres.add(t);
        }
        return libres;
    }

    public List<Table> getTables() {
        return tables;
    }

    public ObservableMap<String, Person> getPersons() {
        return persons;
    }

    public ObservableMap<String, Group> getGroups() {
        return groups;
    }


    public void supprimerPlan() {
        tables.forEach(Table::clear);
    }

    // === UTILS ===
    public Table getTableByNumber(int num) {
        return tables.stream().filter(t -> t.getNumber() == num).findFirst().orElse(null);
    }
    
    private Person findPerson(String fullName) {
        if (fullName == null) return null;
        return persons.get(fullName.toLowerCase());
    }

    private boolean isGroupAssigned(String groupName) {
        for (Table t : tables) {
            if (t.hasGroup(groupName)) return true;
        }
        return false;
    }
    
    private boolean isAnyMemberAssigned(Group group) {
        for (Person member : group.getMembers()) {
            if (getTableDe(member.getFullName()) != null) {
                return true;
            }
        }
        return false;
    }
    
    public String getGroupOf(String personName) {
        String nameLower = personName.toLowerCase();
        for (Map.Entry<String, Group> entry : groups.entrySet()) {
            for (Person member : entry.getValue().getMembers()) {
                if (member.getFullName().equalsIgnoreCase(nameLower)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public Set<String> getGroupMembers(String groupName) {
        Group group = groups.get(groupName.toLowerCase());
        if (group == null) return Collections.emptySet();

        Set<String> memberNames = new HashSet<>();
        for (Person p : group.getMembers()) {
            memberNames.add(p.getFullName());
        }
        return memberNames;
    }
    
    public Table getTableOf(String fullName) {
        for (Table t : tables) {
            if (t.containsPerson(fullName)) return t;
        }
        return null;
    }

    
}
