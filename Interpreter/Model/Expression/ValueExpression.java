package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Type.IType;
import Model.Type.IValue;

public class ValueExpression implements IExpression {
    IValue value;
    public ValueExpression(IValue value)
    {
        this.value=value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, Heap<Integer, IValue> memoryHeap) throws InterpreterException {
        return value;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws InterpreterException {
        return value.getType();
    }

    @Override
    public String toString() {
        return  value.toString();
    }
}
