package com.example.showseek.estructures.hash;

import com.example.showseek.estructures.array.ListArray;

public class HashS {

    //Atributos
    private ListArray<Integer> set;

    //Constructor
    public HashS(){
        this.set = new ListArray<Integer>();
    }

    //Metodos

    //Delete
    public void delete(String id) {
        int is = hashFun(id);
        set.pop(is);
    }

    //Find
    public boolean find(String id) {
        int is = hashFun(id);
        return set.find(is);
    }

    //Add
    public void add(String id) {

        // Cree el codigo hash para el id
        int nuevo = hashFun(id);

        // Añadir al lista el codigo hash
        set.pushBack(nuevo);
    }

    //Add without hash
    public void add(int pass) {
        // Añadir al lista el codigo hash
        set.pushBack(pass);
    }

    //Funcion Hash
    public int hashFun(String password) {
        int sum = 0;
        int ascii = 0;

        char[] pass = password.toCharArray();

        for(int i = 0; i<password.length() ; i++){

            ascii = pass[i];
            sum = sum + (ascii % 71);
        }

        return sum;
    }
}
