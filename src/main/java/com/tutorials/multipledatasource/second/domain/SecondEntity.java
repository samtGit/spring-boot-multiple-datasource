package com.tutorials.multipledatasource.second.domain;


import javax.persistence.*;

@Entity
@Table(name = "SC_TABLE")
public class SecondEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id2;

    @Column(name = "SC_COMMENT")
    private String comment2;

    public Long getId() {
        return id2;
    }

    public String getComment() {
        return comment2;
    }

    public void setComment(String comment) {
        this.comment2 = comment;
    }

    public SecondEntity(String comment) {
        this.comment2 = comment;
    }
}
