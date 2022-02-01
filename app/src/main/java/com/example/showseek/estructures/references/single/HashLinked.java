package com.example.showseek.estructures.references.single;


import android.util.Log;

public class HashLinked {

    // encabezado de la lista de enlaces
    private IDs head;

    // eliminar
    public void deleteEmpById(int id) {
        if (head == null) {
            return;
        }
        IDs temp = head;

        while (true) {
            if (temp.id == id) {
                // eliminar
                head = temp.next;
                break;
            }

            if (temp.next == null) {
                break;
            }

            if (temp.next.id == id) {
                // Eliminar el nodo emp
                temp.next = temp.next.next;
                break;
            }

            // hacia atrás
            temp = temp.next;
        }
    }

    // Encuentra
    public IDs findEmpById(int id) {
        if (head == null) {
            return null;
        }
        IDs temp = head;
        while (true) {
            if (temp.id == id) {
                break;
            }
            if (temp.next == null) {
                temp = null;
                break;
            }
            // hacia atrás
            temp = temp.next;
        }
        return temp;
    }

    // agregar
    public void add(IDs emp) {
        if (head == null) {
            head = emp;
            return;
        }
        IDs temp = head;
        while (temp.next != null) {
            // hacia atrás
            temp = temp.next;
        }
        temp.next = emp;
    }
}