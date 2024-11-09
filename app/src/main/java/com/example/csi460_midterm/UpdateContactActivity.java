//This activity gets an intent from the Adapter, pre-poluates the update form with the objects fields that were sent with intent; allows user to update
// or delete an old contact

package com.example.csi460_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContactActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText contactNameEdt, contactNumberEdt, contactEmailEdt, contactCompanyEdt, contactAddressEdt;
    private Button updateContactBtn, deleteContactBtn;
    private DbHandler dbHandler;
    String contactName, contactNumber, contactEmail, contactCompany, contactAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        // initializing all our variables.
        contactNameEdt = findViewById(R.id.idEdtContactName);
        contactNumberEdt = findViewById(R.id.idEdtContactNumber);
        contactEmailEdt = findViewById(R.id.idEdtContactEmail);
        contactAddressEdt = findViewById(R.id.idEdtContactAddress);
        contactCompanyEdt = findViewById(R.id.idEdtContactCompany);
        updateContactBtn = findViewById(R.id.idBtnUpdateContact);
        deleteContactBtn = findViewById(R.id.idBtnDeleteContact);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DbHandler(UpdateContactActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        contactName = getIntent().getStringExtra("name");
        contactNumber = getIntent().getStringExtra("number");
        contactEmail = getIntent().getStringExtra("email");
        contactCompany = getIntent().getStringExtra("company");
        contactAddress = getIntent().getStringExtra("address");

        // setting data to edit text
        // of our update activity.
        contactNameEdt.setText(contactName);
        contactNumberEdt.setText(contactNumber);
        contactEmailEdt.setText(contactEmail);
        contactCompanyEdt.setText(contactCompany);
        contactAddressEdt.setText(contactAddress);

        // adding on click listener to our update contact button.
        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update contact
                // method and passing all our edit text values.
                dbHandler.updateContact(contactName, contactNameEdt.getText().toString(),
                        contactNumberEdt.getText().toString(),
                        contactEmailEdt.getText().toString(),
                        contactAddressEdt.getText().toString(),
                        contactCompanyEdt.getText().toString());

                // displaying a toast message that our contact has been updated.
                Toast.makeText(UpdateContactActivity.this, "Contact Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our contact.
        deleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our contact.
                dbHandler.deleteContact(contactName);
                Toast.makeText(UpdateContactActivity.this, "Deleted the contact", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
