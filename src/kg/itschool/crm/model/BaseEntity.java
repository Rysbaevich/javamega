package kg.itschool.crm.model;

import java.time.LocalDateTime;

abstract class BaseEntity {
    protected Long id;
    protected LocalDateTime dateCreated;

    public BaseEntity(Long id) {
        this.id = id;
        this.dateCreated = LocalDateTime.now();
    }

    protected BaseEntity() {
        this.dateCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
