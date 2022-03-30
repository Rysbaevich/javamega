package kg.itschool.crm.dao.impl;

import kg.itschool.crm.dao.GroupDao;
import kg.itschool.crm.dao.daoutil.Log;
import kg.itschool.crm.model.Course;
import kg.itschool.crm.model.CourseFormat;
import kg.itschool.crm.model.Group;
import kg.itschool.crm.model.Mentor;

import java.sql.*;
import java.time.LocalTime;

public class GroupDaoImpl implements GroupDao {

    public GroupDaoImpl() {
        Connection connection = null;
        Statement statement = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " establishing connection");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), connection.getClass().getSimpleName(), " connection established");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_groups(" +
                    "id             BIGSERIAL,                " +
                    "name           VARCHAR(50)     NOT NULL, " +
                    "group_time     TIME            NOT NULL, " +
                    "date_created   TIMESTAMP       NOT NULL DEFAULT NOW(), " +
                    "course_id      BIGINT          NOT NULL, " +
                    "mentor_id      BIGINT          NOT NULL, " +
                    "" +
                    "CONSTRAINT pk_group_id PRIMARY KEY(id), " +
                    "CONSTRAINT fk_course_id FOREIGN KEY(course_id) " +
                    "   REFERENCES tb_courses(id), " +
                    "CONSTRAINT fk_mentor_id FOREIGN KEY(mentor_id) " +
                    "   REFERENCES tb_mentors(id) " +
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
    public Group save(Group group) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Group savedGroup = null;
        Course savedCourse = null;
        CourseFormat savedCourseFormat = null;
        Mentor savedMentor = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connecting to database...");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connection succeeded.");

            String createQuery = "INSERT INTO tb_groups(" +
                    "name, group_time, date_created, course_id, mentor_id ) " +

                    "VALUES(?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setTime(2, Time.valueOf(group.getGroupTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(group.getDateCreated()));
            preparedStatement.setLong(4, group.getCourse().getId());
            preparedStatement.setLong(5, group.getMentor().getId());

            preparedStatement.execute();
            close(preparedStatement);

            String readQuery = "" +
                    "SELECT  g.id AS group_id, g.name AS group_name, g.group_time, " +
                    "    g.date_created AS group_dc, " +
                    "        g.course_id, c.name AS course_name, c.price, course_dc, " +
                    "    format_id, course_format, course_duration_weeks, lesson_duration, " +
                    "    lessons_per_week, is_online, format_dc, " +
                    "        g.mentor_id, m.first_name, m.last_name, m.email, m.phone_number, m.salary, m.dob, m.date_created AS mentor_dc " +
                    "    FROM tb_groups AS g " +
                    "" +
                    "    JOIN (SELECT c.id AS course_id, c.name, c.price, c.date_created AS course_dc, " +
                    "    f.id AS format_id, f.course_format, f.course_duration_weeks, f.lesson_duration, " +
                    "    f.lessons_per_week, f.is_online, f.date_created AS format_dc " +
                    "    FROM tb_courses AS c " +
                    "    JOIN tb_course_format AS f " +
                    "    ON c.course_format_id = f.id) AS c " +
                    "    ON g.course_id = c.course_id " +
                    "" +
                    "    JOIN tb_mentors AS m " +
                    "    ON g.mentor_id = m.id " +
                    "    ORDER BY g.id DESC LIMIT 1;";

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
            savedCourse.setName(resultSet.getString("course_name"));
            savedCourse.setPrice(Double.parseDouble(resultSet.getString("price").replaceAll("[^\\d\\.]+", "")) / 100);
            savedCourse.setCourseFormat(savedCourseFormat);
            savedCourse.setDateCreated(resultSet.getTimestamp("course_dc").toLocalDateTime());

            savedMentor = new Mentor();
            savedMentor.setId(resultSet.getLong("mentor_id"));
            savedMentor.setFirstName(resultSet.getString("first_name"));
            savedMentor.setLastName(resultSet.getString("last_name"));
            savedMentor.setEmail(resultSet.getString("email"));
            savedMentor.setPhoneNumber(resultSet.getString("phone_number"));
            savedMentor.setSalary(Double.parseDouble(resultSet.getString("salary").replaceAll("[^\\d\\.]", "")) / 100);
            savedMentor.setDob(resultSet.getDate("dob").toLocalDate());
            savedMentor.setDateCreated(resultSet.getTimestamp("mentor_dc").toLocalDateTime());

            savedGroup = new Group();
            savedGroup.setId(resultSet.getLong("group_id"));
            savedGroup.setName(resultSet.getString("group_name"));
            savedGroup.setGroupTime(LocalTime.parse(resultSet.getString("group_time")));
            savedGroup.setDateCreated(resultSet.getTimestamp("group_dc").toLocalDateTime());
            savedGroup.setCourse(savedCourse);
            savedGroup.setMentor(savedMentor);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedGroup;
    }

    @Override
    public Group findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Group group = null;
        Course course = null;
        CourseFormat courseFormat = null;
        Mentor mentor = null;

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connecting to database...");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connection succeeded.");

            String readQuery = "SELECT  g.id AS group_id, g.name AS group_name, g.group_time, " +
                    "    g.date_created AS group_dc, " +
                    "        g.course_id, c.name AS course_name, c.price, course_dc, " +
                    "    format_id, course_format, course_duration_weeks, lesson_duration, " +
                    "    lessons_per_week, is_online, format_dc, " +
                    "        g.mentor_id, m.first_name, m.last_name, m.email, m.phone_number, m.salary, m.dob, m.date_created AS mentor_dc " +
                    "    FROM tb_groups AS g " +
                    "" +
                    "    JOIN (SELECT c.id AS course_id, c.name, c.price, c.date_created AS course_dc, " +
                    "    f.id AS format_id, f.course_format, f.course_duration_weeks, f.lesson_duration, " +
                    "    f.lessons_per_week, f.is_online, f.date_created AS format_dc " +
                    "    FROM tb_courses AS c " +
                    "    JOIN tb_course_format AS f " +
                    "    ON c.course_format_id = f.id) AS c " +
                    "    ON g.course_id = c.course_id " +
                    "" +
                    "    JOIN tb_mentors AS m " +
                    "    ON g.mentor_id = m.id " +
                    "" +
                    "WHERE g.id  = ?";

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
            course.setName(resultSet.getString("course_name"));
            course.setPrice(Double.parseDouble(resultSet.getString("price").replaceAll("[^\\d\\.]+", "")) / 100);
            course.setCourseFormat(courseFormat);
            course.setDateCreated(resultSet.getTimestamp("course_dc").toLocalDateTime());

            mentor = new Mentor();
            mentor.setId(resultSet.getLong("mentor_id"));
            mentor.setFirstName(resultSet.getString("first_name"));
            mentor.setLastName(resultSet.getString("last_name"));
            mentor.setEmail(resultSet.getString("email"));
            mentor.setPhoneNumber(resultSet.getString("phone_number"));
            mentor.setSalary(Double.parseDouble(resultSet.getString("salary").replaceAll("[^\\d\\.]", "")) / 100);
            mentor.setDob(resultSet.getDate("dob").toLocalDate());
            mentor.setDateCreated(resultSet.getTimestamp("mentor_dc").toLocalDateTime());

            group = new Group();
            group.setId(resultSet.getLong("group_id"));
            group.setName(resultSet.getString("group_name"));
            group.setGroupTime(LocalTime.parse(resultSet.getString("group_time")));
            group.setDateCreated(resultSet.getTimestamp("group_dc").toLocalDateTime());
            group.setCourse(course);
            group.setMentor(mentor);

        } catch (SQLException e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return group;
    }
}
