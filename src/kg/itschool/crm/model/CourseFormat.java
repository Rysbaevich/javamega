package kg.itschool.crm.model;

import java.time.LocalTime;

public final class CourseFormat extends BaseEntity {

    private String format;
    private int courseDurationWeeks;
    private LocalTime lessonDuration;
    private int lessonPerWeek;
    private boolean isOnline;

    public CourseFormat() {
        super();
    }

    public CourseFormat(Long id, String format, int courseDurationWeeks, LocalTime lessonDuration, int lessonPerWeek, boolean isOnline) {
        super(id);
        this.format = format;
        this.courseDurationWeeks = courseDurationWeeks;
        this.lessonDuration = lessonDuration;
        this.lessonPerWeek = lessonPerWeek;
        this.isOnline = isOnline;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getCourseDurationWeeks() {
        return courseDurationWeeks;
    }

    public void setCourseDurationWeeks(int courseDurationWeeks) {
        this.courseDurationWeeks = courseDurationWeeks;
    }

    public LocalTime getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(LocalTime lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public int getLessonPerWeek() {
        return lessonPerWeek;
    }

    public void setLessonPerWeek(int lessonPerWeek) {
        this.lessonPerWeek = lessonPerWeek;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public String toString() {
        return "CourseFormat{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", format='" + format + '\'' +
                ", courseDurationWeeks=" + courseDurationWeeks +
                ", lessonDuration=" + lessonDuration +
                ", lessonPerWeek=" + lessonPerWeek +
                ", isOnline=" + isOnline +
                '}';
    }
}
