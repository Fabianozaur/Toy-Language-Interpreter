package Model.ADT;


import Model.Exceptions.InterpreterException;
import Model.Exceptions.NonexistentKeyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap<Key,Value> implements IHeap<Key,Value>{
    private HashMap<Key, Value> heap;
    private int nextFreeAddress;
    public Heap()
    {
        this.heap=new HashMap<Key,Value>();
        nextFreeAddress=1;
    }

    @Override
    public void add(Key key, Value value) {
        this.heap.put(key,value);
        ++nextFreeAddress;
    }

    @Override
    public void update(Key key, Value value) {
        this.heap.put(key,value);
        ++nextFreeAddress;
    }

    @Override
    public void remove(Key key) throws InterpreterException {
        if(!isDefined(key))
            throw new NonexistentKeyException();
        else
            this.heap.remove(key);
    }

    @Override
    public Value lookup(Key key) {
        return this.heap.get(key);
    }

    @Override
    public Set<Key> getKey() {
        return this.heap.keySet();
    }

    @Override
    public Collection<Value> getValues() {
        return this.heap.values();
    }

    @Override
    public boolean isDefined(Key key) {
        return this.heap.containsKey(key);
    }

    @Override
    public void clearDict() {
        this.heap = new HashMap<Key, Value>();
    }

    @Override
    public int getAddress() {
        return nextFreeAddress;
    }

    @Override
    public HashMap<Key, Value> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(HashMap<Key, Value> Map) {
        this.heap=Map;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{");
        for(HashMap.Entry<Key,Value> element: this.heap.entrySet())
        {
            string.append("(");
            string.append(element.getKey());
            string.append("-> ");
            string.append(element.getValue());
            string.append(")");
            string.append(", ");
        }
        if(string.length() >1)
        {
            string.deleteCharAt(string.length()-2);
            string.deleteCharAt(string.length()-1);
        }
        string.append("}");
        return string.toString();
    }

    @Override
    public String toLogString() {
        StringBuilder string =new StringBuilder();
        for(HashMap.Entry<Key,Value> element : this.heap.entrySet())
        {
            string.append(element.getKey());
            string.append("-> ");
            string.append(element.getValue());
            string.append('\n');
        }
        return string.toString();

    }
}
