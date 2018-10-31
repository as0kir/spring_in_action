package com.apress.prospring4.ch8.spring_data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hobby")
public class Hobby implements Serializable {
    private String hobbyId;
    private Set<com.apress.prospring4.ch8.spring_data.entities.Contact> contacts = new HashSet<com.apress.prospring4.ch8.spring_data.entities.Contact>();

    @Id
    @Column(name = "HOBBY_ID")
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "HOBBY_ID"), inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    public Set<com.apress.prospring4.ch8.spring_data.entities.Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<com.apress.prospring4.ch8.spring_data.entities.Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobbyId='" + hobbyId + '\'' +
                '}';
    }
}
