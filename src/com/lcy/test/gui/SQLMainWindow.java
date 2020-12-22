package com.lcy.test.gui;

import com.lcy.test.util.SQLHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLMainWindow {

    public AnchorPane anchorPane;
    public Button bEXIT;
    SQLHelper sqlHelper;
    public TextField tID;
    public TextField tName;

    public Button bFind;
    public TextArea tContext;

    public void find(ActionEvent actionEvent) throws SQLException {
        if(sqlHelper == null){
            sqlHelper = new SQLHelper();
        }
        tContext.setText(tID.getText()+"\n"+tName.getText());
        String condition = "";
        if(tID.getText() != ""){
            condition = "ID LIKE '%" +tID.getText()+"%'";
        }
        if(tName.getText() != ""){
            if (condition != ""){
                condition += " AND ";
            }
            condition += "NAME LIKE '%"+tName.getText() + "%'";
        }
        ResultSet rs =  sqlHelper.executeQuery("SELECT * FROM ITEMS WHERE "+condition+";");
        String ans = "";
        while(rs.next()){
            ans += rs.getString("ID")+"\t";
            ans += rs.getString("NAME")+"\t";
            ans += rs.getString("AMOUNT")+"\t";
            ans += "\n";
        }
        tContext.setText(ans);
    }

    public void EXIT(ActionEvent actionEvent) {
        Stage stage = (Stage)bEXIT.getScene().getWindow();
        stage.close();
    }
}
