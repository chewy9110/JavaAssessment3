package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student
    extends Person
    implements Evaluation {

    float PASS_MIN_GRADE = 3.0f;

    private ArrayList<Course> enrolledCourses  = new ArrayList<>();;


    public Student(String id, String name, String email, Date birthDate) {

        super(id, name, email, birthDate);
      //  this.enrolledCourses = new ArrayList<>();

    }

    public void enrollToCourse(Course course) {
        //TODO -- done
        if (findCourseById(course.getCode()) == null) {
            Course newCourse = new Course(course.getCode(), course.getName(), course.getCredits(), course.getModule());

            this.enrolledCourses.add(newCourse);
        }
    }

    @Override
    public List<Course> findPassedCourses() {
        //TODO - done

        ArrayList<Course> passedCourses = new ArrayList<>();

        for(Course c : this.enrolledCourses) {
           // System.out.println(c);
            //if(c.getCode().equals(courseId)) {
              if (c.getGrade() >= PASS_MIN_GRADE){
                  passedCourses.add(c);
              }
            }
            return passedCourses;

    }

    // new - for grade Student
    public String showPassedCourses() {
        List<Course> passedCourses = new ArrayList<>();
        String msg = "passed Courses:\n";

        passedCourses = this.findPassedCourses();
        if (passedCourses.isEmpty()) return "";

        for (Course c : passedCourses) {
            //  System.out.println(c);
            msg += " " + c + "\n";
        }

       // System.out.println(msg);
        return msg;
    }


    public Course findCourseById(String courseId) {
        //TODO  -- done

            for(Course c : this.enrolledCourses) {
            //    System.out.println(c);
                if(c.getCode().equals(courseId)) {
                    return c;
                }

        }

        //if fails
        return null;
    }


    @Override
    public List<Course> getEnrolledCourses() {
        //TODO -- done.

        return enrolledCourses;
        //return null;
    }

    @Override
    public String toString() {

        return "Student {" + super.toString() + "}" ;

    }

    // grade -
    public float getGrade(String courseId) {
        for(Course c : this.enrolledCourses) {
         //   System.out.println(c);
            if (c.getCode().equals(courseId)) {
                return c.getGrade();
            }
        }
        return -1;
    }

    public void setGrade(String courseId, float inputGrade) {
        for(Course c : this.enrolledCourses) {
         //   System.out.println(c);
            if (c.getCode().equals(courseId)) {
                 c.setGrade(inputGrade);
            }
        }

    }

}
