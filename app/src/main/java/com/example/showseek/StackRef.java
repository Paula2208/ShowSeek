package com.example.showseek;

public class StackRef {

    //Atributes
    private Node top;
    private String message ="";

    //Constructor
    public StackRef() {
        top = null;
    }

    //Methods
        // Add data at front of stack
    public void push(Object item) {
        Node newp = new Node(item);
        newp.setNext(top);
        top = newp;
        message ="Element added : " + item;
    }

        // Delete data at front of stack
    public Object pop() {
        Object info = null;
        if (!this.empty()) {
            info = top.getData();
            top = top.getNext();
        }
        return info;
    }

        //Stack is empty?
    public boolean empty() {
        return top == null;
    }
}