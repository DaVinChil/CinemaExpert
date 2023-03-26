package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class LogInActivity extends AppCompatActivity {

    private boolean passHiden = true;
    private int passCursorPos = 0;
    private EditText passInput;
    private ImageView passEye;
    private ImageView backArrow;
    private MaterialButton loginBtn;
    private TextView forgotPassBtn;
    private TextView signUpBtn;
    private ImageView yandexSignup;
    private ImageView googleSignup;
    private ImageView vkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findAll();
        setViews();
    }

    protected void setViews(){
        setPasswordEye();
        setBackArrow();
        setSignUpBtn();
        setLoginBtn();
        setForgotPassBtn();
        setSocialMediaSignup();
    }

    protected void setLoginBtn(){
        // TODO: Set login button pressing
    }

    protected void setForgotPassBtn(){
        // TODO: Set Forgot password text pressing
    }

    protected void setSocialMediaSignup(){
        // TODO: Set Social Media Signup
    }

    protected void setSignUpBtn(){
        signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void setPasswordEye(){
        passEye.setOnClickListener((v) -> {
            if(passHiden){
                passHiden = false;
                passCursorPos = passInput.getText().length();
                passEye.setImageResource(R.drawable.password_eye_crossed);
                passInput.setTransformationMethod(null);
                passInput.setSelection(passCursorPos);
            } else {
                passHiden = true;
                passCursorPos = passInput.getText().length();
                passEye.setImageResource(R.drawable.password_eye);
                passInput.setTransformationMethod(new PasswordTransformationMethod());
                passInput.setSelection(passCursorPos);
            }
        });
    }

    protected void setBackArrow(){
        backArrow.setOnClickListener(view -> {
            finish();
        });
    }

    protected void findAll(){
        loginBtn = findViewById(R.id.main_sign_up_button);
        forgotPassBtn = findViewById(R.id.forgot_pass);
        signUpBtn = findViewById(R.id.sign_up);
        yandexSignup = findViewById(R.id.yandex_sign);
        googleSignup = findViewById(R.id.google_sign);
        vkSignup = findViewById(R.id.vk_sign);
        passInput = findViewById(R.id.confirm_password_input);
        passEye = findViewById(R.id.pass_eye);
        backArrow = findViewById(R.id.back_arrow);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}