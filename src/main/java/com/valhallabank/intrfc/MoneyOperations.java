package com.valhallabank.intrfc;

public interface MoneyOperations {

    double withdrawMoney(double accountBalance, double withdrawQuantity);
    double depositMoney(double accountBalance, double depositQuantity);
}
