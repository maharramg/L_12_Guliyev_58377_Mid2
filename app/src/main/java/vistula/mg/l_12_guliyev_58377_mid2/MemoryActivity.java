package vistula.mg.l_12_guliyev_58377_mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MemoryActivity extends AppCompatActivity {


    TextView name, surname, town_text;
    EditText yourName, yourSurname, town;
    Button saveToMemory, goToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        readInternal("data.txt");

        String result = readInternal("data.txt");

        String[] rs = result.split("\\r?\\n");

        name = findViewById(R.id.name3);
        surname = findViewById(R.id.surname3);
        town_text = findViewById(R.id.town_text3);

        yourName = findViewById(R.id.your_name_edit_text2);
        yourSurname = findViewById(R.id.your_surname_edit_text2);
        town = findViewById(R.id.your_town_edit_text2);

        saveToMemory = findViewById(R.id.save_to_memory2);
        goToMain = findViewById(R.id.to_main);

        name.setText(rs[0]);
        surname.setText(rs[1]);
        town_text.setText(rs[2]);

        yourName.setText(rs[0]);
        yourSurname.setText(rs[1]);
        town.setText(rs[2]);

        saveToMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = yourName.getText().toString();
                String _surname = yourSurname.getText().toString();
                String _town = town.getText().toString();

                String content = _name;
                content += "\n" + _surname;
                content += "\n" + _town;

                writeInternal("data.txt", content);

                String result = readInternal("data.txt");

                String[] rs = result.split("\\r?\\n");

                name.setText(rs[0]);
                surname.setText(rs[1]);
                town_text.setText(rs[2]);
            }
        });

        goToMain.setOnClickListener(new View.OnClickListener() {
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