package kg.itschool.crm.dao.impl;

import kg.itschool.crm.dao.GroupDao;
import kg.itschool.crm.dao.daoutil.Log;
import kg.itschool.crm.model.Course;
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
                    "group_time     TIMESTAMP       NOT NULL, " +
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
            preparedStatement.setString(2, String.valueOf(group.getGroupTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(group.getDateCreated()));
            preparedStatement.setLong(4, group.getCourse().getId());
            preparedStatement.setLong(5, group.getMentor().getId());

            preparedStatement.execute();
            close(preparedStatement);

            String readQuery = "SELECT * FROM tb_groups AS g " +
                    "JOIN tb_courses AS c " +
                    "ON g.group_id = c.id " +
                    "JOIN tb_mentors AS m " +
                    "ON g.group_id = m.id " +
                    "ORDER BY id DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(readQuery);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            savedCourse = new Course();
            savedCourse.setId(resultSet.getLong("c.id"));
            savedCourse.setName(resultSet.getString("name"));
            savedCourse.setPrice(Double.parseDouble(resultSet.getString("price").replaceAll("[^\\d\\.]+", "")) / 100);
            savedCourse.setCourseFormat();
            savedCourse.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

            savedGroup = new Group();
            savedGroup.setId(resultSet.getLong("id"));
            savedGroup.setName(resultSet.getString("name"));
            savedGroup.setGroupTime(LocalTime.parse(resultSet.getString("group_time")));
            savedGroup.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
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

        try {
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connecting to database...");
            connection = getConnection();
            Log.info(this.getClass().getSimpleName(), Connection.class.getSimpleName(), " connection succeeded.");

            String readQuery = "SELECT * FROM tb_groups WHERE id = ?";

            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            group = new Group();
            group.setId(resultSet.getLong("id"));
            group.setName(resultSet.getString("name"));
            group.setGroupTime(LocalTime.parse(resultSet.getString("group_time")));
            group.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

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
