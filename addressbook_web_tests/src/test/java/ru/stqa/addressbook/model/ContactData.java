package ru.stqa.addressbook.model;

public record ContactData(String id,
                          String firstName,
                          String lastName,
                          String address,
                          String mobileTelephone,
                          String email,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String secondary) {
    public ContactData() {
        this("", "", "", "", "", "","", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhone(String mobileTelephone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, "", this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, home, this.mobile, this.work, this.secondary);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, mobile, this.work, this.secondary);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, work, this.secondary);
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, secondary);
    }

    public ContactData withNameLastname(String firstName, String lastName) {
        return new ContactData(this.id, firstName, lastName, this.address, this.mobileTelephone, this.email, this.photo, this.home, this.mobile, this.work, this.secondary);
    }
}