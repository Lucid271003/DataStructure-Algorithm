public interface AbstractQueue<E> extends Iterable<E> {
    void offer(E element); // ~ push
    E poll(); // ~ pop
    boolean isEmpty();
}
