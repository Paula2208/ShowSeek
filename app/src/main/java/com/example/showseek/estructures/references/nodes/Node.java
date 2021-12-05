package com.example.showseek.estructures.references.nodes;

public class Node<T extends Comparable<T>> {

    //Atributes
    private T data;
    private Node<T> next;

    //Constructors
    public Node(T data){
        this.data = data;
        next = null;
    }

    public Node(){
        data = null;
        next = null;
    }

    //Methods
    public void setData(T data){
        this.data = data;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public T getData(){
        return data;
    }

    public Node<T> getNext(){
        return next;
    }

}