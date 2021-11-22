package com.example.showseek;

public class Node {
    //Atributes
    private Object data;
    private Node next;
    private Node prev;

    //Constructors
    public Node(){
        this(0);
    }
    public Node(Object data) {
        this.data = data;
        next = null;
        prev = null;
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
        //Set Next Node
    public void setNext(Node next) {
        this.next = next;
    }

        //Prev Node associated
    public Node getPrev() {
        return prev;
    }
        //Set Prev Node
    public void setPrev(Node prev) {
        this.prev = prev;
    }
}