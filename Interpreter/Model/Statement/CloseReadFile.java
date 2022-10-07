package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.StringType;
import Model.Type.StringValue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements IStatement{
    IExpression expression;
    public CloseReadFile(IExpression expression)
    {
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException, IOException {
        var systemTable=state.getSymbolsTable();
        Heap<Integer, IValue> memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();

        IValue stringTester=expression.evaluate(systemTable,memoryHeap);
        if(!(stringTester instanceof StringValue))
        {
            throw new RuntimeException("Expression: "+expression.toString()+" cannot be eval to a string");
        }
        String fileName=((StringValue) stringTester).getValue();
        var fileTable=state.getFileTable();
        if(!fileTable.variableAlreadyIn(fileName))
            throw new RuntimeException("File not found!");
        BufferedReader reader =fileTable.lookup(fileName);
        reader.close();
        fileTable.delete(fileName);
        return null;
    }
    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType expression_type = expression.typecheck(typeEnvironment);
        if(expression_type.equals(new StringType()))
            return typeEnvironment;

        throw  new RuntimeException("The expression cannot be evaluated to a string!");
    }

    @Override
    public String toString() {
        return "closeFile("+expression.toString()+");";
    }
}
