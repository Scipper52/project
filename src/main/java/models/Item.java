package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Класс, объект которого хранит, необходимую из условий задачи, информацию об одном проекте.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("size")
    private int size;
    @JsonProperty("language")
    private String language;
    @JsonProperty("forks")
    private int forks;
    @JsonProperty("open_issues")
    private int openIssues;
    @JsonProperty("watchers")
    private int watchers;

    public Item() { }

    public Item(int id, String name, String updatedAt,
                int size, String language, int forks,
                int openIssues, int watchers) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.size = size;
        this.language = language;
        this.forks = forks;
        this.openIssues = openIssues;
        this.watchers = watchers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }
}
