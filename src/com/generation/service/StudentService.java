package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent(Student student) {
        //TODO -- done
        students.put(student.getId(), student);

    }

    public Student findStudent(String studentId) {
        //TODO -- done
        if (students.containsKey(studentId)) {
            return (students.get(studentId));
        } else
            return null;

    }

    public boolean showSummary() {

        //TODO -- done

        List<Course> enrolledCourses = new ArrayList<>();
        String msg = "Students registered to StudentGen 2 :\n";
        msg += "-----------------------------------------\n";

        if (students == null) return false;

        for (Student s : students.values()) {
            msg += s + "\n";
            msg += "Enrolled Courses:\n";

            enrolledCourses = s.getEnrolledCourses();

            // msg += enrolledCourses;
            for (Course c : enrolledCourses) {
                //  System.out.println(c);
                //msg += " " + c + "\n";
                msg +=  c + "\n";
            }
            msg += "-----------------------------------------\n";
        }

        System.out.println(msg);
        return true;
    }

    public void enrollToCourse(String studentId, Course course) {
        //TODO -- done

        Student student = students.get(studentId);
        // System.out.println(student);
        // System.out.println(course);
        student.enrollToCourse(course);

    }

    // new - for grade Student
    public boolean showEnrolledCourseSummary(Student student) {
        List<Course> enrolledCourses = new ArrayList<>();
        String msg = "Enrolled Courses:\n";

        if (student == null) {
            return false;
        }

         enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) return false;

        for (Course c : enrolledCourses) {
            //  System.out.println(c);
            msg += " " + c + "\n";
        }

        System.out.println(msg);
        return true;
    }



}

