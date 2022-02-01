package com.example.showseek.estructures.hash;

import com.example.showseek.estructures.references.single.HashLinked;
import com.example.showseek.estructures.references.single.IDs;
import com.example.showseek.estructures.references.single.LinkedList;

public class HashM{

    //Atributes
    private HashLinked[] ArrayListas;
    private int size;  //es el tamaño del array de linkedlists

    //Constructor
    public HashM(int size){
        this.ArrayListas = new HashLinked[size];
        this.size = size;

        for(int i=0; i<size;i++){
            ArrayListas[i] = new HashLinked();
        }
    }

    //Methods

    //Delete
    public void delete(int id) {
        int is = hashFun(id);
        ArrayListas[is].deleteEmpById(id);
    }

    //Find
    public boolean findEmpById(int id) {
        int is = hashFun(id);
        boolean salida = false;
        IDs emp = ArrayListas[is].findEmpById(id);

        if(emp != null){
            salida = true;
        }
        return salida;
    }

    //Add
    public void add(IDs emp) {

        // Seleccione la lista vinculada según la identificación del empleado
        int empLinkedListNO = hashFun(emp.id);

        // Añadir a la lista vinculada
        ArrayListas[empLinkedListNO].add(emp);
    }

    //HashFunction
    public int hashFun(int id_contrato) {
        return id_contrato % size;
    }
}
