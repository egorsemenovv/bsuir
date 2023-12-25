package org.example;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private final String name;
    private final int health;

    public Person(String name, int health){
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.health, o.getHealth());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return health == person.health && Objects.equals(name, person.name);
    }

}
