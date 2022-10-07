package Model.ADT;

import java.util.ArrayList;

public class List<T> implements IList<T>{
    ArrayList<T> list = new ArrayList<>();
    @Override
    public void add(T t)
    {
        list.add(t);
    }

    @Override
    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("[");
        for (T t: list)
        {
            string.append(t.toString()).append(", ");
        }
        string.append("]");
        return string.toString();
    }

    @Override
    public String toLogString() {
        StringBuilder string =new StringBuilder();
        for (T t: list)
        {
            string.append(t.toString()).append("\n");
        }
        return string.toString();

    }
}
