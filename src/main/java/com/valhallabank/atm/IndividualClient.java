package com.valhallabank.atm;

public class IndividualClient extends Client {

    private String address;

    public IndividualClient() {
    }

    public IndividualClient(String idNumber, String name, double accountBalance, String address) {
        super(idNumber, name, accountBalance);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
