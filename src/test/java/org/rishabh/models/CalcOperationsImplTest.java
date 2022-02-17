package org.rishabh.models;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.rishabh.exceptions.NotValidTimeException;
import org.rishabh.interfaces.CalcOperationsInterface;
import org.rishabh.interfaces.ValidTimeInterface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalcOperationsImplTest {

    @Mock
    CalcOperationsInterface calcOperationsInterface;

    @Mock
    ValidTimeInterface validTimeInterface;

    @InjectMocks
    CalcOperationsImpl calcOperationsImpl = new CalcOperationsImpl(validTimeInterface);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void add() throws NotValidTimeException {
        int num1 = 1, num2 = 2;
        int expected = 3;

        when(validTimeInterface.isValidTime()).thenReturn(true);
        calcOperationsImpl = new CalcOperationsImpl(validTimeInterface);

        when(calcOperationsInterface.add(num1, num2)).thenReturn(expected);

        assertEquals(expected, calcOperationsImpl.add(num1, num2));
    }

    @Test(expected = NotValidTimeException.class)
    public void addWhenNotValid() throws NotValidTimeException {
        int num1 = 1, num2 = 2;

        when(validTimeInterface.isValidTime()).thenReturn(false);
        calcOperationsImpl = new CalcOperationsImpl(validTimeInterface);

        when(calcOperationsInterface.add(num1, num2)).thenThrow(new NotValidTimeException());

        calcOperationsImpl.add(num1, num2);
    }

    @Ignore
    @Test
    public void subtract() {
        int num1 = 4, num2 = 2;
        int expected = 2;

        when(calcOperationsInterface.subtract(num1, num2)).thenReturn(expected);

        assertEquals(expected, calcOperationsImpl.subtract(num1, num2));
    }

    @Test
    public void multiply() {
        int num1 = 3, num2 = 2;
        long expected = 6;

        when(calcOperationsInterface.multiply(num1, num2)).thenReturn(expected);

        assertEquals(expected, calcOperationsImpl.multiply(num1, num2));
    }

    @Test
    public void divideNormal() {
        int num1 = 7, num2 = 2;
        double expected = 3.5;

        when(calcOperationsInterface.divide(num1, num2)).thenReturn(expected);

        assertThat(calcOperationsImpl.divide(num1, num2), equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideThrowsException() {
        int num1 = 4, num2 = 0;

        when(calcOperationsInterface.divide(num1, num2)).thenThrow(new IllegalArgumentException());

        calcOperationsImpl.divide(num1, num2);
    }

    @Test
    public void divideThrowsException_New() {
        final int num1 = 4, num2 = 0;

        doThrow(new IllegalArgumentException()).when(calcOperationsInterface).divide(num1, num2);

        assertThrows(IllegalArgumentException.class, () -> {
           calcOperationsImpl.divide(num1, num2);
        });
    }

    @Test
    public void display() {
        String name = "Rishabh";

        doAnswer(invocation -> {
            Object arg0 = invocation.getArguments()[0];

            assertEquals(name, arg0.toString());
            return null;
        }).when(calcOperationsInterface).display(any(String.class));

        calcOperationsImpl.display(name);
    }

    @Test
    public void display_New() {
        String name = "Rishabh";
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        CalcOperationsImpl calcOperationsImplMock = mock(CalcOperationsImpl.class);

        doNothing().when(calcOperationsImplMock).display(valueCapture.capture());

        calcOperationsImplMock.display(name);

        assertEquals(name, valueCapture.getValue());

    }
}