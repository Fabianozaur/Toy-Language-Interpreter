package Model.Type;

public class ReferenceType implements IType {
    IType in;
    public ReferenceType(IType in)
    {
        this.in=in;
    }
    public IType getIn()
    {
        return this.in;
    }
    @Override
    public boolean equals(IType another)
    {
        if(another instanceof ReferenceType)
            return in.equals(((ReferenceType)another).getIn());
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0,in);
    }

    @Override
    public String toString() {
        return "Ref("+in.toString()+")";
    }
}
