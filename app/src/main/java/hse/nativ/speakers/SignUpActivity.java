package hse.nativ.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    private ImageView backArrow;
    private EditText emailInput;
    private EditText userNameInput;
    private EditText confirmPassInput;
    private EditText passInput;
    private MaterialButton signUpBtn;
    private ImageView yandexSignup;
    private ImageView googleSignup;
    private ImageView vkSignup;
    private TextView signInBtn;
    private ImageView passEye;
    private ImageView confPassEye;

    private boolean passHiden = true;
    private boolean confPassHiden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findAll();
        setViews();
    }

    protected void setViews(){
        setPassEye();
        setConfPassEye();
        setSignInBtn();
        setSocialMediaSignup();
        setSignUpBtn();
        setBackArrow();
    }

    protected void setBackArrow(){
        backArrow.setOnClickListener(view -> {
            finish();
        });
    }

    protected void setSocialMediaSignup(){
        // TODO: Set Social Media Signup
    }
    protected void setPassEye(){
        passEye.setOnClickListener(v -> {
            if(passHiden){
                passHiden = false;
                passEye.setImageResource(R.drawable.password_eye_crossed);

                if(passInput.hasFocus()){
                    int pos = passInput.getText().length();
                    passInput.setTransformationMethod(null);
                    passInput.setSelection(pos);
                } else {
                    passInput.setTransformationMethod(null);
                }
            } else {
                passHiden = true;
                passEye.setImageResource(R.drawable.password_eye);

                if(passInput.hasFocus()){
                    int pos = passInput.getText().length();
                    passInput.setTransformationMethod(new PasswordTransformationMethod());
                    passInput.setSelection(pos);
                } else {
                    passInput.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    protected void setConfPassEye(){
        confPassEye.setOnClickListener(v -> {
            if(confPassHiden){
                confPassHiden = false;
                confPassEye.setImageResource(R.drawable.password_eye_crossed);

                if(passInput.hasFocus()){
                    int pos = confirmPassInput.getText().length();
                    confirmPassInput.setTransformationMethod(null);
                    confirmPassInput.setSelection(pos);
                } else {
                    confirmPassInput.setTransformationMethod(null);
                }
            } else {
                confPassHiden = true;
                confPassEye.setImageResource(R.drawable.password_eye);

                if(confirmPassInput.hasFocus()){
                    int pos = confirmPassInput.getText().length();
                    confirmPassInput.setTransformationMethod(new PasswordTransformationMethod());
                    confirmPassInput.setSelection(pos);
                } else {
                    confirmPassInput.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    protected void setSignInBtn(){
        signInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void setSignUpBtn(){
        // TODO: Set sign up button pressing
    }

    protected void findAll(){
        backArrow = findViewById(R.id.back_arrow);
        emailInput = findViewById(R.id.email_input);
        userNameInput = findViewById(R.id.user_name_input);
        confirmPassInput = findViewById(R.id.confirm_password_input);
        passInput = findViewById(R.id.password_input);
        signUpBtn = findViewById(R.id.main_sign_up_button);
        yandexSignup = findViewById(R.id.yandex_sign);
        vkSignup = findViewById(R.id.vk_sign);
        googleSignup = findViewById(R.id.google_sign);
        passEye = findViewById(R.id.pass_eye);
        confPassEye = findViewById(R.id.confirm_pass_eye);
        signInBtn = findViewById(R.id.sign_in);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}