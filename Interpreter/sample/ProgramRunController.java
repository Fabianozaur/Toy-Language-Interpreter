package sample;

import Controller.Controller;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.IValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ProgramRunController implements Initializable {
    public ProgramRunController(){}
    private Controller controller;
    private ProgramState selectedProgram;
    @FXML
    private TableView<HashMap.Entry<Integer, String>> HeapTableView=new TableView<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, Integer> HeapAdressColumn=new javafx.scene.control.TableColumn<>();
    @FXML
    private TableColumn<HashMap.Entry<Integer, String>, String> HeapValueColumn=new javafx.scene.control.TableColumn<>();

    @FXML
    private ListView<String> OutputListView=new ListView<>();

    @FXML
    private ListView<String> FileTableListView= new ListView<>();

    @FXML
    private ListView<Integer> ProgramStateListView=new ListView<>();

    @FXML
    private TableView<Map.Entry<String, String>> symTableView=new javafx.scene.control.TableView<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symVarNameColumn=new TableColumn<>();
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symVarTableView=new TableColumn<>();

    @FXML
    private ListView<String> ExeStackView=new ListView<>();

    @FXML
    private TextField nrProgramStatesField=new TextField("");


    public void setController(Controller ctr) {
        controller=ctr;

        selectedProgram=controller.getRepo().getProgramStates().get(0);

        loadData();
    }

    @FXML
    public void setSelectedProgram(){
        if(ProgramStateListView.getSelectionModel().getSelectedIndex()>=0 && ProgramStateListView.getSelectionModel().getSelectedIndex()<=this.controller.getRepo().getProgramStates().size()){
            selectedProgram=controller.getRepo().getProgramStates().get(ProgramStateListView.getSelectionModel().getSelectedIndex());
            loadData();
        }
    }

    private void loadData(){
        this.ProgramStateListView.getItems().setAll( controller.getRepo().getProgramStates().stream().map(ProgramState::getProgramId).collect(Collectors.toList()) );

        if(selectedProgram!=null){

            OutputListView.getItems().setAll( selectedProgram.getOutput().toString());

            FileTableListView.getItems().setAll(String.valueOf(selectedProgram.getFileTable().getDict().keySet()));

            List<String> executionStackList=selectedProgram.getStack().getStack().stream().map(IStatement::toString).collect(Collectors.toList());
            //Collections.reverse(executionStackList);
            ExeStackView.getItems().setAll(executionStackList);

            IHeap<Integer,IValue> heapTable=selectedProgram.getMemoryHeap();
            List<Map.Entry<Integer, String>> heapTableList=new ArrayList<>();
            for(Map.Entry<Integer, IValue> element:heapTable.getContent().entrySet()){
                Map.Entry<Integer, String> el=new AbstractMap.SimpleEntry<Integer, String>(element.getKey(),element.getValue().toString());
                heapTableList.add(el);
            }
            HeapTableView.setItems(FXCollections.observableList(heapTableList));
            HeapTableView.refresh();

            HeapAdressColumn.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
            HeapValueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));

            IDictionary<String, IValue> symbolTable=this.selectedProgram.getSymbolsTable();
            List<Map.Entry<String, String>> symbolTableList=new ArrayList<>();
            for(Map.Entry<String, IValue> element:symbolTable.getDict().entrySet()){
                Map.Entry<String, String> el=new AbstractMap.SimpleEntry<String, String>(element.getKey(),element.getValue().toString());
                symbolTableList.add(el);
            }
            symTableView.setItems(FXCollections.observableList(symbolTableList));
            symTableView.refresh();

            symVarNameColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()));
            symVarNameColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()));

            nrProgramStatesField.setText(Integer.toString(controller.getRepo().getSize()));

        }
    }


    @FXML
    public void onRunOneStepButtonPressed() {
        if(controller == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Program was not selected!");
            alert.setContentText("Please select a program to execute");
            alert.showAndWait();
            return;
        }

        if(selectedProgram.getStack().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Program is done!");
            alert.setContentText("Please select a new program to execute");
            alert.showAndWait();
            return;
        }

        controller.executeOneStep();

        loadData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
