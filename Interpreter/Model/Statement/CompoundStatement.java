package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.ProgramState;
import Model.Type.IType;

import java.time.chrono.IsoChronology;

public class CompoundStatement implements IStatement {
    IStatement left;
    IStatement right;
    public CompoundStatement(IStatement left, IStatement right)
    {
        this.left=left;
        this.right=right;
    }
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException
    {
        IStack<IStatement> stack=state.getStack();
        stack.push(right);
        stack.push(left);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        IDictionary<String,IType> typeEnv1=left.typecheck(typeEnv);
        IDictionary<String,IType> typeEnv2=right.typecheck(typeEnv1);
        return typeEnv2;
    }

    public String toString()
    {
        return left.toString()+" | "+ right.toString();
    }
}
