package com.example.joost.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // arrays containing body parts and booleans for visibility
    String[] pieces = new String[] {"Hat", "Eyebrows", "Ears", "Glasses", "Eyes",
            "Nose", "Arms", "Moustache", "Mouth", "Shoes"};

    public void checkClicked(View v) {

        // store checkbox info in Checkbox object and get name
        CheckBox checkbox = (CheckBox) v;
        String boxName = checkbox.getText().toString();

        // store corresponding image ID in integer and find correct image
        int resID = getResources().getIdentifier(boxName, "id", getPackageName());
        ImageView image = (ImageView) findViewById(resID);

        // set visibility based on whether checbox is checked
        if (checkbox.isChecked()){
            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.INVISIBLE);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        boolean[] visibility = new boolean[10];

        // check visibility for each body part and set boolean for corresponding index
        for (int i = 0; i < pieces.length; i++) {

            int resID = getResources().getIdentifier(pieces[i], "id", getPackageName());
            ImageView image = (ImageView) findViewById(resID);

            if (image.getVisibility() == View.VISIBLE) {
                visibility[i] = true;
            }
        }

        // save visibility boolean to outstate
        outState.putBooleanArray("images", visibility);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // don't perform setting of visibility states if no savedInstance state yet
        if (savedInstanceState != null) {

            boolean[] visibility = savedInstanceState.getBooleanArray("images");

            // set visibility based on boolean made in onSaveInstanceState()
            for (int i = 0; i < pieces.length; i++) {
                int resID = getResources().getIdentifier(pieces[i], "id", getPackageName());
                ImageView image = findViewById(resID);

                if (visibility[i]) {
                    image.setVisibility(View.VISIBLE);
                }
                else {
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
