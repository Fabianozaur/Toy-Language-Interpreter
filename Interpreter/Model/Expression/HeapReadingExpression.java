package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.ReferenceType;
import Model.Type.ReferenceValue;

public class HeapReadingExpression implements IExpression {
    private final IExpression expression;
    public HeapReadingExpression(IExpression expression)
    {
        this.expression=expression;
    }
    @Override
    public IValue evaluate(IDictionary<String,IValue> symbolsTable, Heap<Integer,IValue> memoryHeap) throws InterpreterException
    {
        IValue value = expression.evaluate(symbolsTable,memoryHeap);
        if(value.getType() instanceof ReferenceType)
        {
            ReferenceValue referenceValue=(ReferenceValue) value;
            if(memoryHeap.isDefined(referenceValue.getAddress()))
                return memoryHeap.lookup(referenceValue.getAddress());
            else
                throw new InterpreterException("Address is not in memory");
        }
        else
            throw new InterpreterException("Expression type is not reference type - reading");
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws InterpreterException {
        IType type=expression.typecheck(typeEnv);
        if(type instanceof ReferenceType)
        {
            ReferenceType reft=(ReferenceType) type;
            return reft.getIn();
        }
        else
            throw new InterpreterException("the reading heap argument is not a Reference Type");
    }


    public String toString() {
        return "readHeap("+expression+")";
    }
}
