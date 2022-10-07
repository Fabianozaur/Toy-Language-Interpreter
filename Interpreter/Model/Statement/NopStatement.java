package Model.Statement;

import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.ProgramState;
import Model.Type.IType;

public class NopStatement implements IStatement
{
    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException {
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        return null;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
