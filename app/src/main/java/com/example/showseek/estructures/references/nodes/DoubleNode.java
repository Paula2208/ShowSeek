package com.example.showseek.estructures.references.nodes;

public class DoubleNode<T extends Comparable<T>> extends Node<T> {

    //Atributes
    private DoubleNode<T> back; //Nodo de la izquierda
    private DoubleNode<T> next; //Nodo de la derecha
    private int height = 0;          //Altura del nodo

    //Constructors
    public DoubleNode(T data){
        super.setData(data);
        back = null;
        next = null;
    }

    public DoubleNode(){
        super.setData(null);
        back = null;
        next = null;
    }

    public DoubleNode(T data, DoubleNode<T> izquierda, DoubleNode<T> derecha){
        super.setData(null);
        back = izquierda;
        next = derecha;
    }

    //Methods
    public DoubleNode<T> getBack(){
        return back;
    }

    public void setBack(DoubleNode<T> back){
        this.back = back;
    }

    public DoubleNode<T> getNext(){
        return next;
    }

    public void setNext(DoubleNode<T> next){
        this.next = next;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
