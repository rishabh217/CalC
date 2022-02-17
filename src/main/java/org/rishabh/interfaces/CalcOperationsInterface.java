package org.rishabh.interfaces;

import org.rishabh.exceptions.NotValidTimeException;

public interface CalcOperationsInterface {
    void display(String name);
    int add(int num1, int num2) throws NotValidTimeException;
    int subtract(int num1, int num2);
    long multiply(int num1, int num2);
    double divide(int num1, int num2);
}
