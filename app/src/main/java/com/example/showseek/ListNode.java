package com.example.showseek;

public class ListNode {

    node First;
    node Last;

    public ListNode() {

        First = null;
        Last = null;

    }

    public void EnterNode (int data){

        node NewNode = new node ();
        NewNode.data = data;

        if (First == null){
            First = NewNode;
            First.Next = null;
            Last = First;
        } else {
            Last.Next = NewNode;
            NewNode.Next = null;
            Last = NewNode;
        }
    }

    public void ViewList (){

        node Actual = new node ();
        Actual = First;

        while (Actual != null){

            System.out.print(Actual.data);
            Actual = Actual.Next;

        }

    }
}
