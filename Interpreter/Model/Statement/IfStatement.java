package Model.Statement;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Exceptions.NotABoolException;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.BoolValue;
import Model.Type.IType;
import Model.Type.IValue;

import javax.imageio.plugins.tiff.TIFFTag;

public class IfStatement implements IStatement {
    IExpression expression;
    IStatement statementWasTrue;
    IStatement statementWasFalse;

    public IfStatement(IExpression expression,IStatement ifTrue,IStatement ifFalse)
    {
        this.expression=expression;
        this.statementWasFalse=ifFalse;
        this.statementWasTrue=ifTrue;
    }

    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDefinedException {
        IDictionary<String, IValue> symbolsTable= state.getSymbolsTable();
        Heap<Integer, IValue> memoryHeap = (Heap<Integer, IValue>) state.getMemoryHeap();
        var stack=state.getStack();
        IValue result=expression.evaluate(symbolsTable,memoryHeap);
        if(result instanceof BoolValue)
        {
            boolean resu=((BoolValue)result).getValue();
            if(resu)
                stack.push(statementWasTrue);
            else
                stack.push(statementWasFalse);
        }
        else throw new NotABoolException("If statement needs to be boolean duuh");
        return null;

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typeexp=expression.typecheck(typeEnv);
        if(typeexp.equals(new BoolType()))
        {
            statementWasTrue.typecheck(typeEnv.clone());
            statementWasFalse.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new InterpreterException("The condition of if has not the tyoe bool");
    }

    @Override
    public String toString() {
        return "(if("+expression.toString()+")then("+statementWasTrue.toString()+")else("+statementWasFalse.toString()+"))";
    }
}
