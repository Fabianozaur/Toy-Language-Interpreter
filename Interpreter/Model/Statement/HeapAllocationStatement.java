package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.ReferenceType;
import Model.Type.ReferenceValue;

import java.io.IOException;

public class HeapAllocationStatement implements IStatement {
    private final String variableName;
    private final IExpression expression;
    public HeapAllocationStatement(String variableName,IExpression expression)
    {
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException, IOException {
        Dictionary<String, IValue> symbolsTable=(Dictionary<String, IValue>) state.getSymbolsTable();
        Heap<Integer,IValue> memoryHeap=(Heap<Integer, IValue>) state.getMemoryHeap();
        if (symbolsTable.variableAlreadyIn(variableName)){
            IValue variableValue = symbolsTable.lookup(variableName);

            if (variableValue.getType() instanceof ReferenceType){
                ReferenceValue variableReferenceValue = (ReferenceValue) variableValue;
                IType variableLocationType = variableReferenceValue.getLocationType();
                IValue expressionValue = expression.evaluate(symbolsTable, memoryHeap);

                if (expressionValue.getType().equals(variableLocationType)){
                    int allocatedAddress = memoryHeap.getAddress();
                    memoryHeap.add(allocatedAddress, expressionValue);
                    symbolsTable.update(variableName, new ReferenceValue(allocatedAddress, variableLocationType));
                }
                else
                    throw new InterpreterException("Expression type does not match variable type!");
            }
            else
                throw new InterpreterException("Expression type is not reference type!");
        }
        else
            throw new InterpreterException("Variable " + variableName + " is not defined!");


        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typevar=typeEnv.lookup(variableName);
        IType typeexp=expression.typecheck(typeEnv);
        if(typevar.equals(new ReferenceType(typeexp)))
        {
            return typeEnv;
        }
        else
            throw new InterpreterException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
