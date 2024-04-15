package project.college.event.organizer.display.eventmanage.culturals;

public class CulturalsClass {
    private String studentName;
    private String department;
    private String yearSection;
    private String age;
    private String collegeName;
    private String contactNumber;
    private String nameOfFaculty;
    private String emailId;
    private String eventsRegistered;

    public CulturalsClass(String studentName, String department, String yearSection, String age, String collegeName, String contactNumber, String nameOfFaculty, String emailId, String eventsRegistered) {
        this.studentName = studentName;
        this.department = department;
        this.yearSection = yearSection;
        this.age = age;
        this.collegeName = collegeName;
        this.contactNumber = contactNumber;
        this.nameOfFaculty = nameOfFaculty;
        this.emailId = emailId;
        this.eventsRegistered = eventsRegistered;
    }

    // Getters
    public String getStudentName() {
        return studentName;
    }

    public String getDepartment() {
        return department;
    }

    public String getYearSection() {
        return yearSection;
    }

    public String getAge() {
        return age;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getNameOfFaculty() {
        return nameOfFaculty;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getEventsRegistered() {
        return eventsRegistered;
    }
}
