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

public class MainActivity extends AppCompatActivity {

    TextView name, surname, town;
    EditText yourName, yourSurname;
    Spinner townSpinner;
    Button saveToMemory, goToImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readInternal("data.txt");

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        town = findViewById(R.id.town_text);

        yourName = findViewById(R.id.your_name_edit_text);
        yourSurname = findViewById(R.id.your_surname_edit_text);

        townSpinner = findViewById(R.id.spinner);

        saveToMemory = findViewById(R.id.save_to_memory_button);
        goToImages = findViewById(R.id.go_to_images_button);

        goToImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                startActivity(intent);
            }
        });

        saveToMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = yourName.getText().toString();
                String _surname = yourSurname.getText().toString();
                String _town = townSpinner.getSelectedItem().toString();

                String content = _name;
                content += "\n" + _surname;
                content += "\n" + _town;

                writeInternal("data.txt", content);

                String result = readInternal("data.txt");

                String[] rs = result.split("\\r?\\n");

                name.setText(rs[0]);
                surname.setText(rs[1]);
                town.setText(rs[2]);
            }
        });
    }

    private void writeInternal(String fileName, String content){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer= new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(getApplicationContext(), "Wrote to file: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readInternal(String fileName){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}