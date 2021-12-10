/***********************************************************************
 *                                                                     *
 *   Class Name : Result.java                                          *
 *                                                                     *
 *   Purpose  : The purpose of this class is  to display the final     *
 *              percentage attained by the student in Assignments and  *
 *              QuizExams for the course selected in the first         *
 *              Activity page                                          *
 *                                                                     *
 ***********************************************************************/

package edu.niu.z1862674.graduateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Gravity;
import android.util.Log;
import android.view.View;
import android.graphics.Point;
import android.widget.GridLayout;
import java.text.DecimalFormat;


public class Result extends AppCompatActivity {

    //declaring instance variables
    public static final String RA = "ResultActivity";
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;

    private Button button;
    private static float finalQuizExamPercentage;
    private static float finalAssignmentPercentage;
    private static float totalPercentage;
    private int courseSelected;
    private int examQuizPercent = 70;
    private int assignPercent = 30;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    /************************************************************************
     *                                                                      *
     *   onCreate() Method helps in starting the activity.Whenever activity *
     *   gets forcefully terminated, object of Bundle Class will save the   *
     *   state of an activity                                               *
     *                                                                      *
     ************************************************************************/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(RA, "welcome to result");
        //getting the values from the previous activity to display on the activity page
        finalAssignmentPercentage = getIntent().getFloatExtra("assignmentPercentage",0);
        finalQuizExamPercentage = getIntent().getFloatExtra("examQuizpercentage",0);
        courseSelected = getIntent().getIntExtra("courseSelected",0);
        Log.w(RA, "finalQuizExamPercentage = " + finalQuizExamPercentage);
        Log.w(RA, "finalAssignmentPercentage = " + finalAssignmentPercentage);
        Log.w(RA, "courseSelected = " + courseSelected);
        //setting the weightage for assignments and ExamsQuiz depending on the course selected
        if(courseSelected == 241)
        {
            examQuizPercent = 60;
            assignPercent = 40;
        }
        calculateTotalPercent();
        //building User Interface through code
        buildUiByCode();

    }

    /************************************************************************
     *                                                                      *
     *   calculateTotalPercent() Method helps in getting the overall        *
     *   percentage out of 100% by adding Assignments and ExamQuizzes       *
     *   percentage                                                         *
     *                                                                      *
     ************************************************************************/
    public void calculateTotalPercent(){
        totalPercentage = finalAssignmentPercentage + finalQuizExamPercentage;
    }

    /************************************************************************
     *                                                                      *
     *   buildUiByCode() Method helps in building the UI programatically    *
     *                                                                      *
     ************************************************************************/
    public void buildUiByCode() {
        //getting the screen dimensions
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x;
        Log.w(RA, "welcome to result1");
        //setting the scroll view and Gridlayout inside scrollview for this layout
        ScrollView scrollView = new ScrollView(this);
        GridLayout gridLayout = new GridLayout(this);
        //setting row and column count for the grid layout
        gridLayout.setColumnCount(1);
        gridLayout.setRowCount(9);
        ButtonHandler bh = new ButtonHandler();
        //setting multiple textviews from textView to textView8
        //in order to view the final result inside the GridLayout
        textView = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(0, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus =
                new GridLayout.LayoutParams(rowSpec, columnSpec);
        lpStatus.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView.setLayoutParams(lpStatus);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText("% for Course "+courseSelected+"are:");
        gridLayout.addView(textView);

        textView1 = new TextView(this);
        GridLayout.Spec rowSpec1 = GridLayout.spec(1, 1);
        GridLayout.Spec columnSpec1 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus1 =
                new GridLayout.LayoutParams(rowSpec1, columnSpec1);
        lpStatus1.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus1.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView1.setLayoutParams(lpStatus1);
        textView1.setGravity(Gravity.CENTER);
        textView1.setText("");
        gridLayout.addView(textView1);

        textView2 = new TextView(this);
        GridLayout.Spec rowSpec2 = GridLayout.spec(2, 1);
        GridLayout.Spec columnSpec2 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus2 =
                new GridLayout.LayoutParams(rowSpec2, columnSpec2);
        lpStatus2.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus2.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView2.setLayoutParams(lpStatus2);
        textView2.setGravity(Gravity.CENTER);
        textView2.setTypeface(null, Typeface.BOLD);
        textView2.setText("Exam and Quiz percentage is "+df.format(finalQuizExamPercentage)+"%"+" out of "+examQuizPercent+"%");
        gridLayout.addView(textView2);

        textView3 = new TextView(this);
        GridLayout.Spec rowSpec3 = GridLayout.spec(3, 1);
        GridLayout.Spec columnSpec3 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus3 =
                new GridLayout.LayoutParams(rowSpec3, columnSpec3);
        lpStatus3.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus3.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView3.setLayoutParams(lpStatus3);
        textView3.setGravity(Gravity.CENTER);
        textView3.setText("");
        gridLayout.addView(textView3);

        textView4 = new TextView(this);
        GridLayout.Spec rowSpec4 = GridLayout.spec(4, 1);
        GridLayout.Spec columnSpec4 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus4 =
                new GridLayout.LayoutParams(rowSpec4, columnSpec4);
        lpStatus4.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus4.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView4.setLayoutParams(lpStatus4);
        textView4.setGravity(Gravity.CENTER);
        textView4.setTypeface(null, Typeface.BOLD);
        textView4.setText("Assignments percentage is "+df.format(finalAssignmentPercentage)+"%"+" out of "+assignPercent+"%");
        gridLayout.addView(textView4);

        textView5 = new TextView(this);
        GridLayout.Spec rowSpec5 = GridLayout.spec(5, 1);
        GridLayout.Spec columnSpec5 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus5 =
                new GridLayout.LayoutParams(rowSpec5, columnSpec5);
        lpStatus5.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus5.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView5.setLayoutParams(lpStatus5);
        textView5.setGravity(Gravity.CENTER);
        textView5.setText("");
        gridLayout.addView(textView5);

        textView6 = new TextView(this);
        GridLayout.Spec rowSpec6 = GridLayout.spec(6, 1);
        GridLayout.Spec columnSpec6 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus6 =
                new GridLayout.LayoutParams(rowSpec6, columnSpec6);
        lpStatus3.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus3.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView6.setLayoutParams(lpStatus6);
        textView6.setGravity(Gravity.CENTER);
        textView6.setTypeface(null, Typeface.BOLD);
        textView6.setText("Total percentage is "+df.format(totalPercentage)+"%"+" out of 100%");
        gridLayout.addView(textView6);

        textView7 = new TextView(this);
        GridLayout.Spec rowSpec7 = GridLayout.spec(7, 1);
        GridLayout.Spec columnSpec7 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus7 =
                new GridLayout.LayoutParams(rowSpec7, columnSpec7);
        lpStatus7.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus7.width = GridLayout.LayoutParams.WRAP_CONTENT;
        textView7.setLayoutParams(lpStatus7);
        textView7.setGravity(Gravity.CENTER);
        textView7.setText("");
        gridLayout.addView(textView7);

        //setting the home button when you click on it you will be redirected to MainActivity page
        button = new Button(this);
        GridLayout.Spec rowSpec8 = GridLayout.spec(8, 1);
        GridLayout.Spec columnSpec8 = GridLayout.spec(0, 1);
        GridLayout.LayoutParams lpStatus8 =
                new GridLayout.LayoutParams(rowSpec8, columnSpec8);
        lpStatus8.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus8.width = GridLayout.LayoutParams.WRAP_CONTENT;
        lpStatus8.rightMargin = 5;
        lpStatus8.topMargin = 5;
        lpStatus8.setGravity(Gravity.CENTER);
        button.setLayoutParams(lpStatus8);
        button.setTextSize((int) (w * .0255));
        button.setText("HOME");
        button.setTextColor(getResources().getColor(R.color.white));
        button.setOnClickListener(bh);
        button.setBackgroundColor(getResources().getColor(R.color.brown));
        gridLayout.addView(button);
        //setting the scrollview as the view by adding Gridlayout to it
        scrollView.addView(gridLayout);
        setContentView(scrollView);

    }

    /***********************************************************************
     *                                                                     *
     *   Class Name : ButtonHandler                                        *
     *                                                                     *
     *   Purpose  : The purpose of this class is it implements the abstract*
     *              method in the OnClickListener interface by overriding  *
     *              it and helps in navigating to MainActivity page when   *
     *              the user clicks on Home button in Result page          *
     *                                                                     *
     ***********************************************************************/
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            Intent myIntent3 = new Intent(Result.this, MainActivity.class);
            Result.this.startActivity(myIntent3);
        }
    }
}
