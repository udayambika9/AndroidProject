/***********************************************************************
 *   CSCI 522              Graduate Project                   Fall2021 *
 *                                                                     *
 *   Class Name : MainActivity.java                                    *
 *                                                                     *
 *   Developer : Udayambika Rayapu                                     *
 *                                                                     *
 *   Due date :  3 December 2021                                       *
 *                                                                     *
 *   Purpose  : The purpose of this class is to develop an application *
 *              which helps in calculating the grade for the students  *
 *              when they enter the marks for Assignments,Quizzes,Exams*
 *              The main activity page helps in redirecting to the     *
 *              appropriate Assignment page when you click on the      *
 *              button in the main activity page.                      *
 ***********************************************************************/

package edu.niu.z1862674.graduateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /************************************************************************
     *                                                                      *
     *   onCreate() Method helps in starting the activity.Whenever activity *
     *   gets forcefully terminated, object of Bundle Class will save the   *
     *   state of an activity                                               *
     *                                                                      *
     ************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /************************************************************************
     *                                                                      *
     *   modifyData() Method helps in going to the next activity page       *
     *   AssignmentsActivity.java when you click on CSCI 240 button in main *
     *   activity page                                                      *
     *                                                                      *
     ************************************************************************/
    public void modifyData(View v)
    {
        Intent myIntent = new Intent(this, AssignmentsActivity.class);
        myIntent.putExtra("CourseName",240);
        this.startActivity(myIntent);
    }

    /************************************************************************
     *                                                                      *
     *   modifyData1() Method helps in going to the next activity page       *
     *   AssignmentsActivity.java when you click on CSCI 241 button in main *
     *   activity page                                                      *
     *                                                                      *
     ************************************************************************/
    public void modifyData1(View v)
    {
        Intent myIntent = new Intent(this, AssignmentsActivity.class);
        myIntent.putExtra("CourseName",241);
        this.startActivity(myIntent);
    }
}