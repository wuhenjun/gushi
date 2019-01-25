package com.hewei.happy.vo;

import com.hewei.happy.entity.FamousPhraseDetail;

import java.io.Serializable;

public class FamousPhraseVO implements Serializable{

    private static final long serialVersionUID = 4575961042818993591L;

    private Integer id;

    private String author;

    private String source;

    private String famousPhrase;

    private FamousPhraseDetail famousPhraseDetail;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public FamousPhraseDetail getFamousPhraseDetail() {
        return famousPhraseDetail;
    }

    public void setFamousPhraseDetail(FamousPhraseDetail famousPhraseDetail) {
        this.famousPhraseDetail = famousPhraseDetail;
    }
}
