package com.example.showseek;

public class QueueArray<T> {

    //Atributes
    public int sizeT = 1000;
    public int front;
    public int rear;
    public int csize;
    public String message ="";
    public T[] array;

    //constructors
    public QueueArray(){
        front = 0;
        rear = 0;
        csize = 0;
        array =(T[]) new Object[sizeT];
    }

    public QueueArray(int sizeT){
        front = 0;
        rear = 0;
        csize = 0;
        this.sizeT = sizeT;
        array =(T[]) new Object[sizeT];
    }

    public QueueArray(int front, int rear){
        this.front = front;
        this.rear = rear;
        csize = 0;
        array =(T[]) new Object[sizeT];
    }

    public QueueArray(int front, int rear, int sizeT){
        this.front = front;
        this.rear = rear;
        csize = 0;
        this.sizeT = sizeT;
        array =(T[]) new Object[sizeT];
    }

    //Add a new element
    public void enqueue(T element){

        if(full()){
            message ="The Queue is full: element not enqueued";
        }
        else{
            message ="Element added : " + element;
            array[rear] = element;
            rear = (rear+1)%sizeT;
            csize++;
        }
    }

    //Delete an element
    public T dequeue(){

        T element = null;

        if(empty()){
            message ="The Queue is empty: element not dequeued";
        }
        else{
            element = array[front];
            front = (front+1)%sizeT;
            csize--;
        }

        return element;
    }

    //Is the Queue empty?
    public boolean empty(){
        return csize <= 0;
    }

    //Is the Queue full?
    public boolean full(){
        return csize >= sizeT;
    }

    //Information
    public String getMessage(){
        return this.message;
    }

    //Number of elements on queue
    public int getSize(){
        return this.csize;
    }

}
