package Model.ADT;

import com.sun.jdi.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileTable<Key,Value> implements IDictionary<Key,Value>{

    HashMap<Key,Value> filetable;
    public FileTable()
    {
        this.filetable=new HashMap<>();
    }

    @Override
    public boolean variableAlreadyIn(Key key) {
        return filetable.containsKey(key);
    }

    @Override
    public HashMap<Key, Value> getDict() {
        return filetable;
    }

    @Override
    public void add(Key key, Value value) {
        filetable.put(key, value);
    }

    @Override
    public Value lookup(Key key) {
        return filetable.get(key);
    }

    @Override
    public IDictionary<Key, Value> clone() {
        var clonedDictionary=filetable.clone();
        var newDictionary=new Dictionary<Key,Value>();
        newDictionary.dictionary=(HashMap<Key,Value>) clonedDictionary;
        return newDictionary;
    }

    @Override
    public boolean isDefined(Key key) {
        return this.filetable.containsKey(key);
    }

    @Override
    public void update(Key key, Value value) {
        add(key, value);
    }

    @Override
    public void delete(Key key){ this.filetable.remove(key);}

    @Override
    public Collection<Value> getValues() {
        return this.filetable.values();
    }

    @Override
    public String toLogString()
    {
        StringBuilder result =new StringBuilder();
        for(Key key:filetable.keySet())
        {
            result.append(key.toString()).append("\n");
        }
        return result.toString();
    }
}
