//Starting point of our application, shows a form to create a new contact

package com.example.csi460_midterm;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText contactNameEdt, contactNumberEdt, contactEmailEdt, contactCompanyEdt, contactAddressEdt;
    private Button addContactBtn, readContactBtn;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        contactNameEdt = findViewById(R.id.idEdtContactName);
        contactNumberEdt = findViewById(R.id.idEdtContactNumber);
        contactEmailEdt = findViewById(R.id.idEdtContactEmail);
        contactAddressEdt = findViewById(R.id.idEdtContactAddress);
        contactCompanyEdt = findViewById(R.id.idEdtContactCompany);
        addContactBtn = findViewById(R.id.idBtnAddContact);
        readContactBtn = findViewById(R.id.idBtnReadContacts);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DbHandler(MainActivity.this);

        // below line is to add on click listener for our add contact button.
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String contactName = contactNameEdt.getText().toString();
                String contactNumber = contactNumberEdt.getText().toString();
                String contactEmail = contactEmailEdt.getText().toString();
                String contactCompany = contactCompanyEdt.getText().toString();
                String contactAddress = contactAddressEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (contactName.isEmpty() || contactNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // contact to sqlite data and pass all our values to it.
                dbHandler.addNewContact(contactName, contactNumber, contactEmail, contactAddress, contactCompany);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                contactNameEdt.setText("");
                contactNumberEdt.setText("");
                contactEmailEdt.setText("");
                contactCompanyEdt.setText("");
                contactAddressEdt.setText("");
            }
        });

        readContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewContactsActivity.class);
                startActivity(i);
            }
        });
    }
}
