import java.util.InputMismatchException;
import java.util.Scanner;

class Date {
    private int date;
    private String month;
    private int year;
    private String dayOfWeek;
    public Date(int date,String month,int year){
        this.date=date;
        this.month=month;
        this.year=year;
        this.dayOfWeek=getDayOfWeek();
    }
    public void setDate(int date){
        this.date=date;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setMonth(String month){
        this.month=month;
    }
    public int getDate(){
        return date;
    }
    public int getYear(){
        return year;
    }
    public String getMonth(){
        return month;
    }
    public  String getDayOfWeek(){
        int yearCode = year%100;
        int leapYearCount = getLeapYearCount(year);
        int monthCode = getMonthCode(month);
        int centuryCode = getCenturyCode(year);
        int sum = (yearCode + leapYearCount + monthCode + date + centuryCode) % 7;
        String[] daysOfWeek = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday","saturday"};
       return daysOfWeek[sum];
    }
    public String getDay(){
        return this.dayOfWeek;
    }
    public  int  getCenturyCode(int year){
     return (6-((year/100)%4)*2)%7;
    }
    public  int getLeapYearCount(int year){
        return (year%100)/4;
    }
    public boolean isLeapYear(int year){
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
    public int getMonthCode(String month) {
        switch (month) {
            case "jan": return isLeapYear(year) ? 6: 0;
            case "feb": return isLeapYear(year) ? 2 : 3; 
            case "mar": return 3;
            case "apr": return 6;
            case "may": return 1;
            case "jun": return 4;
            case "jul": return 6;
            case "aug": return 2;
            case "sep": return 5;
            case "oct": return 0;
            case "nov": return 3;
            case "dec": return 5;
            default: throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
    public int getMonthDuration(String month){
        switch(month){
            case "jan": return 31;
            case "feb": return isLeapYear(year) ? 29 : 28; 
            case "mar": return 31;
            case "apr": return 30;
            case "may": return 31;
            case "jun": return 30;
            case "jul": return 31;
            case "aug": return 31;
            case "sep": return 30;
            case "oct": return 31;
            case "nov": return 30;
            case "dec": return 31;
            default: throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
    public void printMonth(String month,int year){
        setDate(1);
        setMonth(month);
        setYear(year);
        String startDate =getDayOfWeek();
        String days[]={"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
        int count=0;
        int num=1;
 
         System.out.printf("""
                          ***************************
                                   %s %d            
                          ***************************
                          """,
                 month, year
         );
         for (int i = 0; i < days.length; i++) {
            if(days[i].equals(startDate)){
            count=i;
            }
            System.out.printf("%3s ",days[i].substring(0,3));
        }
        System.out.println();
        for (int i = 1; i <=6; i++) {
           
           for (int j = 1; j <= 7; j++) {
             if(i==1 && j<=count){
                 System.out.print("    ");
             }else if (num<=getMonthDuration(month)) {
                 System.out.printf("%3d ", num++);
             }
           }
           System.out.println();
            
        }

    }
}
public class Calendar{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int date ;
        String month;
        int year ;
        try{
            System.out.print("Enter the Date : ");
            date = input.nextInt();
            input.nextLine();
            System.out.print("Enter the Month : ");
            month = input.nextLine().trim().toLowerCase().substring(0,3);
            System.out.print("Enter the year : ");
            year = input.nextInt();
            System.out.println();
            input.close();

            Date d = new Date(date, month, year);
            String day = d.getDay(); 
            System.out.printf(" %d  %s  %d  is : %s \n\n",date ,month , year , day);
            d.printMonth(month, year);
        }catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid data " + e);
        }
       
    }
}