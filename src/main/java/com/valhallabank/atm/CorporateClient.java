package com.valhallabank.atm;

public class CorporateClient extends Client {

    private String companyName;

    public CorporateClient() {
    }

    public CorporateClient(String idNumber, String name, double accountBalance, String companyName) {
        super(idNumber, name, accountBalance);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
