package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.InterpreterException;
import Model.Exceptions.NotAnIntException;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.IntType;
import Model.Type.IntValue;

public class ArithmeticExpression implements IExpression
{
    IExpression lft;
    IExpression rgt;
    char operator; //1 plus 2 minus 3 multiply 4 divide
    public ArithmeticExpression(char op,IExpression left,IExpression right)
    {
        lft=left;
        rgt=right;
        operator=op;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, Heap<Integer, IValue> memoryHeap) throws InterpreterException {
        IValue left,right;
        left=lft.evaluate(table,memoryHeap);
        if(left.getType().equals(new IntType()))
        {
            right=rgt.evaluate(table,memoryHeap);
            if(right.getType().equals(new IntType()))
            {
                IntValue leftInt=(IntValue) left;
                IntValue rightInt=(IntValue) right;
                int opRight,opLeft;
                opLeft=leftInt.getValue();
                opRight=rightInt.getValue();
                switch(this.operator)
                {
                    case '+' -> {
                        return new IntValue(opLeft+opRight);
                    }
                    case '-' -> {
                        return new IntValue(opLeft-opRight);
                    }
                    case '*' -> {
                        return new IntValue(opLeft*opRight);
                    }
                    case '/' -> {
                        if (opRight==0)
                            throw new DivisionByZeroException("Trying to divide by zero");
                            else{
                        return new IntValue(opLeft/opRight);
                            }
                    }
                }
            } else throw new NotAnIntException("Right-hand side operator " + right.toString() + " is not an int");
        } else throw new NotAnIntException("Left-hand side operator " + left.toString() + " is not an int");

    return new IntValue();
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws InterpreterException {
        IType type1,type2;
        type1=lft.typecheck(typeEnv);
        type2=rgt.typecheck(typeEnv);
        if(type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
            {
                return new IntType();
            }
            else
                throw new NotAnIntException("second operand is not an integer");
        }
        else
            throw new NotAnIntException("first operand is not an interger");
    }

    @Override
    public String toString() {
        return lft.toString()+" "+operator+" "+rgt.toString();

    }
}
