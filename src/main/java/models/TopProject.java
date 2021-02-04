package models;

//Класс, объект которого хранит имя проекта и его ранк.
public class TopProject {
    private double rank;
    private String name;

    public TopProject() { }

    public TopProject(double rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
