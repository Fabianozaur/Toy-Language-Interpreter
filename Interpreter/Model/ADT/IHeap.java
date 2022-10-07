package Model.ADT;

import Model.Exceptions.InterpreterException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface IHeap<Key,Value> {
    void add(Key key,Value value);
    void update(Key key, Value value);
    void remove(Key key) throws InterpreterException;
    Value lookup(Key key);
    public Set<Key> getKey();
    public Collection<Value> getValues();
    boolean isDefined(Key key);
    void clearDict();
    String toString();
    String toLogString();
    int getAddress();
    void setContent(HashMap<Key,Value> map);
    HashMap<Key,Value> getContent();


}
