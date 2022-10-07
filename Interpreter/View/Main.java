//package View;
//
//import Controller.Controller;
//import Model.Expression.ArithmeticExpression;
//import Model.Expression.ValueExpression;
//import Model.Expression.VariableExpression;
//import Model.Statement.*;
//import Model.Type.BoolType;
//import Model.Type.BoolValue;
//import Model.Type.IntType;
//import Model.Type.IntValue;
//import Repo.IRepository;
//import Repo.Repository;
//
////import static Tests.TestRunner.runTests;
//
//public class Main {
//    public static void main(String[] args)
//    {
//
//        IRepository repo=new Repository("log.txt");
//        Controller controller=new Controller(repo);
//        controller.add(example1());
//        controller.add(example2());
//        controller.add(example3());
//        UI ui=new UI(controller);
//        //runTests();
//        ui.run();
//    }
//    private static IStatement example1()
//    {
//        IStatement first=new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssigmentStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))));
//        return first;
//    }
//
//    private static IStatement example2() {
//        IStatement second = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
//                        new CompoundStatement(new AssigmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompoundStatement(new AssigmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//        return second;
//    }
//    private static IStatement example3() {
//        IStatement third = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
//                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(new AssigmentStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssigmentStatement("v", new ValueExpression(new IntValue(2))), new AssigmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
//        return third;
//    }
//}
