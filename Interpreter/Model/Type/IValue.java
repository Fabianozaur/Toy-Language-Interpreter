package Model.Type;

public interface IValue {
    IType getType();
    boolean equals(IValue another);
}
