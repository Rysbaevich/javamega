package kg.itschool.crm;

import kg.itschool.crm.dao.*;
import kg.itschool.crm.dao.daoutil.DaoContext;
import kg.itschool.crm.dao.impl.CourseDaoImpl;
import kg.itschool.crm.dao.impl.CourseFormatDaoImpl;
import kg.itschool.crm.dao.impl.MentorDaoImpl;
import kg.itschool.crm.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        allMentors();
//        allCourseFormats();
//        allCourses();
//        allManagers();
        allGroups();

    }

    public static void allMentors() {
        Mentor mentor = new Mentor();
        MentorDao mentorDao = (MentorDao) DaoContext.autowired("MentorDao", "singleton");

     /*   Scanner scan = new Scanner(System.in);

        System.out.print("First name: ");
        mentor.setFirstName(scan.nextLine());

        System.out.print("Last name: ");
        mentor.setLastName(scan.nextLine());

        System.out.print("Email: ");
        mentor.setEmail(scan.nextLine());

        System.out.print("Phone number: ");
        mentor.setPhoneNumber(scan.nextLine());

        System.out.print("Date of birth: ");
        mentor.setDob(LocalDate.parse(scan.nextLine())); // yyyy-MM-dd

        System.out.print("Salary: ");
        mentor.setSalary(scan.nextDouble());

        System.out.println("Input: " + mentor);



        System.out.println("From database: " + mentorDao.save(mentor));*/

//        System.out.println(mentorDao.findById(1L));
        for (Mentor mentor1 : mentorDao.findAll()) {
            System.out.println(mentor1);
        }
    }
    public static void allManagers() {
        Manager manager = new Manager();
        ManagerDao managerDao = (ManagerDao) DaoContext.autowired("ManagerDao", "singleton");
        Scanner scan = new Scanner(System.in);

        System.out.print("First name: ");
        manager.setFirstName(scan.nextLine());

        System.out.print("Last name: ");
        manager.setLastName(scan.nextLine());

        System.out.print("Email: ");
        manager.setEmail(scan.nextLine());

        System.out.print("Phone number: ");
        manager.setPhoneNumber(scan.nextLine());

        System.out.print("Date of birth: ");
        manager.setDob(LocalDate.parse(scan.nextLine())); // yyyy-MM-dd

        System.out.print("Salary: ");
        manager.setSalary(scan.nextDouble());

        System.out.println("Input: " + manager);


        System.out.println("From database: " + managerDao.save(manager));

        System.out.println(managerDao.findById(5L));
//        System.out.println(managerDao.findAll());

    }
    public static void allCourses() {

        Course course = new Course();
        CourseFormatDaoImpl courseFormatDaoImpl = new CourseFormatDaoImpl();
        CourseDao courseDao = (CourseDao) DaoContext.autowired("CourseDao", "singleton");
/*
        Scanner scan = new Scanner(System.in);

        System.out.print("Name: ");
        course.setName(scan.nextLine());

        System.out.print("Price: ");
        course.setPrice(scan.nextDouble());
        scan.nextLine();

        System.out.print("Course format(id): ");
        CourseFormat courseFormat = courseFormatDaoImpl.findById(scan.nextLong());
        course.setCourseFormat(courseFormat);

        System.out.println("Input: " + course);
*/


//        System.out.println("From database: " + courseDao.save(course));

//        System.out.println(courseDao.findById(3L));
        for (Course course1 : courseDao.findAll()) {
            System.out.println(course1);
        }
    }
    public static void allCourseFormats() {
        CourseFormat courseFormat = new CourseFormat();
        CourseFormatDao courseFormatDao = (CourseFormatDao) DaoContext.autowired("CourseFormatDao", "singleton");

/*
        Scanner scan = new Scanner(System.in);

        System.out.print("Format: ");
        courseFormat.setFormat(scan.nextLine());

        System.out.print("Course duration in weeks: ");
        courseFormat.setCourseDurationWeeks(scan.nextInt());
        scan.nextLine();

        System.out.print("Lesson duration(hours and minutes): ");
        courseFormat.setLessonDuration(LocalTime.of(scan.nextInt(), scan.nextInt()));

        System.out.print("Lessons per week: ");
        courseFormat.setLessonPerWeek(scan.nextInt());
        scan.nextLine();

        System.out.print("is online: ");
        courseFormat.setOnline(Boolean.parseBoolean(scan.nextLine()));

        System.out.println("Input: " + courseFormat);


        System.out.println("From database: " + courseFormatDao.save(courseFormat));
*/

        for (CourseFormat courseFormat1 : courseFormatDao.findAll()) {
            System.out.println(courseFormat1);
        }

//        System.out.println(courseFormatDao.findById(1L));
    }
    public static void allGroups() {

        Group group = new Group();
        CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
        MentorDaoImpl mentorDaoImpl = new MentorDaoImpl();
        GroupDao groupDao = (GroupDao) DaoContext.autowired("GroupDao", "singleton");

        Scanner scan = new Scanner(System.in);

        System.out.print("Groups name: ");
        group.setName(scan.nextLine());

        System.out.print("Group time(hour 'click enter' then minute 'click enter'): ");
        group.setGroupTime(LocalTime.of(scan.nextInt(), scan.nextInt()));

        System.out.println();
        System.out.println("Courses:");
        System.out.println("1 - Java");
        System.out.println("2 - Flutter");
        System.out.println("3 - Python");
        System.out.println();
        System.out.println("Course(id): ");
        Course course = courseDaoImpl.findById(scan.nextLong());
        group.setCourse(course);

        System.out.println();
        System.out.println("Mentors: ");
        System.out.println("1 - Nodir");
        System.out.println("2 - Jyldyzbek");
        System.out.println();
        System.out.print("Mentor(id): ");

        Mentor mentor = mentorDaoImpl.findById(scan.nextLong());
        group.setMentor(mentor);

        System.out.println("Input: " + group);


        System.out.println("From database: " + groupDao.save(group));
        /*for (Group group1 : groupDao.findAll()) {
            System.out.println(group1);
        }*/
//        System.out.println(groupDao.findById(5L));
    }
}
