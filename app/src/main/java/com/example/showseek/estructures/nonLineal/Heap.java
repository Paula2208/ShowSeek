package com.example.showseek.estructures.nonLineal;

import com.example.showseek.estructures.array.ListArray;

public class Heap<T extends Comparable<T>> {

    //Atributos
    private ListArray<T> arr;

    //Constructor
    public Heap(){
        arr = new ListArray<T>();
    }

    public Heap(int size){
        arr = new ListArray<T>(size);
    }

    //Metodos-----------------------------------------

    //Devuelve el tamaño del array
    public int getSize(){
        return arr.getSize();
    }

    //Está vacio?
    public boolean isEmpty(){
        return arr.empty();
    }

    //Devuelve el nodo padre del index ignresado
    public int parent(int index){
        int parent = 0;
        if(index == 0){
            parent = 0;
        }
        else{
            parent =(index-1)/2;
        }
        return parent;
    }

    //Devuelve el hijo derecho
    public int rightChild(int index){
        return index * 2 + 2 ;
    }

    //Devuelve el hijo izquierdo
    public int leftChild(int index){
        return index * 2 + 1 ;
    }

    //Añade item al árbol
    public void add(T item){
        arr.pushBack(item);
        siftUp(arr.getSize()-1);

    }

    //Intercambio máximo hacia arriba
    public void siftUp(int i){
        while( i>0 && arr.get(parent(i)).compareTo(arr.get(i))<0){

            //Intercambio padre-hijo si es mayor
            arr.swap(i,parent(i));

            //Recuperar el indice del valor insertado luego del cambio
            i = parent(i);
        }
    }

    //muestra el mayor sin eliminarlo
    public T findMax(){
        T max = null;
        if(arr.getSize() >0){
            max = arr.get(0);
        }
        return max;
    }

    //extrae el mayor del monticulo
    public T extractMax(){
        T max = findMax();
        arr.swap(0,arr.getSize()-1);
        arr.popBack();
        siftDown(0);
        return max;
    }

    //baja el nodo segun prioridad
    public void siftDown(int i){
        while(leftChild(i)<arr.getSize()){
            int j= leftChild(i);
            if(j+1 < arr.getSize() && arr.get(j+1).compareTo(arr.get(j))>0){
                j++;
            }
            if(arr.get(i).compareTo(arr.get(j))>=0){
                break;
            }
            arr.swap(i,j);
            i=j;
        }
    }

    //Reemplazar nodo
    public T replace(T i){
        T item = findMax();
        arr.set(0,i);
        siftDown(0);
        return item;
    }

    public void clean(){
        arr.clear();
    }


}
