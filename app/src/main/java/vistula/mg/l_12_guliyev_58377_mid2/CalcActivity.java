package vistula.mg.l_12_guliyev_58377_mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CalcActivity extends AppCompatActivity {

    EditText tempValue;
    Spinner spinner;
    TextView celsius, fahrenheit, kelvin;
    Button convert, toMain;

    String celsius_result, fahr_result, kelvin_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tempValue = findViewById(R.id.temp_value);

        spinner = findViewById(R.id.spinner2);

        celsius = findViewById(R.id.celsius_result);
        fahrenheit = findViewById(R.id.fahr_result);
        kelvin = findViewById(R.id.kelvin_result);

        convert = findViewById(R.id.convert_button);
        toMain = findViewById(R.id.to_main2);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(spinner.getSelectedItem().toString().equals("Celsius")){
                   celsius_result = tempValue.getText().toString();
                   double c2f = C2F(Double.parseDouble(tempValue.getText().toString()));
                   double c2k = C2K(Double.parseDouble(tempValue.getText().toString()));

                   fahr_result = String.valueOf(c2f);
                   kelvin_result = String.valueOf(c2k);
               }else if(spinner.getSelectedItem().toString().equals("Kelvin")){
                   kelvin_result = tempValue.getText().toString();
                   double k2f = K2F(Double.parseDouble(tempValue.getText().toString()));
                   double k2c = K2C(Double.parseDouble(tempValue.getText().toString()));

                   fahr_result = String.valueOf(k2f);
                   celsius_result = String.valueOf(k2c);
               }else{
                   fahr_result = tempValue.getText().toString();
                   double f2c = F2C(Double.parseDouble(tempValue.getText().toString()));
                   double f2k = F2K(Double.parseDouble(tempValue.getText().toString()));

                   celsius_result = String.valueOf(f2c);
                   kelvin_result = String.valueOf(f2k);
               }

               celsius.setText(celsius_result);
               fahrenheit.setText(fahr_result);
               kelvin.setText(kelvin_result);

            }
        });

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


    }



            static double C2F(double c){
                return c*1.8+32;
            }
            static double C2K(double c){
                return c+273.15;
            }
            static double F2C(double f){
                return (f-32)/1.8;
            }
            static double F2K(double f){
                return (f+459.67)*5/9;
            }
            static double K2C(double k){
                return k-273.15;
            }
            static double K2F(double k){
                return k*1.8-459.67;
            }





}