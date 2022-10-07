package Repo;

import Model.Exceptions.InterpreterException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    List<ProgramState> programs;
    int currentProgram;
    private final String logFilePath;

    public Repository(String logFilePath)
    {
        this.programs=new ArrayList<>();
        this.logFilePath=logFilePath;
    }
    public Repository(){this.programs=new ArrayList<>();
    this.logFilePath="";}

    @Override
    public int getSize() {
        return programs.size();
    }

    @Override
    public void add(ProgramState programState) {
        this.programs.add(programState);
    }

    @Override
    public List<ProgramState> getProgramStates()
    { return programs; }

    @Override
    public void logProgramStateExecute(ProgramState progState) {
        PrintWriter logFilePath=null;
        try{
            logFilePath=new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath,true)));
            logFilePath.println("Programs: "+progState.getProgramId());
            logFilePath.println();
            logFilePath.println("ExectionStack: ");
            logFilePath.println(progState.getStack().toLogString());
            logFilePath.println("SymbolStable: ");
            logFilePath.println(progState.getSymbolsTable().toLogString());
            logFilePath.println("Output: ");
            logFilePath.println(progState.getOutput().toLogString());
            logFilePath.println("FileTable: ");
            logFilePath.println(progState.getFileTable().toLogString());
            logFilePath.println("MemoryHeap: ");
            logFilePath.println(progState.getMemoryHeap().toLogString());
            logFilePath.println();
            logFilePath.close();

           }
        catch (Exception exception)
        {
            throw new InterpreterException("File not gound");
        }
    }

    @Override
    public void setProgramStates(List<ProgramState> newProgramStates)
    {
        this.programs = newProgramStates;
    }


    @Override
    public void clearLogFile() {
        PrintWriter logFile=null;
        try{
            logFile=new PrintWriter(this.logFilePath);
            logFile.println();
            logFile.close();
        }
        catch (Exception exception)
        {
            throw new InterpreterException("File not found");
        }
    }
}
