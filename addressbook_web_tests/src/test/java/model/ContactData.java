package model;

public record ContactData(String id, String firstName, String lastName, String address, String mobileTelephone, String email, String photo) {
    public ContactData() {
        this("", "", "", "", "", "","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo);
    }

    public ContactData withName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.mobileTelephone, this.email, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.mobileTelephone, this.email, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.mobileTelephone, this.email, this.photo);
    }

    public ContactData withPhone(String mobileTelephone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, mobileTelephone, this.email, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, email, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.mobileTelephone, this.email, photo);
    }

    public ContactData withNameLastname(String firstName, String lastName) {
        return new ContactData(this.id, firstName, lastName, this.address, this.mobileTelephone, this.email, this.photo);
    }
}