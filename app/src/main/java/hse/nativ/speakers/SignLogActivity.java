package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SignLogActivity extends AppCompatActivity {

    private TextView cinemaTtl;
    private MaterialButton getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_log);

        findAll();
        setGradientOnTitle();

        setButtons();
    }

    protected void setButtons(){
        getStartedBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(SignLogActivity.this, LogInActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        });
    }

    protected void findAll(){
        cinemaTtl = findViewById(R.id.cinema_text);
        getStartedBtn = findViewById(R.id.get_started_button);
    }

    protected void setGradientOnTitle(){
        TextPaint paint = cinemaTtl.getPaint();
        float width = paint.measureText("CINEMA");

        Shader textShader = new LinearGradient(0, 0, width, cinemaTtl.getTextSize(),
                new int[]{
                        Color.parseColor("#F91D00"),
                        Color.parseColor("#FEB700")
                }, null, Shader.TileMode.CLAMP);
        cinemaTtl.getPaint().setShader(textShader);
        cinemaTtl.setShadowLayer(1, 0, 0, Color.BLACK);
    }
}