package hse.nativ.speakers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.time.LocalDate;
import java.time.Period;

import hse.nativ.speakers.HelpClasses.CustomizeHelper;
import hse.nativ.speakers.DatabaseClasses.DataInflater;
import hse.nativ.speakers.Activities.MainScreenActivity;
import hse.nativ.speakers.DatabaseClasses.Person;
import hse.nativ.speakers.R;

public class PersonDetailsFragment extends Fragment {

    private final Person person;
    public PersonDetailsFragment(Person person) {this.person = person;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView view = (ScrollView) inflater.inflate(R.layout.fragment_person_details, container, false);

        ImageView personPhoto = view.findViewById(R.id.person_details_photo);
        Glide.with(MainScreenActivity.context).load(person.getPhoto().getUrl()).into(personPhoto);

        TextView personFullName = view.findViewById(R.id.person_details_name);
        personFullName.setText(person.getFullName());

        TextView lifeDate = view.findViewById(R.id.person_details_birth_date);
        TextView yearAndHeight = view.findViewById(R.id.person_details_hight_and_age);
        if (!person.getBirthDate().equals("?")) {
            lifeAndHeightSet(yearAndHeight, lifeDate);
        }

        RecyclerView personFilmography = view.findViewById(R.id.person_details_filmography);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        personFilmography.setLayoutManager(layoutManager);
        personFilmography.addItemDecoration(new CustomizeHelper.EdgeDecorator(40));
        DataInflater.inflateFilmographyByPersonId(personFilmography, person.getId());

        return view;
    }

    private void lifeAndHeightSet(TextView yearAndHeight, TextView lifeDate) {
        double height = person.getHeight();
        String birthDate = person.getBirthDate();
        LocalDate birthLocalDate = CustomizeHelper.parseDateAsString(birthDate);
        String birthDateMothName = CustomizeHelper.getDateWithMonthsNames(birthDate);
        String deathDate = person.getDeathDate();
        if (!deathDate.equals("-")) {
            String deathDateMonthName = CustomizeHelper.getDateWithMonthsNames(deathDate);
            LocalDate deathLocaleDate = CustomizeHelper.parseDateAsString(deathDate);
            lifeDate.setText(birthDateMothName + " - " + deathDateMonthName);
            if (height < 1) {
                yearAndHeight.setText(Period.between(birthLocalDate, deathLocaleDate).getYears() + " years ");
            } else {
                yearAndHeight.setText(Period.between(birthLocalDate, deathLocaleDate).getYears() + " years " + " · " + height + " cm");
            }
        } else {
            lifeDate.setText(birthDateMothName);
            if (height < 1) {
                yearAndHeight.setText(Period.between(birthLocalDate, LocalDate.now()).getYears() + " years ");
            } else {
                yearAndHeight.setText(Period.between(birthLocalDate, LocalDate.now()).getYears() + " years " + " · " + height + " cm");
            }
        }
    }
}