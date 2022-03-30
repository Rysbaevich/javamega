package kg.itschool.crm.dao.daoutil;


import kg.itschool.crm.dao.*;
import kg.itschool.crm.dao.impl.*;

public abstract class DaoFactory {
    static {
        try {
        System.out.println("Loading driver...");
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed");
            e.printStackTrace();
        }
    }

    public static ManagerDao getManagerDaoSQL() {
        return new ManagerDaoImpl();
    }

    public static MentorDao getMentorDaoSQL() {
        return new MentorDaoImpl();
    }

    public static StudentDao getStudentDaoSQL() {
        return new StudentDaoImpl();
    }

    public static CourseDao getCourseDaoSQL() {
        return new CourseDaoImpl();
    }

    public static GroupDao getGroupDaoSQL() {
        return new GroupDaoImpl();
    }

    public static AddressDao getAddressDaoSQL() {
        return new AddressDaoImpl();
    }

    public static CourseFormatDao getCourseFormatDaoSQL() {
        return new CourseFormatDaoImpl();
    }
}
