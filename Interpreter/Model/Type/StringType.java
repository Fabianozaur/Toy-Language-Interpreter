package Model.Type;

import javax.management.StringValueExp;

public class StringType implements IType{
    @Override
    public IValue defaultValue() {

        return new StringValue();
    }

    @Override
    public boolean equals(IType another) {

        return another instanceof StringType;
    }

    @Override
    public String toString() {

        return "string";
    }
}
