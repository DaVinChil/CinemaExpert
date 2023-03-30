package hse.nativ.speakers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
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
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findAll();
        setViews();
    }

    protected void setViews() {
        setPassEye();
        setConfPassEye();
        setSignInBtn();
        setSocialMediaSignup();
        setSignUpBtn();
        setBackArrow();
    }

    protected void setBackArrow() {
        backArrow.setOnClickListener(view -> {
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
                passEye.setImageResource(R.drawable.password_eye_crossed);

                if (passInput.hasFocus()) {
                    int pos = passInput.getText().length();
                    passInput.setTransformationMethod(null);
                    passInput.setSelection(pos);
                } else {
                    passInput.setTransformationMethod(null);
                }
            } else {
                passHiden = true;
                passEye.setImageResource(R.drawable.password_eye);

                if (passInput.hasFocus()) {
                    int pos = passInput.getText().length();
                    passInput.setTransformationMethod(new PasswordTransformationMethod());
                    passInput.setSelection(pos);
                } else {
                    passInput.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    protected void setConfPassEye() {
        confPassEye.setOnClickListener(v -> {
            if (confPassHiden) {
                confPassHiden = false;
                confPassEye.setImageResource(R.drawable.password_eye_crossed);

                if (passInput.hasFocus()) {
                    int pos = confirmPassInput.getText().length();
                    confirmPassInput.setTransformationMethod(null);
                    confirmPassInput.setSelection(pos);
                } else {
                    confirmPassInput.setTransformationMethod(null);
                }
            } else {
                confPassHiden = true;
                confPassEye.setImageResource(R.drawable.password_eye);

                if (confirmPassInput.hasFocus()) {
                    int pos = confirmPassInput.getText().length();
                    confirmPassInput.setTransformationMethod(new PasswordTransformationMethod());
                    confirmPassInput.setSelection(pos);
                } else {
                    confirmPassInput.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    protected void setSignInBtn() {
        signInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void setSignUpBtn() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    String userName = null;
                    String userEmail = null;
                    String pass = null;
                    String confPass = null;

                    if (isEmptyInput("User name", userNameInput)) {
                        return;
                    } else {
                        userName = userNameInput.getText().toString();
                    }

                    if (isEmptyInput("Email", emailInput)) {
                        return;
                    } else {
                        userEmail = emailInput.getText().toString();
                        if (!FireStoreTools.isCorrectEmailInput(userEmail)) {
                            Toast.makeText(SignUpActivity.this, "Wrong email input", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    if (isEmptyInput("Password", passInput)) {
                        return;
                    } else {
                        pass = passInput.getText().toString();
                    }

                    if (confirmPassInput.getText() == null || confirmPassInput.getText().length() == 0) {
                        Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        confPass = confirmPassInput.getText().toString();
                        if (!pass.equals(confPass)) {
                            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    
                    final boolean[] isAlreadyExist = {false, false};

                    db.collection(FireStoreTools.USERS_COLLECTION).document(userEmail).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.exists()){
                                        Toast.makeText(SignUpActivity.this, "Account with this email already exists", Toast.LENGTH_SHORT).show();
                                        isAlreadyExist[0] = true;
                                    }
                                    isAlreadyExist[1] = true;
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    isAlreadyExist[0] = false;
                                    isAlreadyExist[1] = true;
                                }
                            });

                    while (!isAlreadyExist[1]){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Toast.makeText(SignUpActivity.this, "Interruption occurred", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    if(isAlreadyExist[0]){
                        return;
                    }

                    Map<String, String> newUser = new HashMap<>();
                    newUser.put(FireStoreTools.USER_NAME_TAG, userName);
                    newUser.put(FireStoreTools.USER_EMAIL_TAG, userEmail);
                    newUser.put(FireStoreTools.USER_PASSWORD_TAG, pass);

                    String finalUserName = userName.replaceAll("\\s", "");
                    String finalUserEmail = userEmail.replaceAll("\\s", "");
                    String finalPass = pass.replaceAll("\\s", "");
                    db.collection(FireStoreTools.USERS_COLLECTION).document(userEmail).set(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    CurrentUser.setUserPassword(finalPass);
                                    CurrentUser.setUserEmail(finalUserEmail);
                                    CurrentUser.setUserName(finalUserName);

                                    Toast.makeText(SignUpActivity.this, "Welcome, " + finalUserName, Toast.LENGTH_SHORT).show();
                                    // TODO: Send intent to main page
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "Cannot create an account", Toast.LENGTH_SHORT).show();
                                }
                            });
                }).start();
            }
        });

    }

    protected boolean isEmptyInput(String toastName, EditText input) {
        if (input.getText() == null || input.getText().length() == 0) {
            Toast.makeText(this, toastName + " cannot be empty.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    protected void findAll() {
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}