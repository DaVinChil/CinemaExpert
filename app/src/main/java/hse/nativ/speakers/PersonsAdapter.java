package hse.nativ.speakers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout view;
        public ViewHolder(ConstraintLayout view) {
            super(view);
            this.view = view;
        }
    }

    private List<Person> persons;
    private Map<String, List<String>> characters;

    public PersonsAdapter(List<Person> persons, Map<String, List<String>> characters) {
        this.persons = persons;
        this.characters = characters;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public PersonsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                .apply(new RequestOptions().override(100, 120))
                .into(personPhoto);

        TextView personName = personView.findViewById(R.id.person_name);
        personName.setText(person.getFullName());

        TextView personCharacters = personView.findViewById(R.id.person_characters);
        String charactersString = "";
        for (String curCharacter : characters.get(person.getId())) {
            charactersString += curCharacter + ", ";
        }
        charactersString = charactersString.substring(0, charactersString.length() - 2);
        personCharacters.setText(charactersString);
    }
}
