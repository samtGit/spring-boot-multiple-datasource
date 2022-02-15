package com.tutorials.multipledatasource.primary.domain;


import javax.persistence.*;

@Entity
@Table(name = "PRIMARY_TABLE")
public class PrimaryEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PR_COMMENT")
    private String comment;

    public Long getId() {
        return id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PrimaryEntity(String comment) {
        this.comment = comment;
    }
}
