package com.example.showseek;

public class ListArray {

    static private final int N = 1000;
    private int position, count, larray[];

    public ListArray() {
        count = 0;
        larray = new int [N];
    }

    private boolean empty () {
        return count <= 0;
    }

    private boolean full () {
        return count >= N;
    }

    public boolean delete (int item) {
        boolean deleted = false;
        if (!empty())
            if (search(item)) {
                for (int j = position; j < count-1; j++)
                    larray[j] = larray[j+1];
                count--;
                deleted = true;
            }
            else
                System.out.println("List is Empty");
            return deleted;
    }

    public boolean search (int item) {
        boolean found = false, stop = false;
        position = 0;
        while (position < count && !stop)
            if (larray[position] >= item) {
                stop = true;
                if (larray[position] == item)
                    found = true;
            } else
                position++;
        return found;
    }

    public void output () {
        System.out.print ("List: ");
        int j = 0;
        while (j != count) {
            System.out.print (larray[j]+" ");
            j++;
        }
        System.out.println();
    }
}
