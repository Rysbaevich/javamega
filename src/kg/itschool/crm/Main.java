package kg.itschool.crm;

import kg.itschool.crm.dao.*;
import kg.itschool.crm.dao.daoutil.DaoFactory;
import kg.itschool.crm.dao.impl.CourseDaoImpl;
import kg.itschool.crm.dao.impl.CourseFormatDaoImpl;
import kg.itschool.crm.model.Course;
import kg.itschool.crm.model.CourseFormat;
import kg.itschool.crm.model.Manager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        allCourseFormats();
//        allCourses();
//        CourseDaoImpl courseDao = new CourseDaoImpl();
//        System.out.println(courseDao.findById(1L));
    }

    public static void allManagers() {
        Manager manager = new Manager();

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

        ManagerDao managerDao = DaoFactory.getManagerDaoSQL();

        System.out.println("From database: " + managerDao.save(manager));

        System.out.println(managerDao.findById(1L));
    }
    public static void allCourseFormats() {
        CourseFormat courseFormat = new CourseFormat();

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

        CourseFormatDao courseFormatDao = DaoFactory.getCourseFormatDaoSQL();

        System.out.println("From database: " + courseFormatDao.save(courseFormat));


//        System.out.println(courseFormatDao.findById(1L));
    }
    public static void allCourses() {

        Course course = new Course();
        CourseFormatDaoImpl courseFormatDaoImpl = new CourseFormatDaoImpl();
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

        CourseDao courseDao = DaoFactory.getCourseDaoSQL();

//        System.out.println("From database: " + courseDao.save(course));


        System.out.println(courseDao.findById(1L));
    }
}
