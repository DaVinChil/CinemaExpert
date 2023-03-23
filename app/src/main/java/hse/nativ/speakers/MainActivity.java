package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, second.class);
        startActivity(intent);
        int a = 10;
        int mod = a % 3;// loh
        System.out.println("Hello, world!");
    }
}