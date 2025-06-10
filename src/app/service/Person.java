package app.service;

public class Person {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public String getFullName() {
        return (firstName + " " + lastName).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return this.getFullName().equalsIgnoreCase(p.getFullName());
    }

    @Override
    public int hashCode() {
        return this.getFullName().toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
