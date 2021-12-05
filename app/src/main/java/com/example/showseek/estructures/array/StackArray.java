package com.example.showseek.estructures.array;

public class StackArray<T> {

    //Atributes
    private int sizeT = 1000;
    private String message ="";
    private int top;
    private T[] sarray;

    // Constructors
    public StackArray() {
        top = 0;
        sarray =(T[]) new Object[sizeT];
    }

    public StackArray(int sizeT) {
        top = 0;
        this.sizeT = sizeT;
        sarray =(T[]) new Object[sizeT];
    }

    //Methods
        // Is Stack empty?
    public boolean empty() {
        return top <= 0;
    }

        // Is Stack full?
    public boolean full() {
        return top >= sarray.length;
    }

        // Delete data at the top of stack
    public T pop() {
        T item = null;
        if(empty()){
            message = "Stack is empty: no items to pop";
        }
        else{
            top--;
            item = sarray[top];
            message = "Element deleted: " + item ;
        }
        return item;
    }

        // Add data at the top of the stack
    public void push(T item) {
        if(full()){
            message = "Stack is full: Item not added";
        }
        else{
            sarray[top] = item;
            top++;
            message = "Element added: " + item ;
        }
    }
}