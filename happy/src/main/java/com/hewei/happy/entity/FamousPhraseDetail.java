package com.hewei.happy.entity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="HAPPY_FAMOUS_PHRASE_DETAIL")
public class FamousPhraseDetail implements Serializable {

    private static final long serialVersionUID = -1175507550883404304L;
    private Integer id;

    private Integer famousPhraseId;

    private String famousPhraseTitle;

    private String resource;

    private String poetryContent;

    private String category;

    private String transiation;

    private String annotation;

    private String story;

    private String appreciate;

    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamousPhraseId() {
        return famousPhraseId;
    }

    public void setFamousPhraseId(Integer famousPhraseId) {
        this.famousPhraseId = famousPhraseId;
    }

    public String getFamousPhraseTitle() {
        return famousPhraseTitle;
    }

    public void setFamousPhraseTitle(String famousPhraseTitle) {
        this.famousPhraseTitle = famousPhraseTitle;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPoetryContent() {
        return poetryContent;
    }

    public void setPoetryContent(String poetryContent) {
        this.poetryContent = poetryContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransiation() {
        return transiation;
    }

    public void setTransiation(String transiation) {
        this.transiation = transiation;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getAppreciate() {
        return appreciate;
    }

    public void setAppreciate(String appreciate) {
        this.appreciate = appreciate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
