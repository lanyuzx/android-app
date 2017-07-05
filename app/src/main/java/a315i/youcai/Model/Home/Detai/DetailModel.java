package a315i.youcai.Model.Home.Detai;

import java.util.List;

/**
 * Created by zhouzunxian on 2017/7/5.
 */

public class DetailModel {

    public List<detailItem> item;
    public List<detailComments> comments;

    public class detailItem{
        public String title;
        public String channel;
        public String descr;
        public String mprice;
        public String farm;
        public List<String> imgs;
        public String thumb;
        public int packw;
        public int grossw;
        public int status;
        public int type;
        public String unit;
        public int maxpacks;
        public int sales;
        public int category;
        public int cmntcnt;
        public int fid;
        public int freight;
        public List<String> detail;
        public int quantity;
        public int id;
        public int remains;
        public int tags;
        public int price;
        public int subcate;
        public String payset;
        public List<Integer> wids;
        public List<String> more;


    }
    public class  detailComments{
        public int type;
        public String content;
        public int status;
        public int target;
        public int uid;
        public int id;
        public int orderno;
        public List<String> imgs;
        public int modified;
        public int rating;
        public String title;
        public int nickname;
        public int headimg;

    }

}
