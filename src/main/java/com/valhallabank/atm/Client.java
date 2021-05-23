package com.valhallabank.atm;

public class Client {

    private String idNumber;
    private String name;
    protected double accountBalance;

    public Client() {
    }

    public Client(String idNumber, String name, double accountBalance) {
        this.idNumber = idNumber;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
