package com.example.foodrestaurent;

public class customer {
    private int customer_id;
    private String email;
    private String password;

    public customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "customer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
