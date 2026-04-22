package com.finalproject.primary;

//separate LectureTime class to allow for
public class LectureTime {
    public enum WorkDays {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }

    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private LectureTime.WorkDays dayOfWeek;
    private int id;
    private static int globalId = 1; //unique id for LectureTimes class (used for deleting lecture times by id reference)

    public LectureTime(){
        this.startHour = 0;
        this.startMinute = 0;
        this.endHour = 0;
        this.endMinute = 0;
        this.dayOfWeek = WorkDays.Monday;
        this.id = globalId;
        globalId++; //increment unique LectureTimes class id
    }
    public LectureTime(WorkDays dayOfWeek, int startHour, int startMinute, int endHour, int endMinute) {
        //check if times are valid
        this.startHour = (startHour >= 0 && startHour <= 23) ? startHour : 0;
        this.startMinute = (startMinute >= 0 && startMinute <= 59) ? startMinute : 0;
        this.endMinute = (endMinute >= 0 && endMinute <= 59) ? endMinute : 0;
        if((endHour > startHour && (endHour >=0 && endHour <= 23)) || (endHour == startHour && (endHour >= 0 && endHour <= 23) && startMinute < endMinute)){
            //if overlap is not detected on start and end times set desired end time
            this.endHour = endHour;
        }else{
            //if overlap is detected on start and end times reset times and print error message
            this.startHour = 0;
            this.endHour = 0;
            this.startMinute = 0;
            this.endMinute = 0;
            System.out.println("Could not resolve times specified.");
        }
        this.dayOfWeek = dayOfWeek;
        this.id = globalId;
        globalId++;
    }

    public void setStartHour(int hour){
        this.startHour = hour;
    }
    public int getStartHour(){
        return this.startHour;
    }
    public void setStartMinute(int minute){
        this.startMinute = minute;
    }
    public int getStartMinute(){
        return this.startMinute;
    }
    public void setEndHour(int hour){
        this.endHour = hour;
    }
    public int getEndHour(){
        return this.endHour;
    }
    public void setEndMinute(int minute){
        this.endMinute = minute;
    }
    public int getEndMinute(){
        return this.endMinute;
    }
    public void setDayOfWeek(WorkDays dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }
    public WorkDays getDayOfWeek(){
        return this.dayOfWeek;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public String toString(){
        return (dayOfWeek +" "+ startHour +":"+ startMinute +"-"+endHour +":"+ endMinute);
    }
}
