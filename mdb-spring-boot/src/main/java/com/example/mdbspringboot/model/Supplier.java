package com.example.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "suppliers")
public class Supplier {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("company_name")
    private String companyName;

    @Field("contact_email")
    private String contactEmail;

    @Field("phone")
    private String phone;

    public Supplier(String id, String companyName, String contactEmail, String phone) {
        this.id = id;
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}