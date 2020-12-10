package com.example.passwordstrengthdetector_babahov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PasswordStrength extends AppCompatActivity {
    private int animalNum;
    private ImageView monkey;
    private ImageView animal;
    private TypedArray animal_images;
    private String[] sounds;

    private final int TIME_DELAY = 2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_strength);

        monkey = findViewById(R.id.imageViewMonkey);
        monkey.setImageDrawable(null);

        animal_images = getResources().obtainTypedArray(R.array.animals_array);
        animalNum = (int) (Math.random() * animal_images.length());
        animal = findViewById(R.id.imageViewAnimal);
        animal.setImageDrawable(animal_images.getDrawable(animalNum));

        sounds = getResources().getStringArray(R.array.sounds);

        EditText password = findViewById(R.id.editTextTextPassword);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 8) {
                    monkey.setImageDrawable(getResources().getDrawable(R.drawable.monkey_strong));
                } else if (s.length() >= 6) {
                    monkey.setImageDrawable(getResources().getDrawable(R.drawable.monkey_weak));
                } else {
                    monkey.setImageDrawable(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasswordStrength.this, sounds[animalNum], Toast.LENGTH_SHORT).show();
                System.out.println(sounds[animalNum]);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setAnimalPicture();
                    }
                }, TIME_DELAY);

            }
        });
    }

    private void setAnimalPicture() {
        int oldAnimalNum = animalNum;
        animalNum = (int) (Math.random() * animal_images.length());

        while (animalNum == oldAnimalNum) {
            animalNum = (int) (Math.random() * animal_images.length());
        }

        animal.setImageDrawable(animal_images.getDrawable(animalNum));
    }
}