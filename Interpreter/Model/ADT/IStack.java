package Model.ADT;

import java.util.Deque;

public interface IStack<T> {
    void push(T t);
    T pop();
    boolean isEmpty();
    String toLogString();
    Deque<T> getStack();
}
