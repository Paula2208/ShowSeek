package com.example.showseek.estructures.nonLineal;

import com.example.showseek.estructures.array.ListArray;
import com.example.showseek.estructures.references.nodes.DoubleNode;

//Back -> Left
//Next -> Right

public class AVLTree <T extends Comparable<T>>{

    // Atributos
    private DoubleNode<T> root;

    private DoubleNode<T> parentActual;

    //Constructor
    public AVLTree(){
        root = null;
    }

    public DoubleNode<T> getRoot(){
        return root;
    }

    //Metodos

    //Encuentra el nodo donde se encuentra el inicio del recorrido
    public DoubleNode<T> find(T item, DoubleNode<T> nodo){

        if(nodo.getData().compareTo(item) == 0){
            return nodo;
        }
        else if(nodo.getData().compareTo(item) > 0){

            //El dato del nodo es mayor que el item buscado
            return find(item, nodo.getBack());

        }
        else{

            //El dato del nodo es menor que el item buscado
            return find(item, nodo.getNext());
        }
    }

    //Retorna los datos de la familia del nodo
    public ListArray<T> family(DoubleNode<T> nodo){
        ListArray<T> f = new ListArray<T>(5);

        //Actual f[0]
        try{
            f.set(0,nodo.getData());
        }
        catch(NullPointerException actual){
            f.set(0,null);
        }

        //Izquierdo f[1]
        try{
            f.set(1,nodo.getBack().getData());
        }
        catch(NullPointerException left){
            f.set(1,null);
        }

        //Derecho f[2]
        try{
            f.set(2,nodo.getNext().getData());
        }
        catch(NullPointerException right){
            f.set(2,null);
        }

        //Padre f[3]
        try{
            if(nodo == root){
                f.set(3,null);
            }
            else{

                parent(nodo);
                f.set(3,parentActual.getData());
            }
        }
        catch(NullPointerException parent){
            f.set(3,null);
            System.out.println("ERROR Family parent(nodo)");
        }

        return f;
    }

    //Encuentra el parent del nodo
    public void parent(DoubleNode<T> nodo){
        getParent(root, nodo);
    }

    //Devuelve el parent del nodo - Recursivo
    public void getParent(DoubleNode<T> posibleParent, DoubleNode<T> node){
        try{
            if(posibleParent.getBack() == node || posibleParent.getNext() == node){

                // System.out.println("\n ¡Encontró el parent! Parent: "+ posibleParent.getData() + " nodo actual: " + node.getData()+"\n");

                parentActual = posibleParent;
            }
            else if(posibleParent.getData().compareTo(node.getData()) < 0 ){

                // System.out.println("\n Entro a 1 ");

                getParent(posibleParent.getNext(), node);
            }
            else if( posibleParent.getData().compareTo(node.getData()) > 0){

                // System.out.println("\n Entro a  2");

                getParent(posibleParent.getBack(), node);
            }
        }
        catch(Exception e){
            System.out.println("\nError: "+ e +" Dato en el nodo posibleParent: "+ posibleParent.getData() + " Dato del nodo: " + node.getData()+"\n");
        }
    }

    // inserta datos en el arbol
    public DoubleNode<T> add(T element) {
        return root = insert(element, root);
    }

    // eliminar item del arbol
    public DoubleNode<T> delete(T element) {
        return root = remove(element, root);
    }

    //Limpia todo el contenido del arbol
    public void clean(){
        root.setBack(null);
        root.setNext(null);
        root.setData(null);
    }

    //Insertar datos en el árbol - Devuelve la raiz luego de insertar los datos
    private DoubleNode<T>insert(T element, DoubleNode<T> node) {
        if (node == null) {
            return new DoubleNode<T>(element);
        }

        try{
            if (element.compareTo(node.getData()) < 0) {
                node.setBack(insert(element, node.getBack())); //insercion en nodo izquierdo

            } else if (element.compareTo(node.getData()) > 0) {

                node.setNext(insert(element, node.getNext())); //Insercion en nodo derecho
            }
        }
        catch(Exception e){
            System.out.println("Error: "+e + "Elemento: " + element);
        }


        calcHeight(node); //Calcula la altura del nodo
        return balance(node); //Retorna la raiz del arbol luego de balancearce
    }

    //Eliminar elementos del árbol - Devuelve la raiz luego de eliminar los datos
    private DoubleNode<T> remove(T element, DoubleNode<T> node) {
        if (node == null || (node.getBack() == null && node.getNext() == null)) {
            return null;
        }

        if (element.compareTo(node.getData()) < 0) {
            node.setBack(remove(element, node.getBack()));
        } else if (element.compareTo(node.getData()) > 0) {
            node.setNext(remove(element, node.getNext()));
        } else {
            if (node.getNext() == null) {// derecha vacía izquierda no vacía
                node = node.getBack();
            } else if (node.getBack() == null) {// izquierda vacía derecha no vacía
                node = node.getNext();
            } else {// Izquierda y derecha no están vacías, luego tome el nodo más pequeño del subárbol derecho y úselo para reemplazar el nodo raíz
                DoubleNode<T> rightMin = searchMin(node.getNext());
                node.setData(rightMin.getData());
                node.setNext(remove(rightMin.getData(), node.getNext()));
            }
        }
        calcHeight(node);
        return balance(node);
    }

    //Busca el menor dato del arbol
    private DoubleNode<T> searchMin(DoubleNode<T> node) {
        assert node != null;

        if (node.getBack() != null) {
            return searchMin(node.getBack());
        }
        return node;
    }

    //Calcular la altura de un nodo en el arbol
    private int height(DoubleNode<T> node) {
        return node == null ? -1 : node.getHeight();
        //Si es nulo pues será la raiz y la raiz no tiene parent (-1)
        //Si no pode la atura del nodo como depth
    }

    //Calcula el maximo entre las alturas de los nodos hijos para setear la altura del nodo
    private void calcHeight(DoubleNode<T> node) {
        node.setHeight( Math.max(height(node.getBack()), height(node.getNext())) + 1);
    }

    //**Rotacion izquierda - devuelve el nodo raiz del subarbol rotado
    private DoubleNode<T> leftRotate(DoubleNode<T> node) {
        DoubleNode<T> newNode = node.getNext();
        node.setNext(newNode.getBack());
        newNode.setBack(node);
        calcHeight(node);
        calcHeight(newNode);
        return newNode;
    }

    // Rotación a la derecha - devuelve el nodo raiz del subarbol rotado
    private DoubleNode<T> rightRotate(DoubleNode<T> node) {
        DoubleNode<T> newNode = node.getBack();
        node.setBack(newNode.getNext());
        newNode.setNext(node);
        calcHeight(node);
        calcHeight(newNode);
        return newNode;
    }

    // Rotación a la izquierda y luego derecha - devuelve el nodo raiz del subarbol rotado
    private DoubleNode<T> leftAndRightRotate(DoubleNode<T> node) {
        node.setBack(leftRotate(node.getBack()));
        return rightRotate(node);
    }

    // Rotación a la derecha y luego izquierda - devuelve el nodo raiz del subarbol rotado
    private DoubleNode<T> rightAndLeftRotate(DoubleNode<T> node) {
        node.setNext(rightRotate(node.getNext()));
        return leftRotate(node);
    }

    //Balance del nodo - devuelve el nodo raíz del árbol restaurado
    private DoubleNode<T> balance(DoubleNode<T> node) {
        if (height(node.getBack()) - height(node.getNext()) == 2) {
            if (height(node.getBack().getBack()) > height(node.getBack().getNext())) {

                // necesita girar a la derecha
                return rightRotate(node);

            } else {

                // necesita izquierda y derecha
                return leftAndRightRotate(node);

            }
        } else if (height(node.getNext()) - height(node.getBack()) == 2) {
            if (height(node.getNext().getNext()) > height(node.getNext().getBack())) {

                // necesita girar a la izquierda
                return leftRotate(node);

            } else {

                // necesita diestros y zurdos
                return rightAndLeftRotate(node);

            }
        }
        return node;
    }

    public void setRoot(DoubleNode<T> root) {
        this.root = root;
    }

}
