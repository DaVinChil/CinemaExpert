package hse.nativ.speakers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private AppCompatActivity context;

    public ProfileFragment() {
        this.context = MainScreenActivity.context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        Button logout = constraintLayout.findViewById(R.id.logout_button);
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, LogInActivity.class);
            startActivity(intent);
            context.finish();
        });
        return constraintLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navigationView = context.findViewById(R.id.bottom_navigation);
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}