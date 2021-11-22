package com.example.showseek;

public class QueueRef{

    //Atributes
    private Node front;
    private Node rear;
    private String message ="";
    private int size;

    //Constructors
    public QueueRef(){
        front=null;
        rear= null;
        size = 0;
    }

    //Methods
        //Add an item of queue
    public void enqueue(Object item){
        Node newi = new Node(item);

        if(rear != null){
            rear.setNext(newi);
            message ="Element added: " + item;
        }
        else{
            front = newi;
            message ="Element added: " + item;
        }
        rear = newi;
        size++;
    }

        //Delete an item of queue
    public Object dequeue(){
        Object r = null;
        if(!this.empty()){
            r = front.getData();
            front = front.getNext();
            size--;
        }
        return r;
    }

        //Queue is empty?
    public boolean empty() {
        return size == 0;
    }

    //Getters and Setters
    public Node getFront() {
        return front;
    }

    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
