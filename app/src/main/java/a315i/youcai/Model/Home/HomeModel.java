package a315i.youcai.Model.Home;


import java.util.List;
import java.util.Map;

/**
 * Created by zhouzunxian on 2017/7/4.
 */

public class HomeModel {
    public List<HomeChildModel> items;
    public List<HomeChildModel> tops;
    public List<HomeChildModel> entries;
    public List<slidesModel> slides;


    public class slidesModel{
        public String title;
        public int type;
        public String img;
        public Map<String,Integer> link;
    }
    public  class HomeChildModel {
        public String title;
        public float mprice;
        public List<String> imgs;
        public int type;
        public String unit;
        public int maxpacks;
        public int freight;
        public int quantity;
        public int id;
        public int remains;
        public float price;
        public int subcate;
        public String link;

        public int buyCout;

    }

}



