package birthcertificate;

import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Kelly
 */
public class Data {    
    private SimpleStringProperty name;
    private LocalDate dob;
    private SimpleStringProperty pob;
    private ComboBox sex;

    /*Data(String string, String name, String from_Pob) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }*/        

    public Data(String name, LocalDate dob, String pob, ObservableList getDatas) {
        this.name = new SimpleStringProperty (name);
        this.dob = dob;
        this.pob = new SimpleStringProperty (pob);
        this.sex = new ComboBox(getDatas);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty (name);
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob.get();
    }

    public void setPob(String pob) {
        this.pob = new SimpleStringProperty (pob);
    }

    public ComboBox getSex() {
        return sex;
    }

    public void setSex(ComboBox sex) {
        this.sex = sex;
    }

}