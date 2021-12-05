package com.example.showseek.estructures.references.doub;

public class DoubleStack<T extends Comparable<T>> extends DoubleLinkedList<T> {

    //Constructor
    public DoubleStack(){
        super();
    }

    //Methods

    //Add to the tail of the linkedList
    public void push(T item){
        super.pushBack(item);
    }

    //Delete from the tail of the linkedList
    public T pop(){
        T s = super.popBack();
        return s;
    }

    //Get the item at the tail of the linkedList
    public T top(){
        T s = super.topBack();
        return s;
    }

}
