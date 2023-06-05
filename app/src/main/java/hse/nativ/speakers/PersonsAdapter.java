package hse.nativ.speakers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout view;
        public ViewHolder(ConstraintLayout view) {
            super(view);
            this.view = view;
        }
    }

    private List<Person> persons;
    private Map<String, List<String>> roles;

    public PersonsAdapter(List<Person> persons, Map<String, List<String>> roles) {
        this.persons = persons;
        this.roles = roles;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout personView = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_person, parent, false);
        return new PersonsAdapter.ViewHolder(personView);
    }

    @Override
    public void onBindViewHolder(PersonsAdapter.ViewHolder holder, final int position) {
        ConstraintLayout personView = holder.view;

        Person person = persons.get(position);

        ImageView personPhoto = personView.findViewById(R.id.person_photo);
        Glide.with(MainScreenActivity.context)
                .load(person.getPhoto().getUrl())
                .into(personPhoto);

        TextView personName = personView.findViewById(R.id.person_name);
        personName.setText(person.getFullName());

        if (roles != null) {
            TextView personRoles = personView.findViewById(R.id.person_roles);
            String rolesString = "";
            for (String role : roles.get(person.getId())) {
                rolesString += role + ", ";
            }
            rolesString = rolesString.substring(0, rolesString.length() - 2);
            personRoles.setText(rolesString);
        }

        personView.setOnClickListener(view -> {
            FragmentTransaction ft = MainScreenActivity.context.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new PersonDetailsFragment(person));
            ft.addToBackStack(null);
            ft.commit();
        });
    }
}
