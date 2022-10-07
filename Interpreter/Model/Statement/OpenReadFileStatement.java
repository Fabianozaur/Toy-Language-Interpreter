package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.StringType;
import Model.Type.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement{
    IExpression expression;
    public OpenReadFileStatement(IExpression expression)
    {
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        var fileTable =state.getFileTable();
        var symbolsTable=state.getSymbolsTable();
        Heap<Integer, IValue> memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();
        IValue value=expression.evaluate(symbolsTable,memoryHeap);
        if(value.getType() instanceof StringType)
        {
            String filePath=((StringValue) value).getValue();
            if (!fileTable.variableAlreadyIn(filePath)) {
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    fileTable.add(filePath, bufferedReader);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("File not found!");
                }
            } else {
                throw new RuntimeException("This file is already in the file-table");
            }
        }else
        {
            throw new RuntimeException("the expression is not a string type");
        }
        return null;
    }
    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType expression_type = expression.typecheck(typeEnvironment);
        if(expression_type.equals(new StringType()))
            return typeEnvironment;
        else
            throw  new RuntimeException("The expression cannot be evaluated to a string!");
    }

    @Override
    public String toString() {
        return "OpenFile("+expression.toString()+");";
    }
}
