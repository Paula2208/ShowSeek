package com.example.showseek;

public class StackRef {
    private Node top;
    public StackRef() {
        top = null;
    }
    // Push, añade un nuevo dato arriba de la pila
    public void push(int item) {
        Node newp = new Node(item);
        newp.setNext(top);
        top = newp;
    }
    // Pop, elimina y toma el dato de arriba de la pila
    public Object pop() {
        Object info = -1;
        if (!this.empty()) {
            info = top.getData();
            top = top.getNext();
        }
        return info;
    }
    // Está la pila vacia?
    public boolean empty() {
        return top == null;
    }
}