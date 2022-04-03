import java.util.Iterator;

/**
 * GSLList : Generic Sorted  Linked List public methods: <br>
 * void insert(T val) bool <br>
 * contains(T val) bool <br>
 * remove(T val) <br>
 * remove(int which) <br>
 * isEmpty() <br>
 * T getHead() <br>
 * String toString()
 *
 * @param <T> T extends Comparable
 */
public class GSLList<T extends Comparable<? super T>> implements IGSLList<T> // extends Comparable <? super T>>
{
    //<editor-fold desc="Node definition">
    private class Node<T>
    {
        T info;
        Node<T> next = null;

        public Node(T val)
        {
            info = val;
        }
    }
    //</editor-fold>

    Node<T> head = null;

    @Override
    public void insert(T value)
    {
        Node<T> newNode = new Node<>(value);
        if (head == null)
            head = newNode;
        else
        {
            Node<T> curr = head;
            while (curr.next != null && curr.info.compareTo(value) <= 0)
            {
                curr = curr.next;
            }
            if (curr.next == null) // we are at the last node
            {
                if (curr.info.compareTo(value) <= 0) // our value exceeds info of the last node
                {
                    curr.next = newNode;
                }
                else // we are inserting before the last node
                {
                    insertBefore(curr, newNode);
                }
            }
            else // current info > value
            {
                insertBefore(curr, newNode);
            }
        }
    }

    private void insertBefore(Node<T> thisN, Node<T> thatN)
    {
        T value = thisN.info;
        thatN.next = thisN.next;
        thisN.info = thatN.info;
        thisN.next = thatN;
        thatN.info = value;

    }

    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * This is the size method, which I added into the class
     * @return size of GSLList
     */
    public int size()
    {
        int size = 0;
        Node<T> curr = head;
        if (head != null)
        {
            while (curr.next != null)
            {
                size++;
                curr = curr.next;
            }
            size++;
        }
        return size;
    }

    @Override
    public boolean remove(T value)
    {
        boolean removed = false;
        Node<T> curr = head;
        Node<T> prev = null;
        while (curr != null && curr.info.compareTo(value) < 0)
        {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null && curr.info.equals(value))
        {
            if (prev == null) // decapitation (removing the head)
                head = head.next;
            else
                prev.next = curr.next;
            removed = true;
        }
        return removed;
    }

    @Override
    public T remove(int which)
    {
        T removed = null;
        if (head != null && which >= 0)
        {
            if (which == 0)
            {
                removed = head.info;
                head = head.next;
            }
            else
            {
                Node<T> curr = head;
                Node<T> prev = null;
                while (curr != null && which-- > 0)
                {
                    prev = curr;
                    curr = curr.next;
                }
                if (which == 0)
                {
                    prev.next = curr.next;
                    removed = curr.info;
                }
            }
        }
        return removed;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("->");
        Node<T> curr = head;
        while (curr != null)
        {
            str.append(curr.info.toString()).append("->");
            curr = curr.next;
        }
        return str.toString();
    }

    public Iterator<T> iterator()
    {
        return new Iterator<>()
        {
            Node<T> curr = head;

            @Override
            public boolean hasNext()
            {
                return curr != null;
            }

            @Override
            public T next()
            {
                T value = curr.info;
                curr = curr.next;
                return value;
            }
        };

    }
}

