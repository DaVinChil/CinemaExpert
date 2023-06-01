package hse.nativ.speakers;

import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.Month;

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
    public static LocalDate parseDateAsString(String date) {
        LocalDate localDate;
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            localDate = LocalDate.parse(date);
        } else if (date.matches("\\d{4}-\\d{2}")) {
            String[] dates = date.split("-");
            localDate = LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), 1);
        } else {
            localDate = LocalDate.of(Integer.parseInt(date), 1, 1);
        }
        return localDate;
    }

    public static String getDateWithMonthsNames(String date) {
        String[] dates = date.split("-");
        String result;
        if (dates.length == 3) {
            Month monthName = Month.of(Integer.parseInt(dates[1]));
            result = dates[2] + " " + monthName.name().toLowerCase() + " " + dates[0];
        } else if (dates.length == 2) {
            Month monthName = Month.of(Integer.parseInt(dates[1]));
            result = monthName.name().toLowerCase() + " " + dates[0];
        } else {
            result = dates[0];
        }
        return result;
    }

    static class EdgeDecorator extends RecyclerView.ItemDecoration {

        private final int edgePadding;

        public EdgeDecorator(int edgePadding) {
            this.edgePadding = edgePadding;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int itemCount = state.getItemCount();
            final int itemPosition = parent.getChildAdapterPosition(view);

            // no position, leave it alone
            if (itemPosition == RecyclerView.NO_POSITION) {
                return;
            }
            // last item
            else if (itemCount > 0 && itemPosition == itemCount - 1) {
                outRect.set(view.getPaddingLeft(), view.getPaddingTop(), edgePadding, view.getPaddingBottom());
            }
            else if (itemCount > 0 && itemPosition == 0) {
                outRect.set(edgePadding - 5, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            }
        }
    }
}
