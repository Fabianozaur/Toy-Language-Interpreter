package Model.ADT;

import java.util.ArrayList;

public interface IList<T> {
    void add(T t);
    String toLogString();
    ArrayList<T> getList();
}
