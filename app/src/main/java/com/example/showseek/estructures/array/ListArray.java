package com.example.showseek.estructures.array;

public class ListArray<T extends Comparable<T>>{

    private int sizeT = 1000;
    private int position, count;
    private String message ="";
    private T larray[];
    private T reference;

    //Constructors
    public ListArray() {
        count = 0;
        larray =(T[]) new Comparable[sizeT];
    }

    public ListArray(int sizeT) {
        count = 0;
        this.sizeT = sizeT;
        larray =(T[]) new Comparable[sizeT];
    }

    //Methods
        //Is the LinkedList empty?
    private boolean empty () {
        return count <= 0;
    }

    private boolean full () {
        return count >= sizeT;
    }

        //Is the LinkedList empty?
    public boolean delete (T item) {
        boolean deleted = false;
        if (!empty()) {
            if (search(item)) {
                for (int j = position; j < count - 1; j++) {
                    larray[j] = larray[j + 1];
                }
                count--;
                deleted = true;
            }
            else {
                message = "List is empty";
            }
        }
        return deleted;

    }

        //Add a new item
    public boolean insert (T item) {
        boolean inserted = false;
        if (!full()) {
            if (!search(item)) {
                for (int j = count; j > position; j--) {
                    larray[j] = larray[j + 1];
                }
                larray[position] = item;
                count++;
                inserted = true;
            } else {
                message = "List is full: Item not added";
            }
        }
        else{

        }
        return inserted;
    }

        //Search a given item inside the list
    public boolean search (T item) {
        boolean found = false;
        boolean stop = false;
        position = 0;
        while ((position != count) && !stop) {
            if (larray[position].compareTo(item) >= 0) {
                stop = true;
                if (larray[position].compareTo(item) == 0)
                    found = true;
            }
            else {
                position++;
            }
        }
        return found;
    }

        //Return an String with all the items
    public String output () {
        message ="The list contains: ";
        int j = 0;
        while (j != count) {
            message = message + larray[j]+" ";
            j++;
        }
        return message;
    }

        //Comparation
    public int compareTo(T item) {
        int result;

        if(reference.compareTo(item)>0){
            result = 1;
        }
        else{
            if(reference.compareTo(item)<0){
                result = -1;
            }
            else{
                result = 0;
            }
        }
        return result;
    }
}
