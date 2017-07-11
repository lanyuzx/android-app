package a315i.youcai.Model.Home;


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouzunxian on 2017/7/4.
 */
@Table(name = "youcai_product", onCreated = "CREATE UNIQUE INDEX index_name")
public class HomeModel {
    public HomeModel(){

    }

    public List<HomeChildModel> items;
    public List<HomeChildModel> tops;
    public List<entriesModel> entries;
    public List<slidesModel> slides;


    public class slidesModel{
        public String title;
        public int type;
        public String img;
        public Map<String,Integer> link;
    }

    public static class HomeChildModel  {

        public HomeChildModel(){

        }

        public String getTitle() {
            return title;
        }

        public float getMprice() {
            return mprice;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public int getType() {
            return type;
        }

        public String getUnit() {
            return unit;
        }

        public int getMaxpacks() {
            return maxpacks;
        }

        public int getFreight() {
            return freight;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getId() {
            return id;
        }

        public int getRemains() {
            return remains;
        }

        public float getPrice() {
            return price;
        }

        public int getSubcate() {
            return subcate;
        }

        public String getLink() {
            return link;
        }

        public int getBuyCout() {
            return buyCout;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMprice(float mprice) {
            this.mprice = mprice;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setMaxpacks(int maxpacks) {
            this.maxpacks = maxpacks;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setRemains(int remains) {
            this.remains = remains;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setSubcate(int subcate) {
            this.subcate = subcate;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setBuyCout(int buyCout) {
            this.buyCout = buyCout;
        }

        @Column(name = "title")
        public String title;
        @Column(name = "mprice")
        public float mprice;
        @Column(name = "imgs")
        public List<String> imgs;
        @Column(name = "type")
        public int type;
        @Column(name = "unit")
        public String unit;
        @Column(name = "maxpacks")
        public int maxpacks;
        @Column(name = "freight")
        public int freight;
        @Column(name = "quantity")
        public int quantity;
        @Column(name = "id",isId = true)
        public int id;
        @Column(name = "remains")
        public int remains;
        @Column(name = "price")
        public float price;
        @Column(name = "subcate")
        public int subcate;
        @Column(name = "link")
        public String link;
        @Column(name = "buyCout")
        public int buyCout;

        @Override
        public String toString() {
            return "HomeChildModel{" +
                    "title='" + title + '\'' +
                    ", mprice=" + mprice +
                    ", imgs=" + imgs +
                    ", type=" + type +
                    ", unit='" + unit + '\'' +
                    ", maxpacks=" + maxpacks +
                    ", freight=" + freight +
                    ", quantity=" + quantity +
                    ", id=" + id +
                    ", remains=" + remains +
                    ", price=" + price +
                    ", subcate=" + subcate +
                    ", link='" + link + '\'' +
                    ", buyCout=" + buyCout +
                    '}';
        }
    }
    public class entriesModel {
        public String link;
        public String icon;
        public String title;
    }

}



