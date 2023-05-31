package hse.nativ.speakers;

import android.widget.TextView;

public class CustomizeHelper {
    public static void setGradeBackground(TextView movieGrade, String grade) {
        if (Double.parseDouble(grade) >= 7) {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_green);
        } else if (Double.parseDouble(grade) >= 5) {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_gray);
        } else {
            movieGrade.setBackgroundResource(R.drawable.grade_movie_background_red);
        }
    }
}
