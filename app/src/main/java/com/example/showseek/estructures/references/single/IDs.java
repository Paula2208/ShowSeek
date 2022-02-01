package com.example.showseek.estructures.references.single;

public class IDs {

    public int id;
    public int firmaCode;

    public IDs next;

    public IDs(int id, int firmaCode) {
        this.id = id;
        this.next = null;
        this.firmaCode = firmaCode;
    }
}
