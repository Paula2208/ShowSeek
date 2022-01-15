package com.example.showseek.estructures.references.single;

import com.example.showseek.estructures.references.nodes.Node;

public class OrderSingleLinked<T extends Comparable<T>> extends LinkedList<T> {

    //Constructor
    public OrderSingleLinked(){
        super();
    }

    //Insert methods
    public void insert(T item){
        if(super.getHead() == null){
            Node<T> node = new Node<T>(item);
            super.setHead(node);
            super.setTail(node);
            super.setSize(super.getSize()+1);
        }
        else{
            Node<T> pos = new Node<T>();
            pos = super.getHead();
            while(pos != null){
                if(item.compareTo(pos.getData())<0){
                    super.addBefore(pos, item);
                    break;
                }
                else{
                    if(pos == super.getTail()){
                        super.pushBack(item);
                        break;
                    }
                    else{
                        pos = pos.getNext();
                    }
                }
            }
        }
    }

    //Search Methods with binary search
    @Override
    public boolean find(T item){
        boolean found = false;
        if(binarySearch(super.getHead(), item)!= null){
            found = true;
        }
        return found;
    }

    //Returns the first node with the item given
    @Override
    public Node<T> searchNode(T item){
        return binarySearch(super.getHead(), item);
    }

    //Returns null if ths item isn´t in the list or the node of the item
    public Node<T> binarySearch(Node<T> node, T item){
        Node<T> start = node;
        Node<T> last = null;

        do {
            //Find middle node
            Node<T> middle = middleNode(start, last);

            if(middle == null){
                return null;
            }

            if(middle.getData().compareTo(item)==0){

                return middle;
            }
            else if(middle.getData().compareTo(item)<0){
                start = middle.getNext();
            }
            else{
                last = middle;
            }

        }
        while (last == null || last != start);

        return null;
    }

    //Returns the middle node of the linkedList
    public Node<T> middleNode(Node<T> start, Node<T> last){
        if(start==null){
            return null;
        }

        Node<T> s = start;
        Node<T> l = start.getNext();

        while(l != last){
            l = l.getNext();
            if( l != last){
                s = s.getNext();
                l = l.getNext();
            }
        }
        return s;
    }
}
