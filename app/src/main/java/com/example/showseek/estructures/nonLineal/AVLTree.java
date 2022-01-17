package com.example.showseek.estructures.nonLineal;

import com.example.showseek.estructures.references.nodes.DoubleNode;

//Back -> Left
//Next -> Right

public class AVLTree <T extends Comparable<T>>{
    // nodo raíz del árbol
    private DoubleNode<T> root;

    // inserta datos en el árbol
    public DoubleNode<T> add(T element) {
        return root = insert(element, root);
    }

    // eliminar elemento con elemento de valor en árbol
    public DoubleNode<T> delete(T element) {
        return root = remove(element, root);
    }

    public void clean(){
        root.setBack(null);
        root.setNext(null);
        root.setData(null);
    }

    /**
     * Insertar datos en el árbol
     *
     * valor de datos del elemento @param
     * @param node El nodo raíz del árbol
     * @return devuelve el nodo raíz del árbol después de insertar datos
     */
    private DoubleNode<T>insert(T element, DoubleNode<T> node) {
        if (node == null) {
            return new DoubleNode<T>(element);
        }

        if (element.compareTo(node.getData()) < 0) {
            node.setBack(insert(element, node.getBack()));
        } else if (element.compareTo(node.getData()) > 0) {
            node.setNext(insert(element, node.getNext()));
        }

        calcHeight(node);
        return balance(node);
    }

    /**
     * Eliminar elementos del árbol
     *
     *
     * @param node El nodo raíz del árbol
     * @return devuelve el nodo raíz del árbol después de la eliminación
     */
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

    private DoubleNode<T> searchMin(DoubleNode<T> node) {
        assert node != null;

        if (node.getBack() != null) {
            return searchMin(node.getBack());
        }
        return node;
    }

    /**
     * Calcular la altura del nodo
     *
     * @param node El nodo a calcular
     * @return devuelve la altura del nodo
     */
    private int height(DoubleNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private void calcHeight(DoubleNode<T> node) {
        node.height = Math.max(height(node.getBack()), height(node.getNext())) + 1;
    }

    /**
     * Mano izquierda
     *
     * @param node El nodo raíz del subárbol a rotar
     * @return devuelve el nodo raíz del subárbol girado
     */
    private DoubleNode<T> leftRotate(DoubleNode<T> node) {
        DoubleNode<T> newNode = node.getNext();
        node.setNext(newNode.getBack());
        newNode.setBack(node);
        calcHeight(node);
        calcHeight(newNode);
        return newNode;
    }

    /**
     * Giro a la derecha
     *
     * @param node El nodo raíz del subárbol a rotar
     * @return devuelve el nodo raíz del subárbol girado
     */
    private DoubleNode<T> rightRotate(DoubleNode<T> node) {
        DoubleNode<T> newNode = node.getBack();
        node.setBack(newNode.getNext());
        newNode.setNext(node);
        calcHeight(node);
        calcHeight(newNode);
        return newNode;
    }

    /**
     * Gire a la izquierda y luego a la derecha
     *
     * @param node El nodo raíz del subárbol a rotar
     * @return devuelve el nodo raíz del subárbol girado
     */
    private DoubleNode<T> leftAndRightRotate(DoubleNode<T> node) {
        node.setBack(leftRotate(node.getBack()));
        return rightRotate(node);
    }

    /**
     * Gire a la derecha y luego a la izquierda
     *
     * @param node El nodo raíz del subárbol a rotar
     * @return devuelve el nodo raíz del subárbol girado
     */
    private DoubleNode<T> rightAndLeftRotate(DoubleNode<T> node) {
        node.setNext(rightRotate(node.getNext()));
        return leftRotate(node);
    }

    /**
     * Restaurar un árbol enraizado en el nodo
     *
     * @param node
     * @return devuelve el nodo raíz del árbol restaurado
     */
    private DoubleNode<T> balance(DoubleNode<T> node) {
//        assert node != null;
        if (height(node.getBack()) - height(node.getNext()) == 2) {
            if (height(node.getBack().getBack()) > height(node.getBack().getNext())) {// necesita girar a la derecha
                return rightRotate(node);
            } else {// necesita izquierda y derecha
                return leftAndRightRotate(node);
            }
        } else if (height(node.getNext()) - height(node.getBack()) == 2) {
            if (height(node.getNext().getNext()) > height(node.getNext().getBack())) {// necesita girar a la izquierda
                return leftRotate(node);
            } else {// necesita diestros y zurdos
                return rightAndLeftRotate(node);
            }
        }
        return node;
    }
}
