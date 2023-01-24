package com.example.sulimanlab_7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etPhone, etId;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.edName);
        etPhone = (EditText) findViewById(R.id.edPhon);
        etId = (EditText) findViewById(R.id.edID);

        dbHandler = new MyDBHandler(this);
    }
    public void addButtonClicked(View v){

        String n = etName.getText().toString();
        String p = etPhone.getText().toString();

        dbHandler.addRecord(n,p);

        etName.setText("");
        etPhone.setText("");
    }

    public void printDatabaseData(View v){
        String db = dbHandler.dataBaseToString();
        Toast.makeText(getApplicationContext(),db,Toast.LENGTH_LONG).show();

    }

    public void updateRecord(View v){
        //Left as HW
        String id=etId.getText().toString();
        String na=etName.getText().toString();
        String ph=etPhone.getText().toString();

        dbHandler.updateRecord(id,na,ph);
        etId.setText("");
        etName.setText("");
        etPhone.setText("");
        Toast.makeText(getApplicationContext(),"Database has been updated",Toast.LENGTH_LONG).show();
    }
}