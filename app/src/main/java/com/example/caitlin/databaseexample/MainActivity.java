package com.example.caitlin.databaseexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    Context context;
    ArrayList<Contact> contactList;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        helper = new DBHelper(this);

        contact = new Contact("Mama", "0612345678");

        helper.create(contact);

        contact.setNumber("0612121212");
        helper.update(contact);

        helper.delete(contact);

        contactList = helper.read();
    }

}
