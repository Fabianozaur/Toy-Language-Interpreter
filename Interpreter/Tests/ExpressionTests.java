//package Tests;
//
//
//import Model.ADT.Dictionary;
//import Model.Expression.ArithmeticExpression;
//import Model.Expression.LogicExpression;
//import Model.Expression.ValueExpression;
//import Model.Expression.VariableExpression;
//import Model.Type.BoolValue;
//import Model.Type.IValue;
//import Model.Type.IntValue;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class ExpressionTests {
//    @Test
//    public void testArithmeticExpression(){
//        Dictionary<String, IValue> symbolTable = new Dictionary<>();
//        ValueExpression value1 = new ValueExpression(new IntValue(2));
//
//        ArithmeticExpression expresion1 = new ArithmeticExpression('+', value1,value1);
//
//
//        try{
//            IValue result = expresion1.evaluate(symbolTable);
//            assertEquals(result.toString(), "4");
//        }
//        catch (Exception e){
//            assert false;
//        }
//
//    }
//    @Test
//    public void testLogicExpression() {
//        Dictionary<String, IValue> symbolTable = new Dictionary<>();
//        ValueExpression value1 = new ValueExpression(new BoolValue(true));
//
//        LogicExpression expresion1 = new LogicExpression("&", value1, value1);
//
//
//        try {
//            IValue result = expresion1.evaluate(symbolTable);
//            assertEquals(result.toString(), "true");
//        }catch (Exception e){
//            assert false;
//        }
//    }
//
//    @Test
//    public void testVariableExpression() {
//        Dictionary<String, IValue> symbolTable = new Dictionary<>();
//        symbolTable.add("v", new IntValue(8));
//        VariableExpression expression = new VariableExpression("v");
//
//        assertEquals(expression.toString(), "v");
//
//        try{
//            IValue result = expression.evaluate(symbolTable);
//            assertEquals(result.toString(), "8");
//        }
//        catch (Exception e){
//            assert false;
//        }
//    }
//
//}
