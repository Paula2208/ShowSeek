package com.example.showseek;

public class CircularQueue <T>{
    int read;
    int write;
    int size;
    T[] array;

    CircularQueue (int size) {
        read = -1;
        write = -1;
        this.size = size;
        array =(T[]) new Object[size];

    }

    CircularQueue () {
        size = 10000;
        read = -1;
        write = -1;
        this.size = size;
        array =(T[]) new Object[size];

    }

    boolean isEmpty() {
        if (read == -1){
            return true;
        }else {
            return false;
        }
    }

    boolean isFull(){
        if (read == 0 && write == size -1){
            return true;
        }
        if (read == write +1 ){
            return true;
        }
        return false;
    }

    String enQueue (T element){
        if (isFull()){
            return "Circular Queue is full";
        }else{
            if (read == -1){
                read = 0;
            }
            write = (write + 1) % size;
            array[write] = element;
            return "Element added to the Circular Queue: " + element;
        }
    }

    String deQueue() {
        T element;
        if (isEmpty()){
            return "Queue is empty" ;
        }else{
            element = array[read];
            if (read == write){
                read = -1;
                write = -1;
            }else{
                read = (read + 1) % size;
            }
            return "Dequeued: " + element;
        }
    }

    String display(){
        /*
        Function to display status of Circular Queue
         */
        int i;
        String display = "";
        if (isEmpty()){
            return "Empty Queue";
        } else{
            for (i = read; i!=write; i = (i+1) % size){
                display = display + array[i] + " ";
            }
            return "Items -> " + display;
        }
    }
}
