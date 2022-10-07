package Model.Type;

public interface IType {
    IValue defaultValue();
    boolean equals(IType another);
}
