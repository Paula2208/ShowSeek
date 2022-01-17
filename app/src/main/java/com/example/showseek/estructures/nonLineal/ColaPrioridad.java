package com.example.showseek.estructures.nonLineal;

public class ColaPrioridad<T extends Comparable<T>>extends Heap<T>{

    //Constructor
    public ColaPrioridad(){
        super();
    }

    //Metodos
    //Add to the tail of the linkedList
    public void enqueue(T item){
        super.add(item);
    }

    //Delete from the head of the linkedList
    public T dequeue(){
        return super.extractMax();
    }

    //Get the item at the head of the linkedList
    public T top(){
        return super.findMax();
    }
}
