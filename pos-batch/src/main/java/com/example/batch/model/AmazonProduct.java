package com.example.batch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Id;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonProduct {

    @Id
    private String asin;

    private String main_cat;

    private String title;

    private String price;


    @ElementCollection
    private List<String> category;

    @ElementCollection
    private List<String> imageURLHighRes;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getMain_cat() {
        return main_cat;
    }

    public void setMain_cat(String main_cat) {
        this.main_cat = main_cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getImageURLHighRes() {
        return imageURLHighRes;
    }

    public void setImageURLHighRes(List<String> imageURLHighRes) {
        this.imageURLHighRes = imageURLHighRes;
    }

    public String getPrice() {
        if (!price.startsWith("$"))
            return "";
        else
            return price;
    }
}
