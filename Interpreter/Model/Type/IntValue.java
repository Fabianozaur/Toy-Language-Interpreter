package Model.Type;

public class IntValue implements IValue
{
    private final int value;

    public IntValue() {
        value=0;
    }
    public IntValue(int value) {
        this.value = value;
    }
    @Override
    public IType getType() { return new IntType();}
    public int getValue(){ return value;}
    public String toString(){return "" + value;}
    @Override
    public boolean equals(IValue another) {
        if (another instanceof IntValue)
            return value == ((IntValue) another).getValue();
        return false;
    }
}
