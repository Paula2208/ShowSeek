package com.example.showseek;

public class Node {
    //Atributes
    private Object data;
    private Node next;

    //Constructors
    public Node(){
        this(0);
    }
    public Node(Object data) {
        this.data = data;
        next = null;
    }

    //Methods
        //Return the info in the Node
    public Object getData() {
        return data;
    }
        //Set the info in the Node
    public void setData(Object data) {
        this.data = data;
    }

    //Next Node associated
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}