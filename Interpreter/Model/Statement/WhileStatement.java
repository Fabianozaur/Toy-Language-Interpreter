package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.ADT.Stack;
import Model.Exceptions.NotABoolException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.BoolValue;
import Model.Type.IType;
import Model.Type.IValue;

import java.io.IOException;
import java.io.InterruptedIOException;

public class WhileStatement implements IStatement{
    private final IExpression expression;
    private final IStatement statement;

    public WhileStatement(IExpression expression,IStatement statement)
    {
        this.expression=expression;
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException, IOException {
        Stack<IStatement> stack=(Stack<IStatement>) state.getStack();
        Dictionary<String, IValue> symbolsTable=(Dictionary<String, IValue>) state.getSymbolsTable();
        Heap<Integer,IValue> memoryHeap =(Heap<Integer, IValue>) state.getMemoryHeap();
        IValue condition =expression.evaluate(symbolsTable,memoryHeap);
        if(condition.getType().equals(new BoolType()))
        {
            BoolValue boolcond=(BoolValue) condition;
            if(boolcond.getValue())
            {
                stack.push(new CompoundStatement(statement, new WhileStatement(expression,statement)));

            }

        }
        else
            throw  new InterruptedIOException();
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typeexp=expression.typecheck(typeEnv);
        if(typeexp.equals(new BoolType()))
        {
            statement.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new NotABoolException("The condition of while was not a bool one");
    }

    @Override
    public String toString() {
        return "(while(" + this.expression.toString() + ") {" + this.statement.toString() + "} )";
    }
}
