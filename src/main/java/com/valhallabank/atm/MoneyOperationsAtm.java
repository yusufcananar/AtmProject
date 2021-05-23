package com.valhallabank.atm;

import com.valhallabank.exception.NegativeMoneyException;
import com.valhallabank.intrfc.MoneyOperations;

public class MoneyOperationsAtm implements MoneyOperations {

    public MoneyOperationsAtm() {
    }

    @Override
    public double withdrawMoney(double accountBalance, double withdrawQuantity) {
        try {

            if(withdrawQuantity <= 0){
                throw new NegativeMoneyException();
            }

            if (accountBalance > withdrawQuantity) {
                accountBalance = accountBalance - withdrawQuantity;
            }
            else if(accountBalance < withdrawQuantity){
                System.out.println("You cannot withdraw more money than your account.");
            }
        }
        catch (NegativeMoneyException e){
            System.out.println("CANNOT_WITHDRAW_LESS_THAN_ZERO::ERROR::" + e);
        }
        catch (Exception e){
            System.out.println("ERROR::" + e);
        }

        return accountBalance;
    }

    @Override
    public double depositMoney(double accountBalance, double depositQuantity) {
        try{
            if (depositQuantity <= 0){
                throw new NegativeMoneyException();
            }
            accountBalance = accountBalance + depositQuantity;
        }

        catch (NegativeMoneyException e){
            System.out.println("CANNOT_DEPOSIT_LESS_THAN_ZERO::ERROR::" + e);
        }
        catch (Exception e){
            System.out.println("ERROR::" + e);
        }

        return accountBalance;
    }
}
