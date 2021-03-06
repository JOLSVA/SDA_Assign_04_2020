package com.example.sdaassign4_2019;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {

    private static final String BORROWER_NAME = "BORROWER_NAME";
    private static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    private static final String BORROWER_ID = "BORROWER_ID";
    private static final String SETTINGS_CHECK = "SETTINGS_CHECK";


    private EditText mBorrowerName, mEmailAddress, mBorrowerId;

    public Settings() {
        // Required empty public constructor

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        //final SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
        //SharedPreferences prefs = null;
        final SharedPreferences prefs = getActivity().getSharedPreferences("user-details", MODE_PRIVATE);

        mBorrowerName = view.findViewById(R.id.userName);
        mEmailAddress = view.findViewById(R.id.email);
        mBorrowerId = view.findViewById(R.id.borrowerID);

        mBorrowerName.setText(prefs.getString(BORROWER_NAME,"" ));
        mEmailAddress.setText(prefs.getString(EMAIL_ADDRESS,"" ));
        mBorrowerId.setText(prefs.getString(BORROWER_ID,"" ));

        //Task 3 to check if the user saved setting and to send the check result to the adapter
        //Intent mySettings = new Intent(getContext(), LibraryViewAdapter.class);

        //if (true)
                //prefs.getString(BORROWER_ID, "") == "")
        //{
        //    mySettings.putExtra(SETTINGS_CHECK,"true");
        //    getContext().startActivity(mySettings);
        //}
        //else {
        //    mySettings.putExtra(SETTINGS_CHECK,"false");
        //    getContext().startActivity(mySettings);
        //}

        final Button saveButton = view.findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!mEmailAddress.getText().toString().contains("@")){
                    Toast.makeText(getContext(), "Incorrect email address", Toast.LENGTH_LONG).show();}

                else if(mBorrowerName.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Borrower Name is missing", Toast.LENGTH_LONG).show();}

                    else {
                    // Set high score to 0
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(BORROWER_NAME, mBorrowerName.getText().toString());
                    editor.putString(EMAIL_ADDRESS, mEmailAddress.getText().toString());
                    editor.putString(BORROWER_ID, mBorrowerId.getText().toString());
                    editor.apply();
                    }
            }
        });

        final Button resetButton = view.findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Set high score to 0
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(BORROWER_NAME, "");
                editor.putString(EMAIL_ADDRESS, "");
                editor.putString(BORROWER_ID, "");
                editor.apply();

                mBorrowerName.setText(prefs.getString(BORROWER_NAME,"" ));
                mEmailAddress.setText(prefs.getString(EMAIL_ADDRESS,"" ));
                mBorrowerId.setText(prefs.getString(BORROWER_ID,"" ));

            }
        });

        return view;

    }

}
