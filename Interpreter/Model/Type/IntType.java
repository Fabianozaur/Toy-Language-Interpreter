package Model.Type;

public class IntType implements IType{
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(IType another) {
        return another instanceof IntType;
    }

    @Override
    public IValue defaultValue() {
        return new IntValue();
    }
}
