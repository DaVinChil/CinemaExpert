package hse.nativ.speakers.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import hse.nativ.speakers.Activities.LogInActivity;
import hse.nativ.speakers.Activities.MainScreenActivity;
import hse.nativ.speakers.R;

public class ProfileFragment extends Fragment {
    private ConstraintLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) return view;

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        Button logout = constraintLayout.findViewById(R.id.logout_button);
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainScreenActivity.context, LogInActivity.class);
            startActivity(intent);
            MainScreenActivity.context.finish();
        });
        this.view = constraintLayout;
        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = MainScreenActivity.context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}