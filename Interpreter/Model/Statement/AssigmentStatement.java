package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Exceptions.AssigmentVariablesDoNotMatch;

public class AssigmentStatement implements IStatement{
    private String id;
    private IExpression expression;
    public AssigmentStatement(String id,IExpression expression)
    {
        this.expression=expression;
        this.id=id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException {
        IStack<IStatement> stack=state.getStack();
        IDictionary<String, IValue> symbolsTable= state.getSymbolsTable();
        Heap<Integer, IValue> memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();

        if(symbolsTable.variableAlreadyIn(id))
        {
            IValue value=expression.evaluate(symbolsTable,memoryHeap);
            IType type=symbolsTable.lookup(id).getType();
            if(value.getType().equals(type))
                symbolsTable.update(id,value);
            else
                throw new AssigmentVariablesDoNotMatch("The type of variable "+id+" and the type of the assigned expression do not match");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typevar=typeEnv.lookup(id);
        IType typeexp=expression.typecheck(typeEnv);
        if(typevar.equals(typeexp))
            return typeEnv;
        else
            throw new AssigmentVariablesDoNotMatch("assigment : right hand side and left hand side have different type");

    }

    @Override
    public String toString() {
        return id+" = "+expression.toString() ;
    }
}
