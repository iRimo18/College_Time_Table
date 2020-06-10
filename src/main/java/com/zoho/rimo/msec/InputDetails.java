package com.zoho.rimo.msec;

import com.zoho.rimo.msec.resources.Course;
import com.zoho.rimo.msec.resources.Department;
import com.zoho.rimo.msec.resources.Instructor;
import com.zoho.rimo.msec.resources.MeetingTime;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * InputDetails.java- This is the data class, which feds the resource classes
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class InputDetails
{
    private File file;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingTimes;

    public InputDetails(File file)
    {
        this.file = file;
        initialize();
    }

    private void initialize()
    {
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("### Meeting_Time ###")) {
                    this.meetingTimes = new ArrayList<>();
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("### Instructor ###")) {
                        StringTokenizer st = new StringTokenizer(line, " ");
                        this.meetingTimes.add(new MeetingTime(st.nextToken(), st.nextToken()));
                    }
                }

                if (line.equalsIgnoreCase("### Instructor ###")) {
                    this.instructors = new ArrayList<>();
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("### Course ###")) {
                        StringTokenizer st = new StringTokenizer(line, " ");
                        this.instructors.add(new Instructor(st.nextToken(), st.nextToken()));
                    }
                }

                if (line.equalsIgnoreCase("### Course ###")) {
                    this.courses = new ArrayList<>();
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("### Department ###")) {
                        StringTokenizer st = new StringTokenizer(line, " ");
                        ArrayList<Instructor> instructorArrayList = new ArrayList<>();
                        String id = st.nextToken();
                        String name = st.nextToken();
                        instructorArrayList.add(this.instructors.get(Integer.parseInt(st.nextToken()) - 1));
                        this.courses.add(new Course(id, name, instructorArrayList));
                    }
                }

                if (line.equalsIgnoreCase("### Department ###")) {
                    this.depts = new ArrayList<>();
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("### EOF ###")) {
                        StringTokenizer st = new StringTokenizer(line, " ");
                        String name = st.nextToken();
                        ArrayList<Course> courseArrayList = new ArrayList<>();
                        while (st.hasMoreTokens()) {
                            courseArrayList.add(this.courses.get(Integer.parseInt(st.nextToken()) - 1));
                        }
                        this.depts.add(new Department(name, courseArrayList));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Instructor> getInstructors()
    {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors)
    {
        this.instructors = instructors;
    }

    public ArrayList<Course> getCourses()
    {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses)
    {
        this.courses = courses;
    }

    public ArrayList<Department> getDepts()
    {
        return depts;
    }

    public void setDepts(ArrayList<Department> depts)
    {
        this.depts = depts;
    }

    public ArrayList<MeetingTime> getMeetingTimes()
    {
        return meetingTimes;
    }

    public void setMeetingTimes(ArrayList<MeetingTime> meetingTimes)
    {
        this.meetingTimes = meetingTimes;
    }

    public int getNumberOfClasses()
    {
        // 3 depts and 6 subjects each
        return 3 * 6;
    }
}
