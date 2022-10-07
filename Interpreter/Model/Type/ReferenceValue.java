package Model.Type;

public class ReferenceValue implements IValue {
    private final int address;
    private IType locationType;
    public ReferenceValue(int address,IType locationType)
    {
        this.address=address;
        this.locationType=locationType;
    }
    @Override
    public boolean equals(IValue another)
    {
        if(another instanceof ReferenceValue)
            return (((ReferenceValue) another).getAddress() == this.address & ((ReferenceValue) another).getType() == this.locationType);
        else
            return false;
    }
    public int getAddress()
    {
        return this.address;
    }
    public IType getLocationType()
    {
        return this.locationType;
    }

    @Override
    public IType getType() {
        return new ReferenceType(this.locationType);
    }

    @Override
    public String toString() {
        return "("+Integer.toString(this.address)+", "+this.locationType.toString()+")";
    }
}
