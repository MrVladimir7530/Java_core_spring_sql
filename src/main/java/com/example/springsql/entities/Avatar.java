package com.example.springsql.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    private String path;
    private Long size;
    private String mediaType;
    @JsonIgnore
    private byte[] data;
    @JsonIgnore
    @OneToOne
    private Student student;
    public Avatar() {

    }

    public Avatar(Long id, String path, Long size, String mediaType, byte[] data, Student student) {
        this.id = id;
        this.path = path;
        this.size = size;
        this.mediaType = mediaType;
        this.data = data;
        this.student = student;
    }

    public Avatar(Long id, Long size, String mediaType) {
        this.id = id;
        this.size = size;
        this.mediaType = mediaType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
