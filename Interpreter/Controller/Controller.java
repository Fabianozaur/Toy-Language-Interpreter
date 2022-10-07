package Controller;

import Model.ADT.*;
import Model.Exceptions.InterpreterException;
import Model.Exceptions.VariableAlreadyDefinedException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.IType;
import Model.Type.IValue;
import Model.Type.ReferenceValue;
import Repo.IRepository;
import Repo.Repository;
import View.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final IRepository repo;
    private String ProgramName;
    private boolean display=true;
    private ExecutorService executor;
    public Controller(String file,String name)
    {
        this.repo=new Repository(file);
        this.ProgramName=name;
    }
    public Controller(){repo=new Repository();}
    public IRepository getRepo(){
        return repo;
    }
    public String getProgramName()
    {
        return ProgramName;
    }
    public void add(ProgramState prog)
    {

        repo.add(prog);
    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates)
    {
        return programStates.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }
    public Map<Integer, IValue> conservativeGarbageCollector(java.util.List<ProgramState> programStateList,Map<Integer,IValue> heap){

        java.util.List<Integer> referenceAddresses = Stream.concat(
                getReferenceAddressesFromSymbolTables(programStateList
                        .stream()
                        .map(programState -> programState.getSymbolsTable().getValues())),
                getReferenceAddressesFromHeap(heap
                        .values()
                        .stream())
        ).collect(Collectors.toList());

        return heap
                .entrySet()
                .stream()
                .filter(element -> referenceAddresses.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Stream<Integer> getReferenceAddressesFromSymbolTables(Stream<Collection<IValue>> symbolTableValuesList){
        return symbolTableValuesList
                .flatMap(Collection::stream)
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress());
    }

    public Stream<Integer> getReferenceAddressesFromHeap(Stream<IValue> memoryHeapValues){
        return memoryHeapValues
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress());
    }
//
//    public java.util.List<Integer> getReferenceAddresses(Collection<IValue> symbolTableValues, Collection<IValue> memoryHeapValues){
//        return Stream.concat(
//                symbolTableValues
//                        .stream()
//                        .filter(value -> value instanceof ReferenceValue)
//                        .map(value -> ((ReferenceValue) value).getAddress()),
//                memoryHeapValues
//                        .stream()
//                        .filter(value -> value instanceof ReferenceValue)
//                        .map(value -> ((ReferenceValue) value).getAddress())
//        ).collect(Collectors.toList());
//    }
    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedPrograms(repo.getProgramStates());
        List<ProgramState> programStates = repo.getProgramStates();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAll(repo.getProgramStates());
            } catch (Exception e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repo.logProgramStateExecute(e);
                } catch (Exception e1) {
                    System.out.println();
                }
            });
            removeCompletedPrograms(repo.getProgramStates());
            executor.shutdownNow();
        }
    }
    public void allStep(int index) throws IOException
    {
        repo.clearLogFile();
        executor= Executors.newFixedThreadPool(2);
        List<ProgramState> programsList=removeCompletedPrograms(repo.getProgramStates());
        while (programsList.size()>0)
        {
            Heap<Integer,IValue> heap=programsList.get(0).getMemoryHeap();
            heap.setContent((HashMap<Integer, IValue>) conservativeGarbageCollector(programsList, heap.getContent()));
            try {
                oneStepForAll(programsList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            programsList=removeCompletedPrograms(repo.getProgramStates());
        }
        executor.shutdown();
        repo.setProgramStates(programsList);
    }
    public void oneStepForAll(List<ProgramState> programList) throws Exception {

        for(ProgramState program: programList){
            this.repo.logProgramStateExecute(program);
        }
        List<Callable<ProgramState>> callList = programList.stream()
                .map((ProgramState program) -> (Callable<ProgramState>) () -> {
                    return program.oneStep();
                })
                .collect(Collectors.toList());

        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try{
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(program->program!=null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        programList.addAll(newProgramList);

        //after the execution, print the PrgState List into the log file
        for(ProgramState program: programList){
            this.repo.logProgramStateExecute(program);
        }

        //Save the current programs in the repository
        this.repo.setProgramStates(programList);
    }
}
