import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements AbstractStack<E> {
    private class Node<E>{
        private E element;
        private Node<E> next;
        public Node(E element){
            this.element = element;
            this.next = null;
        }
    }

    /**
     * Data Members
     */
    private Node<E> top;
    private int size;

    /**
     * Method
     */
    public Stack(){
        this.top = null;
        this.size = 0;
    }
    @Override
    public void push(E element) {
        Node<E> newNode = new Node(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public E pop() {
        Node<E> current = top;
        top = top.next;
        current.next = null;
        size--;
        return current.element;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(top == null && size == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("[");
        Node<E> current = top;

        while (current != null){
            result.append(current.element);
            if(current.next != null){
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = top;
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

