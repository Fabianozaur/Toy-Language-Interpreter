package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.*;
import Repo.IRepository;
import Repo.Repository;

import java.io.BufferedReader;
import java.util.Arrays;

public class Interpreter {
    public static void main(String[] args) {

        Controller controller1=newController(example1(),"example1.txt","example1");
        Controller controller2=newController(example2(),"example2.txt","example2");
        Controller controller3=newController(example3(),"example3.txt","example3");
        Controller controller4=newController(example4(),"example4.txt","example4");
        Controller controller5=newController(example5(),"example5.txt","example5");
        Controller controller6=newController(example6(),"example6.txt","example6");
        Controller controller7=newController(example7(),"example7.txt","example7");
        Controller controller8=newController(example8(),"example8.txt","example8");
        Controller controller9=newController(example9(),"example9.txt","example9");
        Controller controller10=newController(example10(),"example10.txt","example10");
        Controller controller11=newController(example11(),"example11.txt","example11");


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunCommand("1", example1().toString(), controller1));
        menu.addCommand(new RunCommand("2", example2().toString(), controller2));
        menu.addCommand(new RunCommand("3", example3().toString(), controller3));
        menu.addCommand(new RunCommand("4", example4().toString(), controller4));
        menu.addCommand(new RunCommand("5", example5().toString(), controller5));
        menu.addCommand(new RunCommand("6", example6().toString(), controller6));
        menu.addCommand(new RunCommand("7", example7().toString(), controller7));
        menu.addCommand(new RunCommand("8", example8().toString(), controller8));
        menu.addCommand(new RunCommand("9", example9().toString(), controller9));
        menu.addCommand(new RunCommand("10", example10().toString(), controller10));
        menu.addCommand(new RunCommand("11", example11().toString(), controller11));

        menu.show();
    }
    public static Controller newController(IStatement statement,String fileName,String programName)
    {
        try {
            IDictionary<String, IType> typeEnv = new Dictionary<String, IType>();
            statement.typecheck(typeEnv);
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(1);
        }
        Model.ADT.Stack<IStatement> executionStack = new Stack<>();
        Dictionary<String, IValue> symbolsTable = new Dictionary<>();
        List<IValue> outputL = new List<>();
        Dictionary<String, BufferedReader> fileTable = new Dictionary<>();
        Heap<Integer, IValue> memoryHeap = new Heap<>();
        ProgramState programState = new ProgramState(executionStack, symbolsTable, outputL, fileTable, memoryHeap, statement);
        Controller controller = new Controller(fileName,programName);
        controller.add(programState);
        return controller;



    }
    public static IStatement makeIt(IStatement... statementList){
        if(statementList.length==0) {
            return new NopStatement();
        }
        return new CompoundStatement(statementList[0],
                makeIt(Arrays.copyOfRange(statementList,1, statementList.length)));
    }

    private static IStatement example1() {
        return new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssigmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
    }

    private static IStatement example2() {
        return new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssigmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssigmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
    }

    private static IStatement example3() {
        return new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssigmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssigmentStatement("v", new ValueExpression(new IntValue(2))), new AssigmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
    }

    private static IStatement example4() {
        return new CompoundStatement(new VariableDeclarationStatement("file_name", new StringType()),
                new CompoundStatement(new AssigmentStatement("file_name", new ValueExpression(new StringValue("src/Model/File/test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("file_name")),
                                new CompoundStatement(new VariableDeclarationStatement("var", new IntType()),
                                        new CompoundStatement(new ReadFromFileStatement(new VariableExpression("file_name"), "var"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("var")),
                                                        new CompoundStatement(new ReadFromFileStatement(new VariableExpression("file_name"), "var"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("var")),
                                                                        new CloseReadFile(new VariableExpression("file_name"))))))))));
    }


    public static IStatement example5(){
        //int v; v=4; (while (v>0) print(v); v=v-1); print(v)
        return makeIt(new VariableDeclarationStatement("v", new IntType()),
                new AssigmentStatement("v", new ValueExpression(new IntValue(4))),
                new WhileStatement(new LogicExpression(">",new VariableExpression("v"),new ValueExpression(new IntValue(0))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssigmentStatement("v", new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                new PrintStatement(new VariableExpression("v")));
    }

    public static IStatement example6(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a)
        return makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new VariableExpression("a"))
        );
    }

    public static IStatement example7(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5)
        return makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                        new ValueExpression(new IntValue(5))))
        );
    }

    public static IStatement example8(){
        //Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);
        return makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new VariableExpression("v")),
                        new ValueExpression(new IntValue(5))))
        );
    }

    public static IStatement example9(){
        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        return makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                //new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
        );
    }

    public static IStatement example10(){
        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        return makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                //new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
        );
    }

    public static IStatement example11(){
//    int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a))); print(v);print(rH(a))
        return makeIt(new VariableDeclarationStatement("v",new IntType()),
                    new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                    new AssigmentStatement("v",new ValueExpression(new IntValue(10))),
                    new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                    new ForkStatement(makeIt(
                            new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
                            new AssigmentStatement("v",new ValueExpression(new IntValue(32))),
                            new PrintStatement(new VariableExpression("v")),
                            new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))),
                    new PrintStatement(new VariableExpression("v")),
                    new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
                );
    }
}
