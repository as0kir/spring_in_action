package com.apress.prospring4.ch8.entities;

import com.apress.prospring4.ch8.entities.ContactTelDetail;
import com.apress.prospring4.ch8.entities.Hobby;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contact")
@NamedQueries({
        @NamedQuery(name = "Contact.findAll",
                query = "select c from com.apress.prospring4.ch8.entities.Contact c"),
        @NamedQuery(name = "Contact.findById",
                query = "select distinct c from com.apress.prospring4.ch8.entities.Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
        @NamedQuery(name = "Contact.findAllWithDetail",
                query = "select distinct c from com.apress.prospring4.ch8.entities.Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
@SqlResultSetMapping(
        name = "contactResult",
        entities = @EntityResult(entityClass = Contact.class)
)
public class Contact implements Serializable {
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<com.apress.prospring4.ch8.entities.ContactTelDetail> contactTelDetails = new HashSet<com.apress.prospring4.ch8.entities.ContactTelDetail>();
    private Set<com.apress.prospring4.ch8.entities.Hobby> hobbies = new HashSet<com.apress.prospring4.ch8.entities.Hobby>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<com.apress.prospring4.ch8.entities.ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    public void setContactTelDetails(Set<com.apress.prospring4.ch8.entities.ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }

    public void addContactTelDetail(com.apress.prospring4.ch8.entities.ContactTelDetail contactTelDetail){
        contactTelDetail.setContact(this);
        getContactTelDetails().add(contactTelDetail);
    }

    public void removeContactTelDetail(com.apress.prospring4.ch8.entities.ContactTelDetail contactTelDetail){
        getContactTelDetails().remove(contactTelDetail);
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "CONTACT_ID"), inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
    public Set<com.apress.prospring4.ch8.entities.Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<com.apress.prospring4.ch8.entities.Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
