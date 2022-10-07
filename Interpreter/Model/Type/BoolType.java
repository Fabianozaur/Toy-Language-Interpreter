package Model.Type;

public class BoolType implements IType{
    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public boolean equals(IType another) {
        return another instanceof BoolType;
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue();
    }

}
