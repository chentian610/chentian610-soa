package com.chentian610.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Rank implements Serializable{
    private static final long serialVersionUID = -8736616045315083846L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String keyword;

    private String brand;

    private Integer rank;

    private Integer is_ads;


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", brand='" + brand + '\'' +
                ", is_ads='" + is_ads + '\'' +
                '}';
    }

    public void setIs_ads(Integer is_ads) {
        this.is_ads = is_ads;
    }
    public Integer getIs_ads() {
        return is_ads;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}