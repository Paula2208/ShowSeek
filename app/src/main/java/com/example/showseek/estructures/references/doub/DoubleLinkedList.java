package com.example.showseek.estructures.references.doub;


import com.example.showseek.estructures.references.nodes.DoubleNode;
import com.example.showseek.estructures.references.nodes.Node;
import com.example.showseek.estructures.references.single.LinkedList;

public class DoubleLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    //Atributes
    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    //Constructor
    public DoubleLinkedList(){
        head = null;
        tail = null;
        super.setSize(0);
    }

    //Methods
    //Inserter Methods
    @Override
    public void pushFront(T item){
        DoubleNode<T> node = new DoubleNode<T>(item);
        if(head == null){
            head = node;
            tail = node;
            super.setSize(super.getSize()+1);
        }
        else{
            node.setNext(head);
            head.setBack(node);
            head = node;
            super.setSize(super.getSize()+1);
        }
    }

    @Override
    public void pushBack(T item){
        DoubleNode<T> node = new DoubleNode<T>(item);
        if(tail == null){
            head = node;
            tail = node;
            super.setSize(super.getSize()+1);;
        }
        else{
            tail.setNext(node);
            node.setBack(tail);
            tail = node;
            super.setSize(super.getSize()+1);
        }
    }


    public void addBefore(DoubleNode<T> base, T item){
        if(head == base){
            pushFront(item);
        }
        else{
            DoubleNode<T> node = new DoubleNode<T>(item);
            node.setBack(base.getBack());
            node.setNext(base);
            base.setBack(node);
            base.getBack().setNext(node);
            super.setSize(super.getSize()+1);
        }
    }


    public void addAfter(DoubleNode<T> base, T item){
        if(tail == base){
            pushBack(item);
        }
        else{
            DoubleNode<T> node = new DoubleNode<T>(item);
            node.setNext(base.getNext());
            node.setBack(base);
            base.setNext(node);
            base.getNext().setBack(node);
            super.setSize(super.getSize()+1);
        }
    }

    //Deleter Methods
    @Override
    public T popFront(){
        T s = null;
        if(!empty()){
            s = head.getData();
            head = head.getNext();
            if(head != null){
                head.setBack(null);
            }
            super.setSize(super.getSize()-1);
            if(head == null){
                tail = null;
            }
        }
        return s;
    }

    @Override
    public T popBack(){
        T s = null;
        if(!empty()){
            s = tail.getData();
            tail = tail.getBack();
            tail.setNext(null);
            super.setSize(super.getSize()-1);
            if(tail == null){
                head = null;
            }
        }
        return s;
    }

    public T pop(DoubleNode<T> base){
        T s = null;
        if(!empty()){
            if(base == head){
                s = popFront();
            }
            else if(base == tail){
                s = popBack();
            }
            else{
                s = base.getData();
                base.getBack().setNext(base.getNext());
                base.getNext().setBack(base.getBack());
                super.setSize(super.getSize()-1);
            }
        }
        return s;
    }

    //Search Methods
    @Override
    public T topFront(){
        return head.getData();
    }

    @Override
    public T topBack(){
        return tail.getData();
    }

    //Returns the first node with the item given
    @Override
    public DoubleNode<T> searchNode(T item){
        DoubleNode<T> pos = head;
        for(int i=0; i<super.getSize(); i++){
            if(pos.getData().compareTo(item) == 0){
                break;
            }
            else{
                pos = pos.getNext();
            }
        }
        return pos;
    }

    @Override
    public boolean find(T item){
        boolean s = false;
        DoubleNode<T> pos = head;
        for(int i=0; i<super.getSize(); i++){
            if(pos.getData().compareTo(item) == 0){
                s=true;
                break;
            }
            else{
                pos = pos.getNext();
            }
        }
        return s;
    }

    //Sort Methods with bubble sort - Ascending 0 - Descending 1
    @Override
    public void sort(int order){
        if(order == 0){
            DoubleNode<T> pos = new DoubleNode<T>();
            DoubleNode<T> index = new DoubleNode<T>();
            pos = head;
            index = null;
            if(!empty()){
                while(pos != null){
                    //Index is the next of the pos
                    index = pos.getNext();
                    while(index != null){
                        if(pos.getData().compareTo(index.getData())>0){
                            //Swapping occurs if data are not in the intended order
                            T ac = pos.getData();
                            pos.setData(index.getData());
                            index.setData(ac);
                        }
                        index = index.getNext();
                    }
                    pos = pos.getNext();
                }
            }
        }
        else if(order == 1){
            DoubleNode<T> pos = new DoubleNode<T>();
            DoubleNode<T> index = new DoubleNode<T>();
            pos = head;
            index = null;
            if(!empty()){
                while(pos != null){
                    //Index is the next of the pos
                    index = pos.getNext();
                    while(index != null){
                        if(pos.getData().compareTo(index.getData())<0){
                            //Swapping occurs if data are not in the intended order
                            T ac = pos.getData();
                            pos.setData(index.getData());
                            index.setData(ac);
                        }
                        index = index.getNext();
                    }
                    pos = pos.getNext();
                }
            }
        }
    }

    //Getter Methods
    @Override
    public DoubleNode<T> getHead(){
        return head;
    }

    @Override
    public DoubleNode<T> getTail(){
        return tail;
    }

    //Setter Methods
    public void setHead(DoubleNode<T> head){
        this.head = head;
    }

    public void setTail(DoubleNode<T> tail){
        this.tail = tail;
    }

    //Is the LinkedList Empty?
    @Override
    public boolean empty(){
        boolean s = false;
        if(head == null){
            s=true;
        }
        return s;
    }

    //Make a Copy of the linkedList
    @Override
    public DoubleLinkedList<T> copy(){
        DoubleLinkedList<T> copy = new DoubleLinkedList<T>();
        DoubleNode<T> pos = new DoubleNode<T>();
        pos = head;
        for(int i=0; i<super.getSize(); i++){
            copy.pushBack(pos.getData());
            pos = pos.getNext();
        }
        return copy;
    }

}

