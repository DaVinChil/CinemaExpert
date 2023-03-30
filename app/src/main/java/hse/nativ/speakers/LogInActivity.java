package hse.nativ.speakers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
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
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findAll();
        setViews();
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
            String userEmail = null;
            String userPass = null;

            if (emailInput.getText() == null || emailInput.getText().length() == 0) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {
                userEmail = emailInput.getText().toString().replaceAll("\\s", "");;
                if (!FireStoreTools.isCorrectEmailInput(userEmail)) {
                    Toast.makeText(this, "Wrong email Input", Toast.LENGTH_SHORT).show();
                }
            }

            if (passInput.getText() == null || passInput.getText().length() == 0) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            userPass = passInput.getText().toString();

            String finalUserPass = userPass.replaceAll("\\s", "");
            db.collection(FireStoreTools.USERS_COLLECTION).document(userEmail).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (!documentSnapshot.exists()) {
                                Toast.makeText(LogInActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if(!documentSnapshot.getString(FireStoreTools.USER_PASSWORD_TAG).equals(finalUserPass)){
                                Toast.makeText(LogInActivity.this, "Email or password mismatch", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            CurrentUser.setUserEmail(documentSnapshot.getString(FireStoreTools.USER_EMAIL_TAG));
                            CurrentUser.setUserIdDocument(documentSnapshot.getId());
                            CurrentUser.setUserName(documentSnapshot.getString(FireStoreTools.USER_NAME_TAG));
                            CurrentUser.setUserPassword(documentSnapshot.getString(FireStoreTools.USER_PASSWORD_TAG));

                            Toast.makeText(LogInActivity.this, "Welcome, " + CurrentUser.getUserName() + "!", Toast.LENGTH_SHORT).show();
                            // TODO: SEND INTENT TO MAIN PAGE
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LogInActivity.this, "Could not find user", Toast.LENGTH_SHORT).show();
                        }
                    });
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
        forgotPassBtn = findViewById(R.id.forgot_pass);
        signUpBtn = findViewById(R.id.sign_up);
        yandexSignup = findViewById(R.id.yandex_sign);
        googleSignup = findViewById(R.id.google_sign);
        vkSignup = findViewById(R.id.vk_sign);
        passInput = findViewById(R.id.password_input);
        emailInput = findViewById(R.id.email_input);
        passEye = findViewById(R.id.pass_eye);
        backArrow = findViewById(R.id.back_arrow);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}