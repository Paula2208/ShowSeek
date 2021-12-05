package com.example.showseek.estructures.references.single;

public class StackRef<T extends Comparable<T>> extends LinkedList<T> {

    //Constructor
    public StackRef(){
        super();
    }

    //Methods

    //Add to the tail of the linkedList
    public void push(T item){
        super.pushBack(item);
    }

    //Delete from the tail of the linkedList
    public T pop(){
        return super.popBack();
    }

    //Get the item at the tail of the linkedList
    public T top(){
        return super.topBack();
    }

}