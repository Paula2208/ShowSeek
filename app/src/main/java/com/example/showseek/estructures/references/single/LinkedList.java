package com.example.showseek.estructures.references.single;

/*To the CompareTo method we know the following returns:
        Less than Zero --> the object is 'smaller' than the parameter
        More than Zero --> the object is 'bigger' than the parameter
        equals to zero --> the objects are equal*/

import com.example.showseek.estructures.references.nodes.Node;

public class LinkedList<T extends Comparable<T>> {

    //Atributes
    private int size;
    private Node<T> head;
    private Node<T> tail;

    //Constructors
    public LinkedList(){
        size = 0;
        head= null;
        tail=null;
    }

    //Methods
    //Inserter Methods
    public void pushFront(T item){
        Node<T> node = new Node<T>(item);
        if(head == null){
            head = node;
            tail = node;
            size++;
        }
        else{
            node.setNext(head);
            head = node;
            size++;
        }
    }

    public void pushBack(T item){
        Node<T> node = new Node<T>(item);
        if(tail == null){
            head = node;
            tail = node;
            size++;
        }
        else{
            tail.setNext(node);
            tail = node;
            size++;
        }
    }

    public void addBefore(Node<T> base, T item){
        if(head == base){
            pushFront(item);
        }
        else{
            Node<T> node = new Node<T>(item);
            Node<T> pos = new Node<T>();

            for(int i=0; i<size; i++){
                if(pos.getNext() == base){
                    node.setNext(base);
                    pos.setNext(node);
                    size++;
                    break;
                }
                else{
                    pos= pos.getNext();
                }
            }
        }
    }

    public void addAfter(Node<T> base, T item){
        if(tail == base){
            pushBack(item);
        }
        else{
            Node<T> node = new Node<T>(item);
            node.setNext(base.getNext());
            base.setNext(node);
            size++;
        }
    }

    //Deleter Methods
    public T popFront(){
        T s = null;
        if(!empty()){
            s = head.getData();
            head = head.getNext();
            size--;
            if(head == null){
                tail = null;
            }
        }
        return s;
    }

    public T popBack(){
        T s = null;
        if(!empty()){
            s = tail.getData();
            Node<T> pos = new Node<T>();
            pos = head;
            for(int i=0; i<size; i++){
                if(pos.getNext() == tail){
                    tail = pos;
                    tail.setNext(null);
                    size--;
                    break;
                }
                else{
                    pos= pos.getNext();
                }
            }
        }
        return s;
    }

    public T pop(Node<T> base){
        T s = null;
        if(!empty()){
            if(base == head){
                s = popFront();
            }
            else if(base == tail){
                s = popBack();
            }
            else{
                Node<T> pos = new Node<T>();
                s = base.getData();
                for(int i=0; i<size; i++){
                    if(pos.getNext() == base){
                        pos.setNext(base.getNext());
                        base.setNext(null);
                        size--;
                        break;
                    }
                    else{
                        pos = pos.getNext();
                    }
                }
            }
        }
        return s;
    }

    //Search Methods
    public T topFront(){
        return head.getData();
    }

    public T topBack(){
        return tail.getData();
    }

    //Returns the first node with the item given
    public Node<T> searchNode(T item){
        Node<T> pos = new Node<T>();
        for(int i=0; i<size; i++){
            if(pos.getData().compareTo(item) == 0){
                break;
            }
            else{
                pos = pos.getNext();
            }
        }
        return pos;
    }

    //Returns the first node with the item given begining from the index given
    public Node<T> searchNode(T item, int index){
        Node<T> pos = new Node<T>();
        for(int i=index; i<size; i++){
            if(pos.getData().compareTo(item) == 0){
                break;
            }
            else{
                pos = pos.getNext();
            }
        }
        return pos;
    }

    public boolean find(T item){
        boolean s = false;
        Node<T> pos = new Node<T>();
        for(int i=0; i<size; i++){
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
    public void sort(int order){
        if(order == 0){
            Node<T> pos = new Node<T>();
            Node<T> index = new Node<T>();
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
            Node<T> pos = new Node<T>();
            Node<T> index = new Node<T>();
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
    public int getSize(){
        return size;
    }

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    //Setter Methods
    public void setSize(int size){
        this.size = size;
    }

    public void setHead(Node<T> head){
        this.head = head;
    }

    public void setTail(Node<T> tail){
        this.tail = tail;
    }

    //Is the LinkedList Empty?
    public boolean empty(){
        boolean s = false;
        if(head == null){
            s=true;
        }
        return s;
    }

    //Make a Copy of the linkedList
    public LinkedList<T> copy(){
        LinkedList<T> copy = new LinkedList<T>();
        Node<T> pos = new Node<T>();
        pos = head;
        for(int i=0; i<size; i++){
            copy.pushBack(pos.getData());
            pos = pos.getNext();
        }
        return copy;
    }
}