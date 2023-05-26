package hse.nativ.speakers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setFirebase();

        findAll();
        setViews();
    }

    protected void setFirebase() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    protected void setViews() {
        setPassEye();
        setConfPassEye();
        setSignInBtn();
        setSocialMediaSignup();
        setSignUpBtn();
    }

    protected void setBackArrow() {
        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void setSocialMediaSignup() {
        // TODO: Set Social Media Signup
    }

    protected void setPassEye() {
        passEye.setOnClickListener(v -> {
            if (passHiden) {
                passHiden = false;
                showPassword(passEye, passInput);
            } else {
                passHiden = true;
                hidePassword(passEye, passInput);
            }
        });
    }

    protected void setConfPassEye() {
        confPassEye.setOnClickListener(v -> {
            if (confPassHiden) {
                confPassHiden = false;
                showPassword(confPassEye, confirmPassInput);
            } else {
                confPassHiden = true;
                hidePassword(confPassEye, confirmPassInput);
            }
        });
    }

    protected void hidePassword(ImageView eye, EditText input){
        eye.setImageResource(R.drawable.password_eye);

        if (input.hasFocus()) {
            int pos = input.getText().length();
            input.setTransformationMethod(new PasswordTransformationMethod());
            input.setSelection(pos);
        } else {
            input.setTransformationMethod(new PasswordTransformationMethod());
        }
    }

    protected void showPassword(ImageView eye, EditText input){
        eye.setImageResource(R.drawable.password_eye_crossed);

        if (input.hasFocus()) {
            int pos = input.getText().length();
            input.setTransformationMethod(null);
            input.setSelection(pos);
        } else {
            input.setTransformationMethod(null);
        }
    }

    protected void setSignInBtn() {
        signInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(intent);
            //finish();
        });
    }

    protected String getUserNameAndVerify() {
        String userName = String.valueOf(userNameInput.getText());
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(SignUpActivity.this, "User name can not be empty.", Toast.LENGTH_SHORT).show();
            return null;
        }

        return userName.replaceAll("\\s", "");
    }

    protected String getEmailAndVerify() {
        String userEmail = String.valueOf(emailInput.getText());
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(SignUpActivity.this, "Email can not be empty.", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            userEmail = userEmail.replaceAll("\\s", "");
            if (!FireStoreTools.isCorrectEmailInput(userEmail)) {
                Toast.makeText(this, "Email can not be empty.", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return userEmail;
    }

    protected String getPassAndVerifyAndCompare() {
        String pass = String.valueOf(passInput.getText());
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(SignUpActivity.this, "Password can not be empty.", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            pass = pass.replaceAll("\\s", "");

            if (!verifyPassword(pass)) {
                System.out.println(pass);
                Toast.makeText(this, "Password too weak", Toast.LENGTH_SHORT).show();
                return null;
            }

            String confPass = getConfirmPassAndVerify();
            if (confPass == null) {
                return null;
            }

            if (!pass.equals(confPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return pass;
    }

    protected String getConfirmPassAndVerify() {
        String confPass = String.valueOf(confirmPassInput.getText());
        if (TextUtils.isEmpty(confPass)) {
            Toast.makeText(SignUpActivity.this, "Confirmation password can not be empty.", Toast.LENGTH_SHORT).show();
            return null;
        }

        return confPass.replaceAll("\\s", "");
    }

    protected boolean verifyPassword(String pass) {
        if (pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(.{8,})")) {
            return true; // strong
        } else if (pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(.{8,})")) {
            return true; // medium
        }

        return false;
    }

    protected void setSignUpBtn() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().post(() -> {
                    String userName = getUserNameAndVerify();
                    String userEmail = getEmailAndVerify();
                    String pass = getPassAndVerifyAndCompare();
                    if (userName == null || userEmail == null || pass == null) { return; }

                    createAccount(userEmail, pass, userName);
                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        });
    }

    protected void createAccount(String userEmail, String pass, String userName){
        mAuth.createUserWithEmailAndPassword(userEmail, pass)
                .addOnCompleteListener(task -> {
                    onCompleteCreatingAccount(task, userName);
                })
                .addOnFailureListener(e -> {
                    onFailureCreatingAccount(e);
                });
    }

    protected void onCompleteCreatingAccount(Task<AuthResult> task, String userName){
        if (task.isSuccessful()) {
            task.getResult().getUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(userName).build());
            Toast.makeText(SignUpActivity.this, "Welcome, " + userName + ".", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onFailureCreatingAccount(Exception e){
        if(e instanceof FirebaseAuthWeakPasswordException){
            Toast.makeText(SignUpActivity.this, "Password too weak", Toast.LENGTH_SHORT).show();
        } else if(e instanceof FirebaseAuthInvalidCredentialsException){
            Toast.makeText(SignUpActivity.this, "Wrong email", Toast.LENGTH_SHORT).show();
        } else if(e instanceof FirebaseAuthUserCollisionException){
            Toast.makeText(SignUpActivity.this, "Account with that email already exists.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void findAll() {
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}