package edu.cs.birzeit.finalassignment.model;

public class CD {
    private  String id;
    private  String tittle;
    private String cat;
    private String genera;

    public CD(String id, String title, String cat, String genera) {
        this.id=id;
        this.tittle=title;
        this.cat=cat;
        this.genera=genera;
    }
    public String getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getCat() {
        return cat;
    }


    @Override
    public String toString() {
        return  "Tittle: "+ tittle +"Type of the game: "+genera+" ,Price :"+cat;


    }
}