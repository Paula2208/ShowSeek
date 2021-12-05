package com.example.showseek.estructures.references.doub;

public class DoubleQueue<T extends Comparable<T>> extends DoubleLinkedList<T> {

    //Constructor
    public DoubleQueue(){

        super();
    }

    //Methods

    //Add to the tail of the linkedList
    public void enqueue(T item){
        super.pushBack(item);
    }

    //Delete from the head of the linkedList
    public T dequeue(){
        T s = super.popFront();
        return s;
    }

    //Get the item at the head of the linkedList
    public T top(){
        T s = super.topFront();
        return s;
    }

    //Get the item at the tail of the linkedList
    public T tail(){
        T s = super.topBack();
        return s;
    }
}
