package ru.job4j.design.srp;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDate hired;
    private LocalDate fired;
    private double salary;

    public Employee(String name, LocalDate hired, LocalDate fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHired() {
        return hired;
    }

    public void setHired(LocalDate hired) {
        this.hired = hired;
    }

    public LocalDate getFired() {
        return fired;
    }

    public void setFired(LocalDate fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employer = (Employee) o;
        return Objects.equals(name, employer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
