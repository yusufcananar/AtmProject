package com.valhallabank.exception;

public class NegativeMoneyException extends Exception {

    @Override
    public String getMessage(){
        return "Negative Money Exception";
    }
}
