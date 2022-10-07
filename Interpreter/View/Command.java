package View;

public abstract class Command {
    private final String key,desc;
    public Command(String key,String desc)
    {
        this.key=key;
        this.desc=desc;
    }
    public abstract void execute();
    public String getKey(){
        return key;
    }
    public String getDesc()
    {
        return desc;
    }
}
