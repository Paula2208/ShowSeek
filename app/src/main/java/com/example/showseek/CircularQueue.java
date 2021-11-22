package com.example.showseek;

public class CircularQueue {
    int read;
    int write;
    int size;
    int numbers[];
    String words[];

    CircularQueue (int size, boolean number) {
        read = -1;
        write = -1;
        this.size = size;
        this.numbers = new int[size];

    }

    CircularQueue (int size) {
        read = -1;
        write = -1;
        this.size = size;
        this.words = new String[size];

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

    String enQueue (int element, boolean number){
        if (isFull()){
            return "Circular Queue is full";
        }else{
            if (read == -1){
                read = 0;
            }
            write = (write + 1) % size;
            numbers[write] = element;
            return "Element added to the Circular Queue";
        }
    }

    String enQueue (String element){
        if (isFull()){
            return "Circular Queue is full";
        }else{
            if (read == -1){
                read = 0;
            }
            write = (write + 1) % size;
            words[write] = element;
            return "Element added to the Circular Queue";
        }
    }

    int deQueue(boolean number) {
        int element;
        if (isEmpty()){
            return -1;
        }else{
            element = numbers[read];
            if (read == write){
                read = -1;
                write = -1;
            }else{
                read = (read + 1) % size;
            }
            return (element);
        }
    }

    String deQueue() {
        String element;
        if (isEmpty()){
            return "Circular Queue is empty";
        }else{
            element = words[read];
            if (read == write){
                read = -1;
                write = -1;
            }else{
                read = (read + 1) % size;
            }
            return (element);
        }
    }

    String display(boolean number){
        /*
        Function to display status of Circular Queue
         */
        int i;
        String display = "";
        if (isEmpty()){
            return "Empty Queue";
        } else{
            for (i = read; i!=write; i = (i+1) % size){
                display = display + numbers[i] + ", ";
            }
            return "Items -> " + display;
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
                display = display + words[i] + ", ";
            }
            return "Items -> " + display;
        }
    }
}
