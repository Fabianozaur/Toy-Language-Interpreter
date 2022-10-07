package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

public class ReadFromFileStatement implements IStatement {
    IExpression expression;
    String variableName;

    public ReadFromFileStatement(IExpression expression,String variableName)
    {
        this.expression=expression;
        this.variableName=variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws IOException {
        var symbolsTable=state.getSymbolsTable();
        var memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();

        if(!symbolsTable.variableAlreadyIn(variableName))
            throw new RemoteException("Variable "+variableName+" is not defined");
        IValue stringTester=expression.evaluate(symbolsTable,memoryHeap);
        if(!(stringTester instanceof StringValue))
            throw new RuntimeException("Expression: " + expression.toString() + " cannot be evaluated to a string!");
        String fileName=((StringValue) stringTester).getValue();
        var fileTable=state.getFileTable();
        if(!fileTable.variableAlreadyIn(fileName))
            throw new RuntimeException("File not found");
        BufferedReader buff=fileTable.lookup(fileName);
        String line=buff.readLine();
        int newVal;
        if(line==null)
            newVal=0;
        else
            newVal=Integer.parseInt(line);
        symbolsTable.update(variableName,new IntValue(newVal));
        return null;
    }
    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) {
        IType variableType = typeEnv.lookup(variableName),
                expression_type = expression.typecheck(typeEnv);
        if (variableType.equals(new IntType()))
            if (expression_type.equals(new StringType()))
                return typeEnv;
            else
                throw new RuntimeException("the expression cannot be evaluated to a string!");
        else
            throw new RuntimeException("The variable is not an int ");
    }

    @Override
    public String toString() {
        return "ReadFile("+expression.toString()+");";
    }
}
