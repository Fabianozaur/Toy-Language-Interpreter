package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.ProgramState;
import Model.Type.*;

public class VariableDeclarationStatement implements IStatement{
    String variableName;
    IType type;
    public VariableDeclarationStatement(String variableName,IType type)
    {
        this.variableName=variableName;
        this.type=type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException {
        IStack<IStatement> stack=state.getStack();
        IDictionary<String, IValue> symbolsTable=state.getSymbolsTable();
        if(symbolsTable.variableAlreadyIn(variableName))
            throw new VariableAlreadyDefinedException("Variable "+variableName+"is already defined ");
        else
        {
            symbolsTable.add(variableName,type.defaultValue());
        }
        return null;


    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        typeEnv.add(variableName,type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString()+" "+variableName;
    }
}
