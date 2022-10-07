package Model.ADT;

import Model.Exceptions.NonexistentKeyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Dictionary<Key,Value> implements IDictionary<Key,Value>
{
    HashMap<Key,Value> dictionary;
    public Dictionary()
    {
        dictionary=new HashMap<Key, Value>();
    }
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<Key, Value> entry : dictionary.entrySet())
        {
            string.append(entry.getKey()).append(" -> ").append(entry.getValue()).append(";");
        }
        return "{" + string + "}";
    }

    @Override
    public HashMap<Key, Value> getDict() {
        return dictionary;
    }

    @Override
    public boolean variableAlreadyIn(Key key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void add(Key key, Value value) {
        dictionary.put(key, value);
    }

    @Override
    public Value lookup(Key key) {
        return dictionary.get(key);
    }

    @Override
    public void update(Key key, Value value) {
        add(key, value);
    }

    @Override
    public void delete(Key key)
    {
        if(!isDefined(key))
            throw new NonexistentKeyException();
        else
            this.dictionary.remove(key);
    }

    @Override
    public boolean isDefined(Key key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public Dictionary<Key, Value> clone() {
        var clonedDictionary=dictionary.clone();
        var newDictionary=new Dictionary<Key,Value>();
        newDictionary.dictionary=(HashMap<Key,Value>) clonedDictionary;
        return newDictionary;
    }

    @Override
    public Collection<Value> getValues() {
        return this.dictionary.values();
    }

    @Override
    public String toLogString()
    {
        StringBuilder result =new StringBuilder();
        for(Key key:dictionary.keySet())
        {
            result.append(key.toString()).append(" -> ").append(dictionary.get(key).toString()).append('\n');
        }
        return result.toString();
    }

}
