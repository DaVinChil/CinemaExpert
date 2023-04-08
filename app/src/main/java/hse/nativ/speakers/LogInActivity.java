package hse.nativ.speakers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInActivity extends AppCompatActivity {

    private boolean passHiden = true;
    private int passCursorPos = 0;
    private EditText passInput;
    private EditText emailInput;
    private ImageView passEye;
    private ImageView backArrow;
    private MaterialButton loginBtn;
    private TextView forgotPassBtn;
    private TextView signUpBtn;
    private ImageView yandexSignup;
    private ImageView googleSignup;
    private ImageView vkSignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setFirebase();
        findAll();
        setViews();
    }

    protected void setFirebase(){
        mAuth = FirebaseAuth.getInstance();
    }

    protected void setViews() {
        setPasswordEye();
        setBackArrow();
        setSignUpBtn();
        setLoginBtn();
        setForgotPassBtn();
        setSocialMediaSignup();
    }

    protected void setLoginBtn() {
        loginBtn.setOnClickListener(v -> {
            String userEmail = getInputEmailAndVerify();
            String userPass = getPassInputAndVerify();

            if(userEmail == null || userPass == null){ return; }

            performLogIn(userEmail, userPass);
            Intent mainScreen = new Intent(this, MainScreenActivity.class);
            startActivity(mainScreen);
        });
    }

    protected String getInputEmailAndVerify(){
        String userEmail = String.valueOf(emailInput.getText());

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return null;
        }

        userEmail = userEmail.replaceAll("\\s", "");

        return userEmail;
    }

    protected String getPassInputAndVerify(){
        String userPass = String.valueOf(passInput.getText());

        if (TextUtils.isEmpty(userPass)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return null;
        }

        userPass = userPass.replaceAll("\\s", "");

        return userPass;
    }

    protected void performLogIn(String userEmail, String userPass){
        mAuth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogInActivity.this, "Welcome, " + task.getResult().getUser().getDisplayName(),
                                    Toast.LENGTH_SHORT);
                            // TODO: SEND INTENT TO MAIN PAGE
                        } else {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    protected void setForgotPassBtn() {
        // TODO: Set Forgot password text pressing
    }

    protected void setSocialMediaSignup() {
        // TODO: Set Social Media Signup
    }

    protected void setSignUpBtn() {
        signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void setPasswordEye() {
        passEye.setOnClickListener((v) -> {
            if (passHiden) {
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

    protected void setBackArrow() {
        backArrow.setOnClickListener(view -> {
            finish();
        });
    }

    protected void findAll() {
        loginBtn = findViewById(R.id.main_sign_up_button);
        signUpBtn = findViewById(R.id.sign_up);
        forgotPassBtn = findViewById(R.id.forgot_pass);

        passInput = findViewById(R.id.password_input);
        emailInput = findViewById(R.id.email_input);

        passEye = findViewById(R.id.pass_eye);

        yandexSignup = findViewById(R.id.yandex_sign);
        googleSignup = findViewById(R.id.google_sign);
        vkSignup = findViewById(R.id.vk_sign);

        backArrow = findViewById(R.id.back_arrow);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}