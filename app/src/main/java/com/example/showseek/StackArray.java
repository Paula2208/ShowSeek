package com.example.showseek;

public class StackArray {
    private static final int N = 3;
    private int top, sarray[];
    // Constructor
    public StackArray() {
        this(N);
    }
    public StackArray(int n) {
        top = 0;
        sarray = new int[n];
    }
    // Vacia?
    public boolean empty() {
        return top <= 0;
    }
    // Llena?
    public boolean full() {
        return top >= sarray.length;
    }
    // Pop, saca y toma el ultimo dato ingresado
    public int pop() {
        if(empty())
            throw new RuntimeException("Stack is empty");
        top--;
        return sarray[top];
    }
    // Push, añade un elemento arriba de la pila
    public void push(int item) {
        if(full())
            throw new RuntimeException("Stack is full");
        sarray[top]=item;
        top++;
    }
}