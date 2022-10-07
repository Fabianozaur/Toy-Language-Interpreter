package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.ProgramState;
import Model.Type.IType;

import java.io.IOException;

public interface IStatement {
    ProgramState execute (ProgramState state) throws RuntimeException, IOException;
    IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws RuntimeException;
}
