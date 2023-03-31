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
    private TextView expertTtl;
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
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent);
        });
    }

    protected void findAll(){
        cinemaTtl = findViewById(R.id.cinema_text);
        expertTtl = findViewById(R.id.expert_text);
        getStartedBtn = findViewById(R.id.get_started_button);
    }

    protected void setGradientOnTitle(){
        TextPaint paint = expertTtl.getPaint();
        float width = paint.measureText("EXPERT");

        Shader textShader = new LinearGradient(0, 0, 0, expertTtl.getTextSize(),
                new int[]{
                        Color.parseColor("#CB0000"),
                        Color.parseColor("#900000")
                }, null, Shader.TileMode.CLAMP);
        expertTtl.getPaint().setShader(textShader);
        expertTtl.setShadowLayer(1, 0, 0, Color.BLACK);
    }
}