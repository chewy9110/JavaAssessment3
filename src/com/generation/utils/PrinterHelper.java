package com.generation.utils;

import com.generation.model.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PrinterHelper
{
    public static void showMainMenu()
    {
        System.out.println( "|-------------------------------|" );
        System.out.println( "| Welcome to StudentGen         |" );
        System.out.println( "|-------------------------------|" );
        System.out.println( "| Select 1 option:              |" );
        System.out.println( "| . 1 Register Student          |" );
        System.out.println( "| . 2 Find Student              |" );
        System.out.println( "| . 3 Grade Student             |" );
        System.out.println( "| . 4 Enroll Student to Course  |" );
        System.out.println( "| . 5 Show Students Summary     |" );
        System.out.println( "| . 6 Show Courses Summary      |" );
        System.out.println( "| . 7 Show Passed Courses       |" );
        System.out.println( "| . 8 Exit                      |" );
        System.out.println( "|-------------------------------|" );
    }

    public static Student createStudentMenu( Scanner scanner ) throws ParseException
    {
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| . 1 Register Student                |" );
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| Enter student name:                 |" );
        String name = scanner.next();
        System.out.println( "| Enter student ID:                   |" );
        String id = scanner.next();
        System.out.println( "| Enter student email:                |" );
        String email = scanner.next();

        System.out.println( "| Enter student birth date(MM/dd/yyyy)|" );
        DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );


        //TODO validate date format and catch exception to avoid crash
        Date birthDate =  new Date();
        String dateStr ;

        boolean isDobValid = false;
        do {
            try {
                dateStr = scanner.next();
                birthDate = formatter.parse(dateStr);
                isDobValid=isValidDate(dateStr);

            } catch (Exception e) {
                // System.out.println("other err"+e);
                isDobValid =  false;
            }
            finally {
                if (!isDobValid) {
                    System.out.println("Invalid date format. Make sure you type the date in the following format : mm/dd/yyyy");
                }
            }

        }
        while (isDobValid==false);


      //  Date birthDate = formatter.parse( scanner.next() );
        System.out.println( "|-------------------------------------|" );
        Student student = new Student( id, name, email, birthDate );
        System.out.println( "Student Successfully Registered! " );
        System.out.println( student );
        return student;
    }

   private static  boolean isValidDate(String inDate)  throws ParseException {

        String[] inDateParseElement = inDate.split("/");
        Integer mm;
        Integer dd;
        Integer yy;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

      //  System.out.println(String.format("currentYear %d", currentYear ));
      //  System.out.println(String.format("mm = %s", inDateParseElement[0]));
      //  System.out.println(String.format("dd = %s", inDateParseElement[1]));
      //  System.out.println(String.format("yyyy = %s", inDateParseElement[2]));
        boolean bolDate = true;

        // leap year not checked
        try {
            mm = Integer.parseInt(inDateParseElement[0]);
            dd = Integer.parseInt(inDateParseElement[1]);
            yy = Integer.parseInt(inDateParseElement[2]);

            if ((mm < 1) || (mm > 12)) {
                System.out.println("invalid Month: Month must be between 1 and 12");
                bolDate= false;
            }

            if ((dd < 1) || (dd > 31)) {
                System.out.println("invalid Day: Day must be between 1 and 31");
                bolDate= false;
            }

            if ( yy > currentYear) {
                bolDate = false;
                System.out.println(("invalid Year: Year is greater than current Year"));
            }
        }
        catch (Exception e) {
            System.out.println("(isValidDate) other err"+e);
            bolDate= false;
        }
        return bolDate;
    }

}
