package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//Класс, объект которого хранит поле items, полученное с URL (остальные поля игнорируются).
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    @JsonProperty("items")
    private ArrayList<Item> items;

    public Project() { }

    public Project(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
