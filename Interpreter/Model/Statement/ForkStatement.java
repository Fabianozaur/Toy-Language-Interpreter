package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.Stack;
import Model.Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IType;

import java.io.IOException;

public class ForkStatement implements IStatement{
    private final IStatement statement;
    public ForkStatement(IStatement statement)
    {
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        return new ProgramState(
                new Stack<IStatement>(),
                state.getSymbolsTable().clone(),
                state.getOutput(),
                state.getFileTable(),
                state.getMemoryHeap(),
                statement
        );
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        statement.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork("+statement+")";
    }
}
