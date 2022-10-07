package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Type.IType;
import Model.Type.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String,IValue> table, Heap<Integer, IValue> memoryHeap) throws InterpreterException;
    IType typecheck(IDictionary<String,IType> typeEnv) throws InterpreterException;
}
