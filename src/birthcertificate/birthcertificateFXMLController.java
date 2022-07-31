package birthcertificate;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Kelly
 */
public class birthcertificateFXMLController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TextField pobField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button openFileButton;
    
    @FXML
    private Button removeFileButton;
    
    @FXML
    private ListView fileListView;
    
    @FXML
    private ImageView fileImageView;
    
    @FXML
    private TextField pathTextField;

    @FXML
    private TableView<Person> dataTable;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, LocalDate> dobColumn;

    @FXML
    private TableColumn<Person, String> pobColumn;

    @FXML
    private TableColumn<Person, ComboBox> sexColumn;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // sets up columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("dob"));
        pobColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("pob"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Person, ComboBox>("sex"));
        
        //load dummy data
        dataTable.setItems(getDatas());
        
        //Update the table to allow for amount, type and remarks columns to be editable
        dataTable.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pobColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow the table to select multiple rows at once
        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //Disable the openFileButton
        this.openFileButton.setDisable(true);
        //fileListView.setItems(fileListViewList);
        //fileListView.getItems().addAll("BC005","BC007","BC008","BC009","BC0010");
        fileListView.setItems(getDatass());
        fileListView.setEditable(true);
        fileListView.setCellFactory(TextFieldListCell.forListView());
        fileListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.removeFileButton.setDisable(true);

    }
    
    private final ObservableList<String> getDatass() {
        ObservableList<String> datass = FXCollections.observableArrayList();
        datass.add(new String ("BC001.png"));
        datass.add(new String ("BC002.jpg"));
        datass.add(new String ("BC003.png"));
        datass.add(new String ("BC004.jpeg"));
        
        return datass;
    }
    
    //observable list
    public ObservableList<Person> getDatas(){
        
        ObservableList<Person> datas = FXCollections.observableArrayList();
        datas.add(new Person("Kelly Morgan", LocalDate.of(2015, Month.JANUARY, 15), "Cameroon", FXCollections.observableArrayList("Male","Female")));
        datas.add(new Person("John Doe", LocalDate.of(2015, Month.JANUARY, 15), "Kenya", FXCollections.observableArrayList("Male","Female")));
        datas.add(new Person("Siara Malone", LocalDate.of(2015, Month.JANUARY, 15), "South Africa", FXCollections.observableArrayList("Male","Female")));
        datas.add(new Person("Jonas Bright", LocalDate.of(2015, Month.JANUARY, 15), "United States", FXCollections.observableArrayList("Male","Female")));
        
        return datas;
    }
    
    @FXML
    private void addData(ActionEvent event) {
        Person newData = new Person(nameField.getText(),
                                dobDatePicker.getValue(),
                                pobField.getText(), 
                                FXCollections.observableArrayList("Male","Female"));
        
        //Get all the items from the table as a list, then add the new data to
        //the list
        dataTable.getItems().add(newData);
    }
    
    @FXML
    private void deleteData(ActionEvent event) {
        ObservableList<Person> selectedRows, allDatas;
        allDatas = dataTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = dataTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Person data: selectedRows)
        {
            allDatas.remove(data);
        }
    }
    
    @FXML
    private void changeName(CellEditEvent edittedCell) {
        Person dataSelected = dataTable.getSelectionModel().getSelectedItem();
        dataSelected.setName(edittedCell.getNewValue().toString());
    }
    
    @FXML
    private void changePob(CellEditEvent edittedCell) {
        Person dataSelected = dataTable.getSelectionModel().getSelectedItem();
        dataSelected.setPob(edittedCell.getNewValue().toString());
    }

    //Enable the openFileButton
    @FXML
    private void enableFileButton(MouseEvent event) {
        this.openFileButton.setDisable(false);
        this.removeFileButton.setDisable(false);
    }
    
    @FXML
    private void OpenFileButtonHandler(MouseEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Birth Certificate");
        filechooser.getExtensionFilters().add(new ExtensionFilter("Images","*.png", "*.jpg", "*.jpeg"));
        File selectedFile = filechooser.showOpenDialog(null);
        //Image image = new Image("");
        //fileImageView.setImage(image);
        
        if (selectedFile != null) {
            //fileImageView.setImage(image);
            Image image = new Image(selectedFile.toURI().toString());
            fileImageView.setImage(image);
            fileListView.getItems().add(selectedFile.getName());
            pathTextField.setText(selectedFile.getAbsolutePath());
            //pathLabel.setText(label);
        }
        else {
            System.out.println("ERROR");
        }
    }
    
    @FXML
    private void removeFileButtonHandler(ActionEvent event) {
        ObservableList<String> selectedCells, allDatass;
        allDatass = fileListView.getItems();
        
        selectedCells = fileListView.getSelectionModel().getSelectedItems();
        
        for (String datass: selectedCells)
        {
            allDatass.remove(datass);
        }
    }
    
}