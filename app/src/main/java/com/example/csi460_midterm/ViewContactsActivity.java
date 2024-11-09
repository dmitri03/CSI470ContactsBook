//sets the adapter on the layout, showing the list of contacts.

package com.example.csi460_midterm;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewContactsActivity extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ContactModal> contactModalArrayList;
    private DbHandler dbHandler;
    private ContactRVAdapter contactRVAdapter;
    private RecyclerView contactsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        // initializing our all variables.
        contactModalArrayList = new ArrayList<>();
        dbHandler = new DbHandler(ViewContactsActivity.this);

        // getting our contact array
        // list from db handler class.
        contactModalArrayList = dbHandler.readContacts();

        // on below line passing our array list to our adapter class.
        contactRVAdapter = new ContactRVAdapter(contactModalArrayList, ViewContactsActivity.this);
        contactsRV = findViewById(R.id.idRVContacts);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewContactsActivity.this, RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        contactsRV.setAdapter(contactRVAdapter);
    }
}
