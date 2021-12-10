/***********************************************************************
 *                                                                     *
 *   Class Name : ExamActivity.java                                    *
 *                                                                     *
 *   Purpose  : The purpose of this class is it allows user to enter   *
 *              all the marks for Exams and calculates the total marks *
 *              gained by the user for all exams and calculates the    *
 *              overall percentage of examAndQuizzes depending on the  *
 *              weightage of the class chosen.                         *
 *                                                                     *
 ***********************************************************************/

package edu.niu.z1862674.graduateproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExamActivity extends AppCompatActivity {

    //declaring instance variables
    public static final String EA = "ExamActivity";
    public static final int num_exams = 3;
    private EditText marks[] = new EditText[num_exams];
    private int examid[] = {R.id.edittext1,R.id.edittext2,R.id.edittext3};
    private String[] input = new String[num_exams];
    private float[] input1 = new float[num_exams];
    private static float totalMarks;
    private static float totalMarks2;
    private float examQuizpercentage;
    private static final int quizExamTotal = 400;
    private static float finalQuizMarks;
    private static float assignmentPercentage;
    private int allocatedExamQuizPercent = 70;
    private int courseSelected;

    /************************************************************************
     *                                                                      *
     *   onCreate() Method helps in starting the activity.Whenever activity *
     *   gets forcefully terminated, object of Bundle Class will save the   *
     *   state of an activity                                               *
     *                                                                      *
     ************************************************************************/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        //getting the values from the previous activity
        finalQuizMarks = getIntent().getFloatExtra("totalMarks1",0);
        assignmentPercentage = getIntent().getFloatExtra("finalAssignmentPercentage",0);
        courseSelected = getIntent().getIntExtra("courseNum",0);

        //setting the weightage for calculating the percentage depending on the course selected
        if(courseSelected == 241) {
            allocatedExamQuizPercent = 60;
        }

    }

    /************************************************************************
     *                                                                      *
     *   modifyData() Method helps in getting the values entered by the     *
     *   user for all exams and calculate the total exam marks and          *
     *   percentage of the exams and quizzes combined when the user         *
     *   clicks on Next button.                                             *
     *                                                                      *
     ************************************************************************/
    public void modifyData(View v)
    {
        totalMarks = 0;
        totalMarks2 = 0;
        //getting the marks entered by the user
        for(int i = 0; i < num_exams ; i++)
        {
            marks[i] = findViewById(examid[i]);
            input[i] = marks[i].getText().toString();
            //safely converting entered marks into float values
            try{
                input1[i] = Float.parseFloat(input[i]);
                totalMarks += input1[i];
            }
            catch ( NumberFormatException nfe ) {

            }

            //if any field is left empty then an error message is displayed
            //and the user will not be allowed to next page
            if (TextUtils.isEmpty(input[i])) {
                marks[i].setError("Do not leave field blank.Please enter valid number which is >=0 or <=100 for Exam"+ (i + 1));
                Toast.makeText(ExamActivity.this, "Do not leave field blank.Please enter a number for Exam"+ (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }

            //if any field value is less than 0 or greater than 100 an error message is displayed
            //and the user will not be allowed to next page
            else if (input1[i] < 0 || input1[i] > 100) {
                Toast.makeText(ExamActivity.this, "Enter a valid number which is >=0 or <=100 for Exam"+ (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }

        }


        //totalMarks2 is the combination of quiz and exam marks
        totalMarks2 = totalMarks + finalQuizMarks;

        //percentage is calculated depending on the weightage under each course
        examQuizpercentage = (totalMarks2/quizExamTotal)*allocatedExamQuizPercent;
        Log.w(EA, "percentage = " + examQuizpercentage);

        //redirected to Result Activity page when there are no errors
        Intent myIntent2 = new Intent(ExamActivity.this, Result.class);
        //assignments percentage and examQuizpercentage gained by student and course number are send to the next activity page
        myIntent2.putExtra("examQuizpercentage",examQuizpercentage);
        myIntent2.putExtra("assignmentPercentage",assignmentPercentage);
        myIntent2.putExtra("courseSelected",courseSelected);
        this.startActivity(myIntent2);
    }

    /************************************************************************
     *                                                                      *
     *   goBack() Method helps in redirecting to QuizActivity page when     *
     *   the user clicks on BACK button in ExamActivity page.               *
     *                                                                      *
     ************************************************************************/
    public void goBack(View v)
    {
        this.finish();
    }

}
