import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E> implements AbstractQueue<E> {
    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    /**
     * Data Members
     */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Methods
     */

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if(tail == null){
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E poll() {
        if(head == null){
            throw new NoSuchElementException();
        }
        Node<E> current = head;
        if(size != 1){
            head = head.next;
            current.next = null;
        } else{
            head = tail = null;
        }
        size--;
        return current.element;
    }

    @Override
    public boolean isEmpty() {
        if(head == null && tail == null){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Node<E> current = head;

        while (current != null){
            result.append(current.element);
            if(current.next != null){
                result.append("\n");
            }
            current = current.next;
        }
        return result.toString();
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            @Override
            public boolean hasNext() {
                if(isEmpty()){
                    throw new NoSuchElementException();
                }
                if(current == null) {
                    throw new NoSuchElementException();
                }
                return true;
            }

            @Override
            public E next() {
                if(current == null) {
                    throw new NoSuchElementException();
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}
