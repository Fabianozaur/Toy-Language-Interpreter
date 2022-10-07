package Model.Expression;

import Model.ADT.Heap;
import Model.ADT.IDictionary;
import Model.Exceptions.InterpreterException;
import Model.Exceptions.NotABoolException;
import Model.Exceptions.NotAnIntException;
import Model.Type.*;

public class LogicExpression implements IExpression{
    IExpression lft;
    IExpression rgt;
    String operator;
    public LogicExpression(String op,IExpression left,IExpression right)
    {
        lft=left;
        rgt=right;
        operator=op;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, Heap<Integer, IValue> memoryHeap) throws InterpreterException {
        IValue leftValue,rightValue;
        leftValue=lft.evaluate(table,memoryHeap);
        if(leftValue.getType().equals(new BoolType())) {
            rightValue = rgt.evaluate(table,memoryHeap);
            if (rightValue.getType().equals(new BoolType())) {
                BoolValue leftBool = (BoolValue) leftValue;
                BoolValue rightBool = (BoolValue) rightValue;
                boolean opRight, opLeft;
                opLeft = leftBool.getValue();
                opRight = rightBool.getValue();
                switch (operator) {
                    case "&" -> {
                        return new BoolValue(opLeft && opRight);
                    }
                    case "|" -> {
                        return new BoolValue(opLeft || opRight);
                    }
                }
            } else
                throw new NotABoolException("Right-hand side operator " + rightValue.toString() + " is not a bool");
        }else if (leftValue.getType().equals(new IntType()))
        {
            rightValue= rgt.evaluate(table,memoryHeap);
            if(rightValue.getType().equals(new IntType()))
            {
                IntValue leftInt=(IntValue) leftValue;
                IntValue rightInt=(IntValue) rightValue;
                int lft,rgt;
                lft=leftInt.getValue();
                rgt=rightInt.getValue();
                switch (operator)
                {
                    case "<"->{
                        return new BoolValue(lft<rgt);
                    }
                    case "<=" -> {
                        return new BoolValue(lft <= rgt);
                    }
                    case "==" -> {
                        return new BoolValue(lft == rgt);
                    }
                    case "!=" -> {
                        return new BoolValue(lft != rgt);
                    }
                    case ">" -> {
                        return new BoolValue(lft > rgt);
                    }
                    case ">=" -> {
                        return new BoolValue(lft >= rgt);
                    }
                }

            }else
                throw new NotAnIntException(" Right-hand side operator " + rightValue.toString() + " is not an int");
        } else
            throw new NotABoolException("Left-hand side operator " + leftValue.toString() + " is not a bool or an int ");
        return null;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws InterpreterException {
        IType type1,type2;
        type1=lft.typecheck(typeEnv);
        type2=rgt.typecheck(typeEnv);
        if (!type1.equals(type2))
            throw new InterpreterException("Operands type are different");

        return new BoolType();
    }

    @Override
    public String toString() {
        return lft.toString()+" "+operator+" "+rgt.toString();

    }
}
