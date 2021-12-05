package com.example.showseek.estructures.references.doub;

import com.example.showseek.estructures.references.nodes.DoubleNode;

public class OrderDoubleLinked<T extends Comparable<T>> extends DoubleLinkedList<T> {

    //Constructor
    public OrderDoubleLinked(){
        super();
    }

    //Insert methods
    public void insert(T item){
        if(super.getHead() == null){
            DoubleNode<T> node = new DoubleNode<T>(item);
            super.setHead(node);
            super.setTail(node);
            super.setSize(super.getSize()+1);
        }
        else{
            DoubleNode<T> pos = new DoubleNode<T>();
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
    public DoubleNode<T> searchNode(T item){
        return binarySearch(super.getHead(), item);
    }

    //Returns null if ths item isn´t in the list or the node of the item
    public DoubleNode<T> binarySearch(DoubleNode<T> node, T item){
        DoubleNode<T> start = node;
        DoubleNode<T> last = null;

        do {
            //Find middle node
            DoubleNode<T> middle = middleNode(start, last);
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
    public DoubleNode<T> middleNode(DoubleNode<T> start, DoubleNode<T> last){
        if(start==null){
            return null;
        }

        DoubleNode<T> s = start;
        DoubleNode<T> l = start.getNext();

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
