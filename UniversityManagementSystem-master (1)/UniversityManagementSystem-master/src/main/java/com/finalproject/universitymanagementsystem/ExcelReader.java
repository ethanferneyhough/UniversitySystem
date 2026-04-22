package com.finalproject.universitymanagementsystem;

import com.finalproject.identification.Faculty;
import com.finalproject.identification.Student;
import com.finalproject.identification.User;
import com.finalproject.primary.Course;
import com.finalproject.primary.Event;
import com.finalproject.primary.LectureTime;
import com.finalproject.primary.Subject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelReader {

    public static List<User> loadUsersFromExcel(String filePath) {
        List<User> users = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath)); //open Excel file
             Workbook workbook = new XSSFWorkbook(file)) {  //workbook object for xlsx manipulation

            Sheet studentsSheet = workbook.getSheet("Students "); //read Students sheet
            for (Row row : studentsSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0)!=null && (row.getCell(0).getCellType() != CellType.BLANK)) { //parse data if cells are not null or blank
                    String id = row.getCell(0).getStringCellValue();  //student ID
                    String password = row.getCell(11).getStringCellValue();  //password
                    users.add(new User(id, password, 1));  //create new user
                    System.out.println("Student user added.");
                }else{
                    break; //break once null or blank cell is reached
                }
            }

            Sheet facultiesSheet = workbook.getSheet("Faculties "); //read Faculties sheet
            for (Row row : facultiesSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0)!=null && (row.getCell(0).getCellType() != CellType.BLANK)){ //parse data if cells are not null or blank
                    String id = row.getCell(0).getStringCellValue();  //faculty ID
                    String password = row.getCell(7).getStringCellValue();  //password
                    users.add(new User(id, password, 2));  //create new user
                    System.out.println("Faculty user added.");
                }else{
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break; //break once null or blank cell is reached
                }
            }
        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return users;
    }
    public static List<Student> loadStudentsFromExcel(String filePath) {
        List<Student> students = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet studentsSheet = workbook.getSheet("Students ");//read Students sheet
            for (Row row : studentsSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0)!=null && (row.getCell(0).getCellType() != CellType.BLANK)) {
                    String id = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String address = row.getCell(2).getStringCellValue();
                    String telephone = row.getCell(3).getStringCellValue();
                    String email = row.getCell(4).getStringCellValue();
                    String academicLevel = row.getCell(5).getStringCellValue();
                    String currentSem = row.getCell(6).getStringCellValue();
                    String profilePhoto = row.getCell(7).getStringCellValue();
                    if(profilePhoto.contains("default")){
                        profilePhoto = StartApp.masterDefaultImage; //use masterDefaultImage if cell text is "default"
                    }
                    if(!profilePhoto.contains(".jpg") && !profilePhoto.contains(".png")){
                        profilePhoto = profilePhoto+ StartApp.masterFallbackImageFiletype;  //append masterFallbackImageFiletype if no filetype specified
                    }
                    String[] enrolledSubjects = row.getCell(8).getStringCellValue().split(",");    //split string into parts before and after the "/" character
                    ArrayList<String> enrolledSubjectList = new ArrayList<String>();
                    for(int i = 0; i < enrolledSubjects.length; i++){
                        enrolledSubjects[i] = enrolledSubjects[i].replaceAll("\\s", "");    //remove any problematic whitespaces
                        enrolledSubjectList.add(enrolledSubjects[i].toUpperCase());  //add formatted subject names to list
                    }
                    String thesisTitle = row.getCell(9).getStringCellValue();
                    double progress = row.getCell(10).getNumericCellValue();
                    students.add(new Student(id,name,address,telephone,email,academicLevel,currentSem,profilePhoto,enrolledSubjectList,thesisTitle,progress)); //create new Student
                    System.out.println("Student added. ");
                }else{
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return students;
    }
    public static List<Faculty> loadFacultyFromExcel(String filePath) {
        List<Faculty> facultyList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet facultiesSheet = workbook.getSheet("Faculties "); //read Faculties Sheet
            for (Row row : facultiesSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0)!=null && (row.getCell(0).getCellType() != CellType.BLANK)) {
                    String name = row.getCell(1).getStringCellValue();
                    String degree = row.getCell(2).getStringCellValue();
                    String researchInterest = row.getCell(3).getStringCellValue();
                    String email = row.getCell(4).getStringCellValue();
                    String officeLocation = row.getCell(5).getStringCellValue();
                    facultyList.add(new Faculty(name,degree,researchInterest,email,officeLocation)); //create new Faculty
                    System.out.println("Faculty added.");
                }else{
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return facultyList;
    }
    public static List<Subject> loadSubjectsFromExcel(String filePath) {
        List<Subject> subjectList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet subjectsSheet = workbook.getSheet("Subjects"); //read Subjects Sheet
            for (Row row : subjectsSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0)!=null && (row.getCell(0).getCellType() != CellType.BLANK)) {
                    String code = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    subjectList.add(new Subject(name,code)); //create new Subject
                    System.out.println("Subject added.");
                }else{
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return subjectList;
    }
    public static List<Course> loadCoursesFromExcel(String filePath) {
        List<Course> courseList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet coursesSheet = workbook.getSheet("Courses"); //read Subjects Sheet
            for (Row row : coursesSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0) != null && (row.getCell(0).getCellType() != CellType.BLANK)) {
                    double code = row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String subjectCode = row.getCell(2).getStringCellValue();
                    String sectionNumber = row.getCell(3).getStringCellValue();
                    double capacity = row.getCell(4).getNumericCellValue();
                    String lectureTimeString = row.getCell(5).getStringCellValue();
                    List<LectureTime> lectureTimes = parseLectureTime(lectureTimeString);   //parse LectureTime cell and return separate LectureTime objects for each day
                    LocalDateTime examDate;
                    //the block of code below converts a cell of type double to a human-readable date (thanks Excel)
                    if(row.getCell(6) != null && (row.getCell(6).getCellType() != CellType.BLANK)){ //if cell is not blank or null
                        double dateTime = row.getCell(6).getNumericCellValue();
                        int excelDays = (int)dateTime;  //remove decimals with a remainer of days
                        LocalDate baseDate = LocalDate.of(1900, 1, 1);  //excels starting reference date for double dates is this
                        LocalDate date = baseDate.plusDays(excelDays - 2);  //corrected date for leap year(s)
                        double excelTime = dateTime - excelDays;    //remove date with a remainder of time
                        long totalSeconds = (long) (excelTime * 24 * 60 * 60);  //convert remainder to seconds
                        LocalTime time = LocalTime.ofSecondOfDay(totalSeconds);
                        examDate = LocalDateTime.of(date, time);  //create LocalDateTime combining days (integer) and seconds (decimal) part of original double
                    }else{
                        System.out.println("Could not parse Excel exam date for course " + name + ". Setting date to now.");
                        examDate = LocalDateTime.now(); //if cell was blank or null, just set current datetime
                    }
                    String location = row.getCell(7).getStringCellValue();
                    String teacherName = row.getCell(8).getStringCellValue();
                    Course course = new Course(code, name, subjectCode, sectionNumber, capacity, examDate, location, teacherName);  //create new Course object with current values
                    for(LectureTime lectureTime : lectureTimes){    //add all parsed lecture times to current course
                        course.addLectureTime(lectureTime);
                    }
                    courseList.add(course); //add newly created course to list
                    System.out.println("Course added.");
                } else {
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return courseList;
    }
    public static List<Event> loadEventsFromExcel(String filePath) {
        List<Event> eventList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet eventsSheet = workbook.getSheet("Events "); //read Subjects Sheet
            for (Row row : eventsSheet) {
                if (row.getRowNum() == 0) continue; //skip header row
                if (row.getCell(0) != null && (row.getCell(0).getCellType() != CellType.BLANK)) {
                    String code = row.getCell(0).getStringCellValue();
                    if(Event.globalCode <= Integer.parseInt(code.replaceAll("EV",""))){ //parse out digits from event code and compare to current globalEventCode
                        Event.globalCode = Integer.parseInt(code.replaceAll("EV",""))+1;    //if current globalEventCode is less than code found in spreadsheet, refresh globalEventCode value
                    }
                    String name = row.getCell(1).getStringCellValue();
                    String description = row.getCell(2).getStringCellValue();
                    String location = row.getCell(3).getStringCellValue();
                    LocalDateTime eventTime;
                    //the block of code below converts a cell of type double to a human-readable date (thanks Excel)
                    if(row.getCell(4) != null && (row.getCell(4).getCellType() != CellType.BLANK)){ //if cell is not blank or null
                        double dateTime = row.getCell(4).getNumericCellValue();
                        int excelDays = (int)dateTime;  //remove decimals with a remainer of days
                        LocalDate baseDate = LocalDate.of(1900, 1, 1);  //excels starting reference date for double dates is this
                        LocalDate date = baseDate.plusDays(excelDays - 2);  //corrected date for leap year(s)
                        double excelTime = dateTime - excelDays;    //remove date with a remainder of time
                        long totalSeconds = (long) (excelTime * 24 * 60 * 60);  //convert remainder to seconds
                        LocalTime time = LocalTime.ofSecondOfDay(totalSeconds);
                        eventTime = LocalDateTime.of(date, time);  //create LocalDateTime combining days (integer) and seconds (decimal) part of original double
                    }else{
                        System.out.println("Could not parse Excel event date for event " + code + ". Setting date to now.");
                        eventTime = LocalDateTime.now(); //if cell was blank or null, just set current datetime
                    }
                    double capacity = row.getCell(5).getNumericCellValue();
                    String tempCost = row.getCell(6).getStringCellValue(); //fetch cost as string
                    double cost;
                    try{
                        cost = Double.parseDouble(tempCost.replaceAll("[^0-9]", "")); //parse out digits and cast as double
                    }catch (Exception e){
                        cost = 0;
                        System.out.println("Could not parse event cost out of Excel. Setting cost to 0.");
                    }
                    String headerImage = row.getCell(7).getStringCellValue();
                    if(!headerImage.contains(".jpg") && !headerImage.contains(".png")){
                        headerImage = headerImage+ StartApp.masterFallbackImageFiletype;
                    }
                    String studentList = row.getCell(8).getStringCellValue();
                    String[] individualStudents = studentList.split(",");    //split string into parts before and after the "," character (to parse individual student names)
                    ArrayList<String> students = new ArrayList<>();
                    for(int i = 0; i < individualStudents.length; i++){ //for each name in String array...
                        if(individualStudents[i].charAt(0) == ' '){ //if the name starts with a leading space, remove the space
                            individualStudents[i].replaceFirst("\\s", "");
                        }
                        students.add(individualStudents[i]);    //add student name to arrayList of student name
                    }
                    eventList.add(new Event(code, name, description, location, eventTime, capacity, cost, headerImage, students)); //create new Event
                    System.out.println("Event added.");
                } else {
                    System.out.println("Blank or null cell reached. Exiting current Excel import.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Caught error during Excel import: ");
            e.printStackTrace();
        }
        return eventList;
    }
    public static List<LectureTime> parseLectureTime(String input) {
        Pattern pattern = Pattern.compile("([A-Za-z/]+?\\s*?[A-Za-z/]+)\\s+(\\d+:?\\d*)\\s*-\\s*(\\d+:?\\d*)\\s*([AP]M)?"); //match pattern found in Excel cell with optional AM/PM period and hh or hh:mm format
        Matcher matcher = pattern.matcher(input);

        List<LectureTime> lectureTimes = new ArrayList<>();

        if (matcher.find()) {
            String daysStr = matcher.group(1); // day/day
            String startTimeStr = matcher.group(2); // hh or hh:mm
            String endTimeStr = matcher.group(3); // hh or hh:mm
            String period = matcher.group(4); // AM or PM
            LocalTime startTime;
            if(period==null){
                period = "AM";
            }

            List<LectureTime.WorkDays> daysOfWeek = parseDays(daysStr); //parse days

            //the below block parses the start time
            if(endTimeStr.startsWith("12") && !startTimeStr.startsWith("12")){  //this condition accounts for edge cases where the parses may get confused thinking that a class ending at 12pm would also start in the PM
                startTime = parseTime(startTimeStr, "AM");
            }else{
                startTime = parseTime(startTimeStr, period);
            }

            LocalTime endTime = parseTime(endTimeStr, period);  //parse end time

            for (LectureTime.WorkDays day : daysOfWeek) {   //create a separate LectureTime object for each day
                lectureTimes.add(new LectureTime(day, startTime.getHour(), startTime.getMinute(), endTime.getHour(), endTime.getMinute())); //add LectureTime to the list
            }
        } else {
            throw new IllegalArgumentException("Invalid input format");
        }
        return lectureTimes;    //return entire LectureTime list
    }

    private static LocalTime parseTime(String timeStr, String period) {
        if(timeStr.length()==1||timeStr.length()==4){   //if the time string is 1 or 4 characters we know it is missing a leading 0 that the LocalTime parser requires
            timeStr = "0"+timeStr;  //add leading 0
        }
        //the below conditions correct the format to hh:mm:ss based on what is passed (hh or hh:mm)
        if (timeStr.contains(":")) {
            timeStr = timeStr+":00";
        }else{
            timeStr = timeStr+":00:00";
        }
        LocalTime time = LocalTime.parse(timeStr);

        //the below condition converts our time to 24h format
        if (period.equals("PM") && time.getHour() < 12) {
            time = time.plusHours(12);
        }
        return time;
    }

    private static List<LectureTime.WorkDays> parseDays(String daysStr) {
        daysStr = daysStr.replaceAll("\\s", "");    //remove any problematic whitespaces
        String[] dayTokens = daysStr.split("/");    //split string into parts before and after the "/" character
        List<LectureTime.WorkDays> daysOfWeek = new ArrayList<>();

        for (String day : dayTokens) {  //return the WorkDays enum based on string passed
            switch (day.toLowerCase()) {
                case "mon":
                    daysOfWeek.add(LectureTime.WorkDays.Monday);
                    break;
                case "tue":
                    daysOfWeek.add(LectureTime.WorkDays.Tuesday);
                    break;
                case "wed":
                    daysOfWeek.add(LectureTime.WorkDays.Wednesday);
                    break;
                case "thu":
                    daysOfWeek.add(LectureTime.WorkDays.Thursday);
                    break;
                case "thur":    //corrects edge case where thur is written as thurs
                    daysOfWeek.add(LectureTime.WorkDays.Thursday);
                    break;
                case "fri":
                    daysOfWeek.add(LectureTime.WorkDays.Friday);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid day: " + day);
            }
        }
        return daysOfWeek;
    }
}
