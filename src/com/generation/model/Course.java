package com.generation.model;

public class Course
{
    private final String code;
    private final String name;
    private final int credits;
    private final Module module;

    // new -- grade
    private float grade = 0.0f;
    private final float minGrade = 1.0f;
    private final float maxGrade = 6.0f;

    public float getMinGrade() {
        return minGrade;
    }

    public float getMaxGrade() {
        return maxGrade;
    }

    public Course(String code, String name, int credits, Module module )
    {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.module = module;
    }



    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public int getCredits()
    {
        return credits;
    }

    public Module getModule()
    {
        return module;
    }

    // grade
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public boolean isValidGrade(float inputGrade) {
        if (inputGrade < minGrade || inputGrade > maxGrade)
            return false;

        return true;
    }
    //

    @Override
    public String toString()
    {
        return "Course{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", credits=" + credits + ", module="
            + module + '}' + String.format(" Grade : %2.2f", grade);
    }
}
