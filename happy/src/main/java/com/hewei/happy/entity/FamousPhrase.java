package com.hewei.happy.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="HAPPY_FAMOUS_PHRASE")
public class FamousPhrase implements Serializable{

    @Id
    private Integer id;

    private String author;

    private String source;

    private String famousPhrase;

    private String url;

    private Date createdDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFamousPhrase() {
        return famousPhrase;
    }

    public void setFamousPhrase(String famousPhrase) {
        this.famousPhrase = famousPhrase;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
