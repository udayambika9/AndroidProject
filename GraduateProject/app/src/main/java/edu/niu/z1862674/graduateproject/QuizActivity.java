/***********************************************************************
 *                                                                     *
 *   Class Name : QuizActivity.java                                    *
 *                                                                     *
 *   Purpose  : The purpose of this class is it allows user to enter   *
 *              all the marks for quizzes and calculates the total     *
 *              marks gained by the user for all quizzes by removing   *
 *              the least two quiz scores.                             *
 *                                                                     *
 ***********************************************************************/

package edu.niu.z1862674.graduateproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    //declaring instance variables

    public static final int num_quizzes = 12;
    private EditText marks[] = new EditText[num_quizzes];
    private int quizid[] = {R.id.edittext1,R.id.edittext2,R.id.edittext3,R.id.edittext4,R.id.edittext5,R.id.edittext6,
            R.id.edittext7,R.id.edittext8,R.id.edittext9,R.id.edittext10,R.id.edittext11,R.id.edittext12};
    private String[] input = new String[num_quizzes];
    private float[] input1 = new float[num_quizzes];
    private static float totalMarks;
    private static float totalMarks1;
    private static float firstleastNumber,secondLeastNumber;
    private static float finalAssignmentPercentage;
    private int courseNum;


    /************************************************************************
     *                                                                      *
     *   onCreate() Method helps in starting the activity.Whenever activity *
     *   gets forcefully terminated, object of Bundle Class will save the   *
     *   state of an activity                                               *
     *                                                                      *
     ************************************************************************/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //getting the values from the previous activity
        finalAssignmentPercentage = getIntent().getFloatExtra("percentage",0);
        courseNum = getIntent().getIntExtra("courseNumber",0);


    }

    /************************************************************************
     *                                                                      *
     *   findleast2number() Method helps in getting the lowest 2 values     *
     *   entered by the user for quiz marks                                 *
     *                                                                      *
     ************************************************************************/
    public static void findleast2number(float input1[]){


        firstleastNumber = secondLeastNumber = Integer.MAX_VALUE;
        //iterating through the array and finding the first and second least numbers
        for (int i = 0; i < input1.length ; i ++)
        {
            if (input1[i] <= firstleastNumber){
                secondLeastNumber = firstleastNumber;
                firstleastNumber = input1[i];
            }
            else if (input1[i] < secondLeastNumber && input1[i] != firstleastNumber){
                secondLeastNumber = input1[i];
            }

        }

    }

    /************************************************************************
     *                                                                      *
     *   modifyData() Method helps in getting the values entered by the     *
     *   user for all quizzes and calculate the total quiz marks by removing*
     *   the lease two quiz scores when the user clicks on Next button      *
     *                                                                      *
     ************************************************************************/
    public void modifyData(View v) {

        totalMarks = 0;

        //getting the marks for quizzes entered by the user
        for(int i = 0; i < num_quizzes; i++)
        {
            marks[i] = findViewById(quizid[i]);
            input[i] = marks[i].getText().toString();
            ////safely converting entered marks into float values
            try
            {
                input1[i] = Float.parseFloat(input[i]);
                totalMarks += input1[i];

            }
            catch ( NumberFormatException nfe ) {

            }
            //if any field is left empty then an error message is displayed
            //and the user will not be allowed to next page
            if (TextUtils.isEmpty(input[i])) {
                marks[i].setError("Do not leave field blank.Please enter valid number which is >=0 or <=10 for Quiz"+ (i + 1));
                Toast.makeText(QuizActivity.this, "Do not leave field blank.Please enter a number which is >=0 or <=10 for Quiz"+ (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }
            //if any field value is less than 0 or greater than 100 an error message is displayed
            //and the user will not be allowed to next page
            else if (input1[i] < 0 || input1[i] > 10) {
                Toast.makeText(QuizActivity.this, "Enter a valid number which is >=0 or <=10 for Quiz"+ (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //calling findleast2number() by passing the array to it to find the least 2 scores
        findleast2number(input1);
        //getting the final total marks by removing the first and second least numbers entered by the user
        totalMarks1 = totalMarks - firstleastNumber - secondLeastNumber;


        //redirected to ExamActivity page when there are no errors
        Intent myIntent1 = new Intent(QuizActivity.this, ExamActivity.class);
        //assignments percentage and total quiz marks  gained by student and course number are send to the next activity page
        myIntent1.putExtra("totalMarks1",totalMarks1);
        myIntent1.putExtra("finalAssignmentPercentage",finalAssignmentPercentage);
        myIntent1.putExtra("courseNum",courseNum);
        this.startActivity(myIntent1);

    }

    /************************************************************************
     *                                                                      *
     *   goBack() Method helps in redirecting to AssignmentsActivity page   *
     *   when the user clicks on BACK button in QuizActivity page.          *
     *                                                                      *
     ************************************************************************/

    public void goBack(View v)
    {
        this.finish();
    }

}