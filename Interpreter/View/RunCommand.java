package View;

import Controller.Controller;

import java.io.IOException;

public class RunCommand extends Command{
    private Controller controller;
    public RunCommand(String key,String desc,Controller controller)
    {
        super(key,desc);
        this.controller=controller;
    }

    @Override
    public void execute() {
        try{
            controller.allStep(Integer.parseInt(super.getKey())-1);
        }catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}
