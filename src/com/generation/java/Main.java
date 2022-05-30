
/*
     JFSD Assessment 04 - Java
     Author : Chew KB
     Date   : 30/05/2022

     Known problems:
     1. leap year not handled.
     2. updateCourseGrade :  parseFloat exception error handling not handled.
     3. enrollCourse : message is incorrect if dup course is enrolled
*/

package com.generation.java;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
            throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses( studentService, scanner );
                    break;
            }
            System.out.println("Press \"ENTER\" to continue...");
          // temp workaround (a quick workaround to make screen pause)
            scanner.nextLine(); // absorb \r and \n
            scanner.nextLine();
        }
        while ( option != 8 );
    }

    private static void enrollCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        if (!studentService.showSummary())
        {
            System.out.println("No Student Yet");
        }
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {

        Student student = getStudentInformation( studentService, scanner );
       // System.out.println( "Enrolled course:" );

        //TODO
        if (student == null)  return  ;

        if (!studentService.showEnrolledCourseSummary(student)) {
           System.out.println("No Enrolled course");
        }
        else {
            updateCourseGrade(student, scanner );
        }


    }

    private static Student getStudentInformation( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Student not found" );
        }
        return student;
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        Student student = getStudentInformation( studentService, scanner );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );

        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner ) throws ParseException {
       Student student = PrinterHelper.createStudentMenu( scanner );

       studentService.subscribeStudent( student );

    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Student not found" );
        }
        else
        {
            if (student.findPassedCourses().size() == 0)
            {
                System.out.println( "No passed courses available" );
            }
           else
            {
               // System.out.println(student.findPassedCourses());
                System.out.println(student.showPassedCourses());
            }
        }
    }


// new -- for grade
private static void updateCourseGrade( Student student, Scanner scanner )
{
    System.out.println( "Insert Course Id to be graded: " );
    String courseId = scanner.next();
    Course course = student.findCourseById( courseId );
    if ( course == null )
    {
        System.out.println( "course not found" );
    }
    else {
        System.out.println( String.format("Insert Course grade for: %s ", course.getName()) );

            // assume integer value, no error checking
            float inputGrade = Float.parseFloat(scanner.next());

            if (!course.isValidGrade(inputGrade) ) {
                System.out.println(String.format("Course grade (%2.2f)  is out of range (min: %2.2f, max: %2.2f) ", inputGrade, course.getMinGrade(), course.getMaxGrade()));
            }
            else {

                float prevGrade = student.getGrade(courseId);
                student.setGrade(courseId, inputGrade);
                float newGrade = student.getGrade(courseId);

                System.out.println(String.format("Course grade for %s changed from %2.2f to %2.2f", course.getName(), prevGrade, newGrade));
            }

    }



}

}
