package app.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private static final String[] FIRST_NAMES = {
        "Zorg", "Xan", "Lumo", "Brak", "Nara", "Fipo", "Vex", "Julo", "Tiq", "Drel",
        "Sina", "Rulo", "Pex", "Vala", "Kri", "Mina", "Grop", "Fala", "Tano", "Leki"
    };
    private static final String[] LAST_NAMES = {
        "Zan", "Xep", "Lorl", "Brim", "Nex", "Fiz", "Vrak", "Jul", "Til", "Dran",
        "Sin", "Rus", "Pik", "Valo", "Krim", "Min", "Grox", "Fap", "Tan", "Lek"
    };

    private static String randomName(String[] names) {
        return names[ThreadLocalRandom.current().nextInt(names.length)];
    }

    // Generate a unique person checking if they already exist in gala
    private static Person generateUniquePerson(Gala gala) {
        String first, last, fullName;
        int suffix = 0;
        do {
            first = randomName(FIRST_NAMES);
            last = randomName(LAST_NAMES);
            String lastWithSuffix = last + (suffix > 0 ? "#" + suffix : "");
            fullName = (first + " " + lastWithSuffix).toLowerCase();
            suffix++;
        } while (gala.getPersons().get(fullName) != null);
        return new Person(first, last + (suffix > 1 ? "#" + (suffix-1) : ""));
    }

    // Generate a group where all members share the same last name, checking uniqueness for each person
    private static Group generateUniqueGroup(Gala gala, String groupName, int size) {
        List<Person> members = new ArrayList<>();
        String sharedLastName = randomName(LAST_NAMES);
        for (int i = 0; i < size; i++) {
            String first;
            String fullName;
            int suffix = 0;
            do {
                first = randomName(FIRST_NAMES);
                String lastWithSuffix = sharedLastName + (suffix > 0 ? "#" + suffix : "");
                fullName = (first + " " + lastWithSuffix).toLowerCase();
                suffix++;
            } while (gala.getPersons().get(fullName) != null);
            members.add(new Person(first, sharedLastName + (suffix > 1 ? "#" + (suffix-1) : "")));
        }
        return new Group(groupName, members);
    }

    public static Gala makeGala() {
        Gala gala = new Gala();
        Random rnd = new Random();

        // Seed fixed individuals
        gala.ajouterPersonne(new Person("Jean", "Dupont"));
        gala.ajouterPersonne(new Person("Claire", "Martin"));

        // Assign Jean Dupont alone at table 1
        gala.assignerPersonne("Jean Dupont", 1);

        for (Table table : gala.getTables()) {
            int tableNum = table.getNumber();
            int capacity = table.getAvailableSeats();

            // 30% chance to leave empty
            if (rnd.nextInt(100) < 30) continue;

            boolean assignGroup = rnd.nextBoolean();

            if (assignGroup && capacity >= 2) {
                int maxGroupSize = Math.min(capacity, 6);
                int groupSize = 2 + rnd.nextInt(maxGroupSize - 1);

                if (capacity >= groupSize) {
                    String groupName = "Groupe-" + tableNum;
                    Group group = generateUniqueGroup(gala, groupName, groupSize);
                    gala.ajouterGroupe(group);
                    gala.assignerGroupe(groupName, tableNum);

                    // Fill remaining seats with individuals
                    int remainingSeats = gala.getTableByNumber(tableNum).getAvailableSeats();
                    for (int i = 0; i < remainingSeats; i++) {
                        if (table.getAvailableSeats() <= 0) break;
                        Person p = generateUniquePerson(gala);
                        gala.ajouterPersonne(p);
                        boolean assigned = gala.assignerPersonne(p.getFullName(), tableNum);
                        if (!assigned) break;
                    }
                    continue; // go to next table
                }
            }
            
            // Assign individuals only
            int seatsToFill = Math.min(capacity, 6);
            for (int i = 0; i < seatsToFill; i++) {
                if (table.getAvailableSeats() <= 0) break;
                Person p = generateUniquePerson(gala);
                gala.ajouterPersonne(p);
                boolean assigned = gala.assignerPersonne(p.getFullName(), tableNum);
                if (!assigned) break;
            }
        }
        
        gala.ajouterGroupe(generateUniqueGroup(gala, "grrr", 4));
        gala.ajouterGroupe(generateUniqueGroup(gala, "zebfez", 4));
        gala.ajouterGroupe(generateUniqueGroup(gala, "mezemz", 4));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        gala.ajouterPersonne(generateUniquePerson(gala));
        System.out.println(gala.getGroupMembers("grrr"));
        System.out.println(gala.getGroupMembers("zebfez"));
        System.out.println(gala.getGroupMembers("mezemz"));

        // Debug output
        for (Table t : gala.getTables()) {
            System.out.println("[Table " + t.getNumber() + "] Occupants: " + t.getOccupants());
        }
        System.out.println("[🧾] Total registered persons: " + gala.getPersons().size());
        int totalSeated = 0;
        for (Table t : gala.getTables()) {
            totalSeated += t.getOccupants().size();
        }
        System.out.println("[🪑] Total persons seated at tables: " + totalSeated);
        System.out.println("[🧾] Total groups: " + gala.groups.size());

        return gala;
    }
}