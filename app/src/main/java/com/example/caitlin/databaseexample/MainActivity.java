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
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        helper = new DBHelper(this);
        tv1 = (TextView) findViewById(R.id.text);
        tv2 = (TextView) findViewById(R.id.text2);
        tv3 = (TextView) findViewById(R.id.text3);

        contact = new Contact("Mama", "0612345678");

        helper.create(contact);

        showContact(1);
    }

    public void showContact(int tv) {
        contactList = helper.read();
        String contactInfo;

        if (!contactList.isEmpty()) {
            contactInfo = contactList.get(0).getName() + " - " + contactList.get(0).getNumber();
        }
        else {
            contactInfo = "No contacts in database";
        }

        if (tv == 1) {
            tv1.setText(contactInfo);
            contact.setID(contactList.get(0).getID());
        }
        else if (tv == 2) {
            tv1.setVisibility(View.INVISIBLE);
            tv2.setText(contactInfo);
            tv2.setVisibility(View.VISIBLE);
        }
        else {
            tv2.setVisibility(View.INVISIBLE);
            tv3.setText(contactInfo);
            tv3.setVisibility(View.VISIBLE);
        }

    }

    public void clicked1(View view) {
        contact.setNumber("0612121212");
        int a = helper.update(contact);
        Log.d("update gelukt?", Integer.toString(a));
        showContact(2);
    }

    public void clicked2(View view) {
        helper.delete(contact);
        showContact(3);
    }
}
