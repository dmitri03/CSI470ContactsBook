// our sqlLite database object file, defining such parameters as table and column names, as well as provding definition for the CRUD methods

package com.example.csi460_midterm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "contactDb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycontacts";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our contact name column
    private static final String NAME_COL = "name";

    // below variable id for our contact duration column.
    private static final String NUMBER_COL = "number";

    // below variable for our contact description column.
    private static final String EMAIL_COL = "email";

    // below variable is for our contact tracks column.
    private static final String ADDRESS_COL = "address";
    private static final String COMPANY_COL = "company";

    // creating a constructor for our database handler.
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + NUMBER_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + COMPANY_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new contact to our sqlite database.
    public void addNewContact(String contactName, String contactNumber, String contactEmail, String contactAddress, String contactCompany) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, contactName);
        values.put(NUMBER_COL, contactNumber);
        values.put(EMAIL_COL, contactEmail);
        values.put(COMPANY_COL, contactCompany);
        values.put(ADDRESS_COL, contactAddress);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the contacts.
    public ArrayList<ContactModal> readContacts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ContactModal> contactModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contactModalArrayList.add(new ContactModal(cursorContacts.getString(1),
                        cursorContacts.getString(2),
                        cursorContacts.getString(3),
                        cursorContacts.getString(4),
                        cursorContacts.getString(5)));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contactModalArrayList;
    }

    // below is the method for updating our contacts
    public void updateContact(String originalContactName, String contactName, String contactNumber,
                             String contactEmail, String contactAddress, String contactCompany) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, contactName);
        values.put(NUMBER_COL, contactNumber);
        values.put(EMAIL_COL, contactEmail);
        values.put(COMPANY_COL, contactCompany);
        values.put(ADDRESS_COL, contactAddress);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our contact which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalContactName});
        db.close();
    }

    // below is the method for deleting our contact.
    public void deleteContact(String contactName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // contact and we are comparing it with our contact name.
        db.delete(TABLE_NAME, "name=?", new String[]{contactName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
