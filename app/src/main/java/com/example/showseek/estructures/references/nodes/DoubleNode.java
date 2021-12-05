package com.example.showseek.estructures.references.nodes;

public class DoubleNode<T extends Comparable<T>> extends Node<T> {

    //Atributes
    private DoubleNode<T> back;
    private DoubleNode<T> next;

    //Constructors
    public DoubleNode(T data){
        super.setData(data);
        back = null;
        next = null;
    }

    public DoubleNode(){
        super.setData(null);
        back = null;
        next = null;
    }

    //Methods
    public DoubleNode<T> getBack(){
        return back;
    }

    public void setBack(DoubleNode<T> back){
        this.back = back;
    }

    public DoubleNode<T> getNext(){
        return next;
    }

    public void setNext(DoubleNode<T> next){
        this.next = next;
    }

}
