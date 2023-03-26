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
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SignLogActivity extends AppCompatActivity {

    private TextView cinemaTtl;
    private TextView expertTtl;
    private MaterialButton loginBtn;
    private MaterialButton signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_log);

        findAll();
        setAppearingAnim();
        setGradientOnTitle();

        setButtons();
    }

    protected void setButtons(){
        loginBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(SignLogActivity.this, LogInActivity.class);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent);
        });
        signupBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(SignLogActivity.this, SignUpActivity.class);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent);
        });
    }

    protected void findAll(){
        cinemaTtl = findViewById(R.id.cinema_text);
        expertTtl = findViewById(R.id.expert_text);
        loginBtn = findViewById(R.id.log_in_button);
        signupBtn = findViewById(R.id.main_sign_up_button);
    }

    protected void hideViews(View... views){
        for(View v : views){
            v.setVisibility(View.INVISIBLE);
        }
    }

    protected void revealViews(View... views){
        for(View v : views){
            v.setVisibility(View.VISIBLE);
        }
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

    protected void setAppearingAnim(){
        hideViews(cinemaTtl, expertTtl, loginBtn, signupBtn);

        viewTranslationY(cinemaTtl, 210f, 1);
        viewTranslationY(expertTtl, 210f, 1);

        revealViews(cinemaTtl, expertTtl);

        new Handler().postDelayed(() -> {
            viewTranslationY(cinemaTtl, 10f, 700);
            viewTranslationY(expertTtl, 10f, 700);
        }, 1000);

        new Handler().postDelayed(()->{
            revealWithAnim(500, loginBtn, signupBtn);
        }, 1700);
    }

    protected void revealWithAnim(int duration, View... views){
        for(View v : views){
            v.setAlpha(0f);
            revealViews(v);
            v.animate().alpha(1f)
                    .setDuration(duration)
                    .setListener(null);
        }
    }

    protected void viewTranslationY(View v, float offset, int duration){
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", offset);
        anim.setDuration(duration);
        anim.start();
    }
}