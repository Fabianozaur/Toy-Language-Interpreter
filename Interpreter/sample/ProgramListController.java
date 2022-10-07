package sample;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static View.Interpreter.makeIt;

public class ProgramListController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public ProgramListController(){}
    private List<Controller> programs=new ArrayList<>();
    @FXML
    private ListView<String>ProgramListView=new ListView<>();
    public void setProgramListView(){
        IStatement ex1=new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssigmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        try{
            Stack<IStatement> executionStack1=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable1=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable1=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap1=new Heap<Integer,IValue>();
            IList<IValue> output1=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex1.typecheck(typeenv);
            ProgramState program1=new ProgramState(executionStack1,symbolsTable1,output1,fileTable1,heap1,ex1);
            Controller controller1=new Controller("example1.txt","example1");
            controller1.add(program1);
            programs.add(controller1);
        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 1 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex2=new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssigmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssigmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        try{
            Stack<IStatement> executionStack2=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable2=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable2=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap2=new Heap<Integer,IValue>();
            IList<IValue> output2=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex2.typecheck(typeenv);
            ProgramState program2=new ProgramState(executionStack2,symbolsTable2,output2,fileTable2,heap2,ex2);
            Controller controller2=new Controller("example2.txt","example2");
            controller2.add(program2);
            programs.add(controller2);
        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 2 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex3=new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssigmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssigmentStatement("v", new ValueExpression(new IntValue(2))), new AssigmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        try{
            Stack<IStatement> executionStack3=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable3=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable3=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap3=new Heap<Integer,IValue>();
            IList<IValue> output3=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex3.typecheck(typeenv);
            ProgramState program3=new ProgramState(executionStack3,symbolsTable3,output3,fileTable3,heap3,ex3);
            Controller controller3=new Controller("example3.txt","example3");
            controller3.add(program3);
            programs.add(controller3);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 3 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex4=new CompoundStatement(new VariableDeclarationStatement("file_name", new StringType()),
                new CompoundStatement(new AssigmentStatement("file_name", new ValueExpression(new StringValue("src/Model/File/test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("file_name")),
                                new CompoundStatement(new VariableDeclarationStatement("var", new IntType()),
                                        new CompoundStatement(new ReadFromFileStatement(new VariableExpression("file_name"), "var"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("var")),
                                                        new CompoundStatement(new ReadFromFileStatement(new VariableExpression("file_name"), "var"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("var")),
                                                                        new CloseReadFile(new VariableExpression("file_name"))))))))));
        try{
            Stack<IStatement> executionStack4=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable4=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable4=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap4=new Heap<Integer,IValue>();
            IList<IValue> output4=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex4.typecheck(typeenv);
            ProgramState program4=new ProgramState(executionStack4,symbolsTable4,output4,fileTable4,heap4,ex4);
            Controller controller4=new Controller("example4.txt","example4");
            controller4.add(program4);
            programs.add(controller4);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 4 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex5=makeIt(new VariableDeclarationStatement("v", new IntType()),
                new AssigmentStatement("v", new ValueExpression(new IntValue(4))),
                new WhileStatement(new LogicExpression(">",new VariableExpression("v"),new ValueExpression(new IntValue(0))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssigmentStatement("v", new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                new PrintStatement(new VariableExpression("v")));
        try{
            Stack<IStatement> executionStack5=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable5=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable5=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap5=new Heap<Integer,IValue>();
            IList<IValue> output5=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex5.typecheck(typeenv);
            ProgramState program5=new ProgramState(executionStack5,symbolsTable5,output5,fileTable5,heap5,ex5);
            Controller controller5=new Controller("example5.txt","example5");
            controller5.add(program5);
            programs.add(controller5);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 5 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex6=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new VariableExpression("a"))
        );
        try{
            Stack<IStatement> executionStack6=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable6=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable6=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap6=new Heap<Integer,IValue>();
            IList<IValue> output6=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex6.typecheck(typeenv);
            ProgramState program6=new ProgramState(executionStack6,symbolsTable6,output6,fileTable6,heap6,ex6);
            Controller controller6=new Controller("example6.txt","example6");
            controller6.add(program6);
            programs.add(controller6);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 6 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex7=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                        new ValueExpression(new IntValue(5))))
        );
        try{
            Stack<IStatement> executionStack7=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable7=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable7=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap7=new Heap<Integer,IValue>();
            IList<IValue> output7=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex7.typecheck(typeenv);
            ProgramState program7=new ProgramState(executionStack7,symbolsTable7,output7,fileTable7,heap7,ex7);
            Controller controller7=new Controller("example7.txt","example7");
            controller7.add(program7);
            programs.add(controller7);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 7 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex8=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new VariableExpression("v")),
                        new ValueExpression(new IntValue(5))))
        );
        try{
            Stack<IStatement> executionStack8=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable8=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable8=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap8=new Heap<Integer,IValue>();
            IList<IValue> output8=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex8.typecheck(typeenv);
            ProgramState program8=new ProgramState(executionStack8,symbolsTable8,output8,fileTable8,heap8,ex8);
            Controller controller8=new Controller("example8.txt","example8");
            controller8.add(program8);
            programs.add(controller8);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 8 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex9=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                //new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
        );
        try{
            Stack<IStatement> executionStack9=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable9=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable9=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap9=new Heap<Integer,IValue>();
            IList<IValue> output9=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex9.typecheck(typeenv);
            ProgramState program9=new ProgramState(executionStack9,symbolsTable9,output9,fileTable9,heap9,ex9);
            Controller controller9=new Controller("example9.txt","example9");
            controller9.add(program9);
            programs.add(controller9);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 9 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex10=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a", new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                //new HeapAllocationStatement("a", new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
        );
        try{
            Stack<IStatement> executionStack10=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable10=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable10=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap10=new Heap<Integer,IValue>();
            IList<IValue> output10=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex10.typecheck(typeenv);
            ProgramState program10=new ProgramState(executionStack10,symbolsTable10,output10,fileTable10,heap10,ex10);
            Controller controller10=new Controller("example10.txt","example10");
            controller10.add(program10);
            programs.add(controller10);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 10 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        IStatement ex11=makeIt(new VariableDeclarationStatement("v",new IntType()),
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
        try{
            Stack<IStatement> executionStack11=new Stack<IStatement>();
            Dictionary<String, IValue> symbolsTable11=new Dictionary<String, IValue>();
            Dictionary<String,BufferedReader> fileTable11=new Dictionary<String,BufferedReader>();
            Heap<Integer,IValue> heap11=new Heap<Integer,IValue>();
            IList<IValue> output11=new Model.ADT.List<IValue>();
            IDictionary<String,IType> typeenv=new Dictionary<String, IType>();
            ex11.typecheck(typeenv);
            ProgramState program11=new ProgramState(executionStack11,symbolsTable11,output11,fileTable11,heap11,ex11);
            Controller controller11=new Controller("example11.txt","example11");
            controller11.add(program11);
            programs.add(controller11);

        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Type check error");
            alert.setContentText("Example 11 did not pass the type check: "+e.getMessage());
            alert.showAndWait();
        }
        ProgramListView.setItems(FXCollections.observableArrayList(
                programs.stream().map(Controller::getProgramName).collect(Collectors.toList())
        ));


    }
    @FXML
    private void RunButtonPressed()
    {
        if(ProgramListView.getSelectionModel().getSelectedItem()!=null)
        {
            try{
                FXMLLoader loader =new FXMLLoader(getClass().getResource("ProgramRunLayout.fxml"));
                Parent interpreterView=loader.load();
                ProgramRunController controller=loader.getController();
                controller.setController(programs.get(ProgramListView.getSelectionModel().getSelectedIndex()));
                Stage stage=new Stage();
                stage.setTitle("Main Window");
                stage.setScene(new Scene(interpreterView));
                stage.showAndWait();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
