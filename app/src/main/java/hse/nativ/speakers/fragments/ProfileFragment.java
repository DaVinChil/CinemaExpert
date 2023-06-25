package hse.nativ.speakers.fragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import hse.nativ.speakers.activities.LogInActivity;
import hse.nativ.speakers.activities.MainScreenActivity;
import hse.nativ.speakers.CurrentUser;
import hse.nativ.speakers.R;

public class ProfileFragment extends Fragment {
    private ConstraintLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) return view;

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        setAllViews(constraintLayout);
        this.view = constraintLayout;
        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = MainScreenActivity.context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(2).setChecked(true);
    }

    private void setAllViews(ConstraintLayout layout) {
        Button logout = layout.findViewById(R.id.logout_button);
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainScreenActivity.context, LogInActivity.class);
            startActivity(intent);
            MainScreenActivity.context.finish();
        });

        ImageView photo = layout.findViewById(R.id.profile_fragment_photo);
        String userPhotoUri = CurrentUser.getPhoto();
        Glide.with(MainScreenActivity.context).load(userPhotoUri == null ? R.drawable.profile_standart_photo : userPhotoUri).into(photo);

        EditText userName = layout.findViewById(R.id.profile_fragment_name);
        String savedUserName = CurrentUser.getUserName();
        if (savedUserName.equals("") || savedUserName == null) {
            userName.setText("Default User");
        } else {
            userName.setText(savedUserName);
        }
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CurrentUser.setUserName(userName.getText().toString());
                CurrentUser.updateUserSettings();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}