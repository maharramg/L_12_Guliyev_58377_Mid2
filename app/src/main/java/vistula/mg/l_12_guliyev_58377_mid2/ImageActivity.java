package vistula.mg.l_12_guliyev_58377_mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {

    ImageView imageTown, imageCalc, imageMemory;
    TextView name, surname, town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        readInternal("data.txt");

        String result = readInternal("data.txt");

        String[] rs = result.split("\\r?\\n");

        name = findViewById(R.id.name2);
        surname = findViewById(R.id.surname2);
        town = findViewById(R.id.town_text2);

        imageTown = findViewById(R.id.imageView2);
        imageCalc = findViewById(R.id.imageView3);
        imageMemory = findViewById(R.id.imageView4);

        name.setText(rs[0]);
        surname.setText(rs[1]);
        town.setText(rs[2]);

        imageTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        imageCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
                startActivity(intent);
            }
        });

        imageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemoryActivity.class);
                startActivity(intent);
            }
        });

    }

    private String readInternal(String fileName) {
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