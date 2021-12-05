package com.example.showseek.estructures.array;

public class StackArray<T extends Comparable<T>> extends ListArray<T> {

    //Constructor
    public StackArray(){
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