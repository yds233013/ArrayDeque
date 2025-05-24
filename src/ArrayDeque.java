import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int start;
    private int back;
    private T[] item; // circularArrayDeque
    private static final int RESIZEQUOTIENT = 16;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        start = 4;
        back = 5;
        size = 0;
    }

    public static void main(String[] args) {
    }


    private void resize(int capac) {
        T[] a = (T[]) new Object[capac];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        start = capac - 1;
        back = size;
        item = a;
    }

    @Override
    public void addFirst(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        size += 1;
        while (start > 0) {
            item[start] = x;
            start -= 1;
            return;
        }
        item[start] = x;
        start = item.length - 1;

    }

    @Override
    public void addLast(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        size += 1;
        while (back < item.length - 1) {
            item[back] = x;
            back += 1;
            return;
        }
        item[back] = x;
        back = 0;
    }

    @Override
    public List<T> toList() {
        List<T> lastList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            lastList.add(get(i));
        }
        return lastList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        while (size < item.length * 0.25 && item.length > RESIZEQUOTIENT) {
            resize(item.length / 4);
        }
        while (size > 0) {
            T value;
            if (start != item.length - 1) {
                value = item[start + 1];
                item[start + 1] = null;
                start += 1;
            } else {
                value = item[0];
                item[0] = null;
                start = 0;
            }
            size -= 1;
            return value;
        }
        return null;

    }

    @Override
    public T removeLast() {
        while (size < item.length * 0.25 && item.length > RESIZEQUOTIENT) {
            resize(item.length / 4);
        }
        while (size > 0) {
            T value;
            if (back != 0) {
                value = item[back - 1];
                item[back - 1] = null;
                back -= 1;
            } else {
                value = item[item.length - 1];
                item[item.length - 1] = null;
                back = item.length - 1;
            }
            size -= 1;
            return value;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0 || size == 0) {
            return null;
        }
        return item[(start + 1 + index) % item.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

}




