package com.travel.childern.model;

import java.util.List;

public class OrderAndContact {

    private Order order;

    private List<Contact> contacts;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
