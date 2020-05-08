package ru.barancev.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name="addressbook")
public class ContactData {

    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Column(name="middlename")
    private String middlename;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    @Column(name="nickname")
    private String nickname;

    @Column(name="title")
    private String title;

    @Column(name="company")
    private String company;

    @Column(name="address")
    @Type(type="text")
    private String address;

    @Column(name="home")
    @Type(type="text")
    private String homePhone;

    @Transient
    private String group;

    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;

    @Column(name="work")
    @Type(type="text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name="email")
    @Type(type="text")
    private String email;

    @Column(name="email2")
    @Type(type="text")
    private String email2;

    @Column(name="email3")
    @Type(type="text")
    private String email3;

    @Column(name="photo")
    @Type(type="text")
    private String photo;
    //private File photo;






    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
//Лекция 5.4. Fluent-интерфейсы
//    public ContactData(String firstname, String middlename, String lastname, String nickname,
//                       String title, String company, String address, String homePhone,
//                       String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstname = firstname;
//        this.middlename = middlename;
//        this.lastname = lastname;
//        this.nickname = nickname;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.group = group;
//    }
//
//    public ContactData(int id, String firstname, String middlename, String lastname, String nickname,
//                       String title, String company, String address, String homePhone,
//                       String group) {
//        this.id = id;
//        this.firstname = firstname;
//        this.middlename = middlename;
//        this.lastname = lastname;
//        this.nickname = nickname;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.homePhone = homePhone;
//        this.group = group;
//    }

    @Override
    public String toString() {
        return "NewContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getGroup() {
        return group;
    }


    public String getAllPhones() {
        return allPhones;
    }


    public String getAllEmails() {
        return allEmails;
    }


    public String getEmail() {
        return email;
    }


    public String getEmail2() {
        return email2;
    }


    public String getEmail3() {
        return email3;
    }


    public File getPhoto() {
        return new File(photo);
    }


}
