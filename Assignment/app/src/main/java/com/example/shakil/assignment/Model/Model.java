package com.example.shakil.assignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {

    @SerializedName("product_group_list")
    @Expose
    private List<ProductGroupList> productGroupList = null;
    @SerializedName("literature_list")
    @Expose
    private List<LiteratureList> literatureList = null;
    @SerializedName("physician_sample_list")
    @Expose
    private List<PhysicianSampleList> physicianSampleList = null;
    @SerializedName("gift_list")
    @Expose
    private List<GiftList> giftList = null;

    public List<ProductGroupList> getProductGroupList() {
        return productGroupList;
    }

    public void setProductGroupList(List<ProductGroupList> productGroupList) {
        this.productGroupList = productGroupList;
    }

    public List<LiteratureList> getLiteratureList() {
        return literatureList;
    }

    public void setLiteratureList(List<LiteratureList> literatureList) {
        this.literatureList = literatureList;
    }

    public List<PhysicianSampleList> getPhysicianSampleList() {
        return physicianSampleList;
    }

    public void setPhysicianSampleList(List<PhysicianSampleList> physicianSampleList) {
        this.physicianSampleList = physicianSampleList;
    }

    public List<GiftList> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<GiftList> giftList) {
        this.giftList = giftList;
    }

    public class GiftList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("gift")
        @Expose
        private String gift;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

    }

    public class LiteratureList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("literature")
        @Expose
        private String literature;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLiterature() {
            return literature;
        }

        public void setLiterature(String literature) {
            this.literature = literature;
        }

    }

    public class PhysicianSampleList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("sample")
        @Expose
        private String sample;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSample() {
            return sample;
        }

        public void setSample(String sample) {
            this.sample = sample;
        }

    }

    public class ProductGroupList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("product_group")
        @Expose
        private String productGroup;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductGroup() {
            return productGroup;
        }

        public void setProductGroup(String productGroup) {
            this.productGroup = productGroup;
        }

    }
}
