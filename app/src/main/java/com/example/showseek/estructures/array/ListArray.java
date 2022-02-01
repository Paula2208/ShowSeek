package com.example.showseek.estructures.array;

import android.util.Log;

public class ListArray<T extends Comparable<T>>{

    private int size;
    private int capacity = 100000;
    private T larray[];

    //Constructors
    public ListArray() {
        size = 0;
        larray =(T[]) new Comparable[capacity];
    }

    public ListArray(int capacity) {
        size = 0;
        this.capacity = capacity;
        larray =(T[]) new Comparable[capacity];
    }

    //Methods
    //Is the List empty?
    public boolean empty () {
        return size <= 0;
    }

    public boolean full () {
        return size >= capacity;
    }

    //Insert methods
    public void pushFront(T item){
        if(size+1 > capacity){
            larray = increase();
            for(int i=size-1; i>=0; i--){
                larray[i+1] = larray[i];
            }
            larray[0] = item;
            size++;
        }
        else{
            for(int i=size-1; i>=0; i--){
                larray[i+1] = larray[i];
            }
            larray[0] = item;
            size++;
        }
    }

    public void pushBack(T item){
        if(size+1 > capacity){
            larray = increase();

            larray[size] = item;
            size++;

        }
        else{
            larray[size] = item;
            size++;
        }
    }

    public void push(int index, T item){
        if(index == 0){
            pushFront(item);
        }
        else{
            if(size+1>capacity){
                larray = increase();
                for(int i=size-1; i>=index; i--){
                    larray[i+1] = larray[i];
                }
                larray[index]= item;
                size++;
            }
            else{
                for(int i=size-1; i>=index; i--){
                    larray[i+1] = larray[i];
                }
                larray[index]= item;
                size++;
            }
        }
    }

    //Delete methods
    public T popFront(){
        T s = larray[0];
        for(int i=0; i<size; i++){
            larray[i]=larray[i+1];
        }
        larray[size-1]=null;
        size--;
        return s;
    }

    public T popBack(){
        T s = larray[size-1];
        larray[size-1]=null;
        size--;
        return s;
    }

    public T pop(int index){
        T s = larray[index];
        try{
            for(int i=index; i<size; i++){
                larray[i]=larray[i+1];
            }
        }
        catch(Exception e){
            Log.d("Error","Index out of bound Array");
        }

        size--;
        return s;
    }

    //Search methods
    public T topFront(){
        return larray[0];
    }

    public T topBack(){
        return larray[size-1];
    }

    public T get(int index){
        return larray[index];
    }

    public boolean find(T item){
        boolean n = false;
        for(int i=0; i<size; i++){
            if(larray[i].compareTo(item)== 0){
                n = true;
                break;
            }
        }
        return n;
    }

    public T top(int index){
        return larray[index];
    }

    //Returns the index of the first item equal to the parameter
    public int searchIndex(T item){
        int n = -1;
        for(int i=0; i<size; i++){
            if(larray[i].compareTo(item)== 0){
                n = i;
                break;
            }
        }
        return n;
    }

    //Returns the index of item equals since the index
    public int searchIndex(T item, int index){
        int n = -1;
        for(int i=index; i<size; i++){
            if(larray[i].compareTo(item)== 0){
                n = i;
                break;
            }
        }
        return n;
    }

    //Sort Method with bubble sort - Ascending 0 - Descending 1
    public void sort(int order){
        if(order == 0){
            for(int i=0; i<size-1;i++){
                for(int j=0; j<size-i-1; j++){
                    if(larray[j].compareTo(larray[j+1])>0){
                        T ac = larray[j];
                        larray[j]=larray[j+1];
                        larray[j+1]= ac;
                    }
                }
            }
        }
        else if(order==1){
            for(int i=0; i<size-1;i++){
                for(int j=0; j<size-i-1; j++){
                    if(larray[j].compareTo(larray[j+1])<0){
                        T ac = larray[j];
                        larray[j]=larray[j+1];
                        larray[j+1]= ac;
                    }
                }
            }
        }
    }

    //Getters and Setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public T[] getLarray() {
        return larray;
    }

    public void setLarray(T[] larray) {
        this.larray = larray;
    }


    //Return a copy of the List
    public ListArray<T> copy(){
        ListArray<T> copy = new ListArray<T>(capacity);
        for(int i=0; i<size; i++){
            copy.pushBack(larray[i]);
        }
        return copy;
    }

    //Increase the size of the array
    public T[] increase(){
        int a = capacity*2;
        T[] bigger = (T[]) new Comparable[capacity*2];
        for(int i=0; i<size; i++){
            bigger[i]=larray[i];
        }
        capacity = a;
        return bigger;
    }

    public void swap(int i, int parent) {
        T datai = larray[i];
        T datap = larray[parent];

        larray[parent] = datai;
        larray[i] = datap;

    }

    public void set(int index, T e){
        String prueba = "";
       /* if(index > size || index<0){
            prueba = "Set failed ,index is overflow";
            System.out.println(prueba);
        }
        else{
            larray[index] = e;
        }*/

        larray[index] = e;
    }

    public void clear(){
        T[] nuevo = (T[]) new Comparable[capacity];
        larray = nuevo;
    }

}
