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

import java.beans.Expression;
import java.io.IOException;

public class HeapWritingStatement implements IStatement{
    private final String variableName;
    private final IExpression expression;

    public HeapWritingStatement(String variableName, IExpression expression)
    {
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException, IOException {
        var symbolTable=(Dictionary<String, IValue>) state.getSymbolsTable();
        var memoryHeap=(Heap<Integer,IValue>) state.getMemoryHeap();
        if(symbolTable.variableAlreadyIn(variableName))
        {
            IValue value=symbolTable.lookup(variableName);
            if(value.getType() instanceof ReferenceType)
            {
                ReferenceValue variableReference=(ReferenceValue) value;
                int address=variableReference.getAddress();
                if(memoryHeap.isDefined(address))
                {
                    IValue expression=this.expression.evaluate(symbolTable,memoryHeap);
                    if(expression.getType().equals(variableReference.getLocationType()))
                    {
                        memoryHeap.update(address,expression);
                    }
                    else
                        throw new InterpreterException("Variable type and expression type do not match");
                }
                else
                    throw new InterpreterException("Address is not a key in memory heap");
            }
            else
                throw new InterpreterException("Variable type is not reference type");
        }
        else
            throw new InterpreterException("Variable "+variableName+"is not defined");
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
            throw new InterpreterException("right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {

        return "writeHeap(" + variableName + ", " + expression.toString() + ")";
    }
}

