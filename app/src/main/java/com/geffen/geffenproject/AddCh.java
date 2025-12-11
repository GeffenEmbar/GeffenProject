package com.geffen.geffenproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddCh extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner catSpinner;
    LinearLayout editTextsContainer, editTextsContainer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        catSpinner = findViewById(R.id.cat);
        editTextsContainer = findViewById(R.id.editTextsContainer);
        editTextsContainer2 = findViewById(R.id.editTextsContainer2);
        catSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String selected = parent.getItemAtPosition(i).toString();

        if (i == 1) {
            // FIRST real option
            editTextsContainer.setVisibility(View.VISIBLE);
            editTextsContainer2.setVisibility(View.GONE);
        }
        else if (i == 2) {
            // SECOND real option
            editTextsContainer.setVisibility(View.GONE);
            editTextsContainer2.setVisibility(View.VISIBLE);
        }
        else {
            // HINT or something else
            editTextsContainer.setVisibility(View.GONE);
            editTextsContainer2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}