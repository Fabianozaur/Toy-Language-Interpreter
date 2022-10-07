package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Type.IType;
import Model.Type.IValue;

public class VariableExpression implements IExpression {
    String variable_name;

    public VariableExpression(String variable_name){
        this.variable_name = variable_name;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, Heap<Integer, IValue> memoryHeap) throws InterpreterException {
        return table.lookup(variable_name);
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws InterpreterException {
        return typeEnv.lookup(variable_name);
    }

    @Override
    public String toString() {
        return variable_name;
    }
}