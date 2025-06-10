package app.service;

import java.util.*;

public class Group {
    private final String groupName;
    private final List<Person> members;

    public Group(String groupName, List<Person> members) {
        this.groupName = groupName.trim();
        this.members = new ArrayList<>(members);
    }

    public String getGroupName() {
        return groupName.toLowerCase();
    }

    public List<Person> getMembers() {
        return Collections.unmodifiableList(members);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getGroupName().equals(group.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }

    @Override
    public String toString() {
        return groupName + " (" + members.size() + " members)";
    }
}
