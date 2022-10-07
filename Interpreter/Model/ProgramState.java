package Model;

import Model.ADT.*;
import Model.Exceptions.EmptyProgramStateException;
import Model.Exceptions.InterpreterException;
import Model.Statement.IStatement;
import Model.Type.IType;
import Model.Type.IValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;

public class ProgramState {
    private static int currentId;
    private final int programId;
    Stack<IStatement> stack;
    Dictionary<String, IValue> symbolsTable;
    IList<IValue> output;
    IStatement statement;
    Dictionary<String, BufferedReader> fileTable;
    Heap<Integer, IValue> memoryHeap;

    public ProgramState(Stack<IStatement> stack, Dictionary<String, IValue> symbolsTable, IList<IValue> output, Dictionary<String, BufferedReader> fileTable, Heap<Integer, IValue> memory, IStatement statement) {
        this.stack = stack;
        this.symbolsTable = symbolsTable;
        this.output = output;
        this.statement = statement;
        this.stack.push(statement);
        this.fileTable=fileTable;
        this.memoryHeap=memory;
        this.programId=getNewId();
    }

    private static synchronized int getNewId()
    {
        return ++currentId;
    }

    public int getProgramId() { return programId;}

    public Boolean isNotCompleted() { return !stack.isEmpty();}

    public IStack<IStatement> getStack() {
        return stack;
    }

    public String toString() {
        return "Execution Stack: " + stack.toString() + "\n" +
                "Symbols Table:  " + symbolsTable.toString() + "\n" +
                "Output:         " + output.toString() + "\n"+
                "File Table:     " + fileTable.toString()+"\n"+
                "Memory Heap:    " + memoryHeap.toString()+"\n-----------";

    }
    public void setFileTable(Dictionary<String,BufferedReader> fileTable){
        this.fileTable=fileTable;
    }
    public Dictionary<String,BufferedReader> getFileTable()
    {
        return fileTable;
    }
    public Dictionary<String,IValue> getSymbolsTable()
    {
        return symbolsTable;
    }
    public IList<IValue> getOutput()
    {

        return output;
    }
    public IStatement getStatement() {

        return statement;
    }
    public Heap<Integer,IValue> getMemoryHeap(){
        return memoryHeap;
    }

    public void setMemoryHeap(Heap<Integer,IValue> memoryHeap)
    {
        this.memoryHeap=memoryHeap;
    }

    public void setStack(Stack<IStatement> stack)
    {
        this.stack=stack;
    }
    public void setSymbolsTable(Dictionary<String, IValue> symbolsTable)
    {
        this.symbolsTable=symbolsTable;
    }
    public void setOutput(IList<IValue> output)
    {
        this.output=output;
    }
    public void setStatement(IStatement statement)
    {
        this.statement=statement;
    }

    public ProgramState oneStep() throws InterpreterException, IOException {
        if(stack.isEmpty())
            throw new EmptyProgramStateException("bla bla bla nu ai ceva in stack ");
        IStatement crtStmt = stack.pop();
        return crtStmt.execute(this);

    }

    public String toLogString() {
        return "Execution Stack:\n" + stack.toLogString() +
                "Symbols Table:\n" + symbolsTable.toLogString() +
                "Files Table:\n" + fileTable.toLogString() +
                "Output:\n" + output.toLogString()+
                "Memory Stack:\n"+memoryHeap.toLogString()+
                "-------------------------";
    }
}
