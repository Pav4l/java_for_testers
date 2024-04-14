package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String middleName ="";
    public String lastname;
    public String nickname="";
    public String title="";

    public String company="";
    public String address;
    public String home;
    public String mobile;
    public String work;
    public String phone2;

    @Column(name = "fax")
    public String faxTelephone = "";
    public String email;
    public String email2;
    public String email3;
    public String homepage = "";
    //public String photo = "";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address, String mobile, String email/*, String photo*/) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        //this.photo = photo;
    }
}
