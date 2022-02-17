package org.rishabh.models;

import org.rishabh.exceptions.NotValidTimeException;
import org.rishabh.interfaces.CalcOperationsInterface;
import org.rishabh.interfaces.ValidTimeInterface;

public class CalcOperationsImpl implements CalcOperationsInterface {

    private final ValidTimeInterface validTimeInterface;

    public CalcOperationsImpl(ValidTimeInterface validTimeInterface) {
        this.validTimeInterface = validTimeInterface;
    }

    @Override
    public void display(String name) {
        System.out.println("Welcome " + name + " to the calculator!");
    }

    @Override
    public int add(int num1, int num2) throws NotValidTimeException {
        if (!validTimeInterface.isValidTime()) {
            throw new NotValidTimeException();
        }
        return num1 + num2;
    }

    @Override
    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public long multiply(int num1, int num2) {
        return (long) num1 * num2;
    }

    @Override
    public double divide(int num1, int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException();
        }
        return (double) num1 / num2;
    }

}
