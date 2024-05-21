package adts;

 /**
  * Represents a course in a course scheduling system.
  */
public class Course {
  private String name;
  private String teacher;
  private String campus;
  private String building;
  private String classroom;
  
  Course(String name, String teacher, String campus, String building, String classroom) {
    this.name = name;
    this.teacher = teacher;
    this.campus = campus;
    this.building = building;
    this.classroom = classroom;
  }
  
  Course() {}
  
  public String getName() {
    return name;
  }
  
  public String getTeacher() {
    return teacher;
  }
  
  public String getCampus() {
    return campus;
  }
  
  public String getBuilding() {
    return building;
  }
  
  public String getClassroom() {
    return classroom;
  }
  
  public String getLocation() {
    return campus + '\n' + building + '\n' + classroom;
  }
}
