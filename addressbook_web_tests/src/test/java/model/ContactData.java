package model;

public record ContactData(String firstName, String lastName, String address, String mobileTelephone, String email) {
    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withName(String name) {
        return new ContactData(name, this.lastName, this.address, this.mobileTelephone, this.email);
    }

    public ContactData withNameLastname(String name) {
        return new ContactData(name, lastName, this.address, this.mobileTelephone, this.email);
    }
}