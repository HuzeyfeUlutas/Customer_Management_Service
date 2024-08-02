package com.hulutas.customer_management.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "TC No", unique = true, nullable = false, updatable = false, length = 11)
    private Long citizenNumber;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birth_date;
    @Column(name="is_active")
    private boolean isActive;

    public Customer() {
    }

    public Customer(UUID id, String name, String surname, int age, Long citizenNumber, LocalDate birth_date, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.citizenNumber = citizenNumber;
        this.birth_date = birth_date;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getCitizenNumber() {
        return citizenNumber;
    }

    public void setCitizenNumber(Long citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

}
