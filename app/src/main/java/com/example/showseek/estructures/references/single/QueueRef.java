package com.example.showseek.estructures.references.single;

public class QueueRef<T extends Comparable<T>> extends LinkedList<T> {

    //Constructor
    public QueueRef(){
        super();
    }

    //Methods

    //Add to the tail of the linkedList
    public void enqueue(T item){
        super.pushBack(item);
    }

    //Delete from the head of the linkedList
    public T dequeue(){
        return super.popFront();
    }

    //Get the item at the head of the linkedList
    public T top(){
        return super.topFront();
    }

    //Get the item at the tail of the linkedList
    public T tail(){
        return super.topBack();
    }
}
