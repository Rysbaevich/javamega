package kg.itschool.crm.dao.impl;

import kg.itschool.crm.dao.CourseDao;
import kg.itschool.crm.dao.daoutil.Log;
import kg.itschool.crm.model.Course;
import kg.itschool.crm.model.CourseFormat;

import java.sql.*;

public class CourseDaoImpl implements CourseDao {

    public CourseDaoImpl() {
        Connection connection = null;
        Statement statement = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " establishing connection");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), connection.getClass().getSimpleName(), " connection established");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_courses(" +
                    "id                 BIGSERIAL,              " +
                    "name               VARCHAR(50)  NOT NULL,  " +
                    "price              MONEY        NOT NULL,  " +
                    "date_created       TIMESTAMP    NOT NULL DEFAULT NOW(), " +
                    "course_format_id   BIGINT NOT NULL, " +
                    "" +
                    "CONSTRAINT pk_course_id PRIMARY KEY(id), " +
                    "CONSTRAINT fk_course_format_id FOREIGN KEY (course_format_id) " +
                    "   REFERENCES tb_course_format(id) " +
                    ");";

            Log.info(this.getClass().getSimpleName(), Statement.class.getSimpleName(), " creating statement...");
            statement = connection.createStatement();
            Log.info(this.getClass().getSimpleName(), Statement.class.getSimpleName(), " executing create table statement...");
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Course save(Course course) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course savedCourse = null;
        CourseFormat savedCourseFormat = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connecting to database...");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connection succeeded.");

            String createQuery = "INSERT INTO tb_courses(" +
                    "name, price, date_created, course_format_id) " +

                    "VALUES(?, MONEY(?), ?, ?)";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, (course.getPrice() + "").replace(".", ","));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(course.getDateCreated()));
            preparedStatement.setLong(4, course.getCourseFormat().getId());

            preparedStatement.execute();
            close(preparedStatement);

            String readQuery = "SELECT c.id AS course_id, c.name, c.price, c.date_created AS course_dc, " +
            "f.id AS format_id, f.course_format, f.course_duration_weeks, f.lesson_duration, " +
                    "f.lessons_per_week, f.is_online, f.date_created AS format_dc " +
                    "FROM tb_courses AS c " +
                    "JOIN tb_course_format AS f " +
                    "ON c.course_format_id = f.id " +
                    ";";


                    /*
                    "SELECT c.id AS courses_id FROM tb_courses AS c " +
                    "JOIN tb_course_format AS f " +
                    "ON c.course_format_id = f.id " +
                    "ORDER BY c.id DESC LIMIT 1;";
*/
            preparedStatement = connection.prepareStatement(readQuery);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedCourseFormat = new CourseFormat();
            savedCourseFormat.setId(resultSet.getLong("format_id"));
            savedCourseFormat.setFormat(resultSet.getString("course_format"));
            savedCourseFormat.setCourseDurationWeeks(resultSet.getInt("course_duration_weeks"));
            savedCourseFormat.setLessonDuration(resultSet.getTime("lesson_duration").toLocalTime());
            savedCourseFormat.setLessonPerWeek(resultSet.getInt("lessons_per_week"));
            savedCourseFormat.setOnline(resultSet.getBoolean("is_online"));
            savedCourseFormat.setDateCreated(resultSet.getTimestamp("format_dc").toLocalDateTime());

            savedCourse = new Course();
            savedCourse.setId(resultSet.getLong("course_id"));
            savedCourse.setName(resultSet.getString("name"));
            savedCourse.setPrice(Double.parseDouble(resultSet.getString("price").replaceAll("[^\\d\\.]+", "")) / 100);
            savedCourse.setCourseFormat(savedCourseFormat);
            savedCourse.setDateCreated(resultSet.getTimestamp("course_dc").toLocalDateTime());

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedCourse;
    }

    @Override
    public Course findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        CourseFormat courseFormat = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connecting to database...");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connection succeeded.");

            String readQuery = "SELECT c.id AS course_id, c.name, c.price, c.date_created AS course_dc, " +
                    "f.id AS format_id, f.course_format, f.course_duration_weeks, f.lesson_duration, " +
                    "f.lessons_per_week, f.is_online, f.date_created AS format_dc " +
                    "FROM tb_courses AS c " +
                    "JOIN tb_course_format AS f " +
                    "ON c.course_format_id = f.id " +
                    "WHERE c.id = ?;";

            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            courseFormat = new CourseFormat();
            courseFormat.setId(resultSet.getLong("format_id"));
            courseFormat.setFormat(resultSet.getString("course_format"));
            courseFormat.setCourseDurationWeeks(resultSet.getInt("course_duration_weeks"));
            courseFormat.setLessonDuration(resultSet.getTime("lesson_duration").toLocalTime());
            courseFormat.setLessonPerWeek(resultSet.getInt("lessons_per_week"));
            courseFormat.setOnline(resultSet.getBoolean("is_online"));
            courseFormat.setDateCreated(resultSet.getTimestamp("format_dc").toLocalDateTime());

            course = new Course();
            course.setId(resultSet.getLong("course_id"));
            course.setName(resultSet.getString("name"));
            course.setPrice(Double.parseDouble(resultSet.getString("price").replaceAll("[^\\d\\.]+", "")) / 100);
            course.setCourseFormat(courseFormat);
            course.setDateCreated(resultSet.getTimestamp("course_dc").toLocalDateTime());

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return course;
    }
}
