// This is a recycle view adapter, that populates the ViewContactsActivity with the contacts; takes an arraylist as a parameter
package com.example.csi460_midterm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ContactModal> contactModalArrayList;
    private Context context;

    // constructor
    public ContactRVAdapter(ArrayList<ContactModal> contactModalArrayList, Context context) {
        this.contactModalArrayList = contactModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ContactModal modal = contactModalArrayList.get(position);
        holder.contactNameTV.setText(modal.getContactName());
        holder.contactNumberTV.setText(modal.getContactNumber());
        holder.contactEmailTV.setText(modal.getContactEmail());
        holder.contactAddressTV.setText(modal.getContactAddress());
        holder.contactCompanyTV.setText(modal.getContactCompany());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateContactActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getContactName());
                i.putExtra("number", modal.getContactNumber());
                i.putExtra("email", modal.getContactEmail());
                i.putExtra("address", modal.getContactAddress());
                i.putExtra("company", modal.getContactCompany());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return contactModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView contactNameTV, contactNumberTV, contactEmailTV, contactCompanyTV, contactAddressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            contactNameTV = itemView.findViewById(R.id.idTVContactName);
            contactNumberTV = itemView.findViewById(R.id.idTVContactNumber);
            contactEmailTV = itemView.findViewById(R.id.idTVContactEmail);
            contactCompanyTV = itemView.findViewById(R.id.idTVContactCompany);
            contactAddressTV = itemView.findViewById(R.id.idTVContactAddress);
        }
    }
}
