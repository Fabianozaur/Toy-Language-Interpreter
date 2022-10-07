package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.ADT.IList;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IValue;

public class PrintStatement implements IStatement{
    IExpression expression;
    public PrintStatement(IExpression expression)
    {
        this.expression=expression;
    }
    public String toString()
    {
        return "print("+expression.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException {
        IList<IValue> output = state.getOutput();
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        Heap<Integer, IValue> memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();

        output.add(expression.evaluate(symbolsTable,memoryHeap));
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }
}
