/***********************************************************************
 *                                                                     *
 *   Class Name : AssignmentsActivity.java                             *
 *                                                                     *
 *   Purpose  : The purpose of this class is it allows user to enter   *
 *              all the marks for Assignments depending on the course  *
 *              you have selected on the main activity page.Total      *
 *              assignment marks and percentage is calculated as per   *
 *              the course selected and sent to the next activity      *
 *              page through putExtra method.                          *
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

public class AssignmentsActivity extends AppCompatActivity {

    //declaring instance variables
    public static final String AA = "AssignmentActivity";
    public int num_assignments = 10;
    private EditText marks[] = new EditText[num_assignments];
    private int assignmentid[] = {R.id.edittext1,R.id.edittext2,R.id.edittext3,R.id.edittext4,R.id.edittext5,R.id.edittext6,
            R.id.edittext7,R.id.edittext8,R.id.edittext9,R.id.edittext10};
    private String[] input = new String[num_assignments];
    private float[] input1 = new float[num_assignments];
    private static float totalMarks;
    private float percentage;
    private int actual_assignTotal = 500;
    private int courseNumber;
    private int allocatedAssignPercentage = 30;

    /************************************************************************
     *                                                                      *
     *   onCreate() Method helps in starting the activity.Whenever activity *
     *   gets forcefully terminated, object of Bundle Class will save the   *
     *   state of an activity                                               *
     *                                                                      *
     ************************************************************************/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        courseNumber = getIntent().getIntExtra("CourseName",0);

        //as per the requirement for CSCI 241 course there are only 8 assignments
        //so disabling the view for 2 assignments
        //and also setting the count of assignments in total for 241 course and percentage weightage
        if(courseNumber == 241){
            num_assignments = 8;
            actual_assignTotal = 400;
            allocatedAssignPercentage = 40;
            findViewById(R.id.edittext9).setVisibility(View.INVISIBLE);
            findViewById(R.id.edittext10).setVisibility(View.INVISIBLE);
            findViewById(R.id.assignment9).setVisibility(View.INVISIBLE);
            findViewById(R.id.assignment10).setVisibility(View.INVISIBLE);
        }
    }

    /************************************************************************
     *                                                                      *
     *   modifyData() Method helps in getting the values entered by the     *
     *   user for all assignments and calculate the total marks and         *
     *   percentage of the assignments in total when the user clicks on     *
     *   Next button.                                                       *
     *                                                                      *
     ************************************************************************/
    public void modifyData(View v) {
        totalMarks = 0;
        //getting the marks entered by the user
        for (int i = 0; i < num_assignments; i++) {
            marks[i] = findViewById(assignmentid[i]);
            input[i] = marks[i].getText().toString();
            //safely converting entered marks into float values
            try {
                input1[i] = Float.parseFloat(input[i]);
                totalMarks += input1[i];
            } catch (NumberFormatException nfe) {

            }
            //if any field is left empty then an error message is displayed
            //and the user will not be allowed to next page
            if (TextUtils.isEmpty(input[i])) {
                marks[i].setError("Do not leave field blank.Please enter valid number which is >=0 or <=50 for Assignment"+ (i + 1));
                Toast.makeText(AssignmentsActivity.this, "Do not leave field blank.Please enter valid number which is >=0 or <=50 for Assignment"+ (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }
            //if any field value is less than 0 or greater than 100 an error message is displayed
            //and the user will not be allowed to next page
            else if (input1[i] < 0 || input1[i] > 50) {
                Toast.makeText(AssignmentsActivity.this, "Enter a valid number which is >=0 or <=50 for Assignment" + (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }

        }

        //percentage is calculated depending on the weightage under each course
        percentage = (totalMarks/actual_assignTotal)*allocatedAssignPercentage;
        Log.w(AA, "percentage = " + percentage);

        //redirected to QuizActivity page when there are no errors
        Intent myIntent1 = new Intent(AssignmentsActivity.this, QuizActivity.class);
        //assignments percentage gained by student and course number are send to the next activity page
        myIntent1.putExtra("percentage",percentage);
        myIntent1.putExtra("courseNumber",courseNumber);
        this.startActivity(myIntent1);

    }

    /************************************************************************
     *                                                                      *
     *   goBack() Method helps in redirecting to MainActivity page when     *
     *   the user clicks on BACK button in AssignmentsActivity page.        *
     *                                                                      *
     ************************************************************************/
    public void goBack(View v)
    {
        this.finish();
    }

}
