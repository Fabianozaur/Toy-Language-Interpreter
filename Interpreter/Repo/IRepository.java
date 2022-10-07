package Repo;

import java.util.List;
import Model.ProgramState;
import Model.Statement.PrintStatement;

public interface IRepository {
    void add(ProgramState programState);
    void logProgramStateExecute(ProgramState porgState);
    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> newProgState);
    int getSize();
    void clearLogFile();

}
