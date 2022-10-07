package Model.ADT;

import java.util.Collection;
import java.util.HashMap;

public interface IDictionary<Key,Value> {
    boolean variableAlreadyIn(Key key);
    void add(Key key,Value value);
    Value lookup(Key key);
    void update(Key key,Value value);
    void delete(Key key);
    public Collection<Value> getValues();
    IDictionary<Key, Value> clone();
    boolean isDefined(Key key);
    HashMap<Key,Value> getDict();
    String toLogString();

}
