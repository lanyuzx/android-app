package a315i.youcai.Model.Home.Detai;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouzunxian on 2017/7/5.
 */

public class DetailModel {

    public Object item;
    public List<detailComments> comments;

    public class detailItem{
        public String title;
        public String descr;
        public int mprice;
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
        public Object detail;
        public int quantity;
        public int id;
        public int remains;
        public Object tags;
        public int price;
        public int subcate;
        public String payset;
        public Object wids;
        public Object more;


    }
    public class detail {
        public List<String> imgs;
        public String content;
    }
    public class  detailComments{
        public int type;
        public String content;
        public int status;
        public int target;
        public int uid;
        public int id;
        public String orderno;
        public List<String> imgs;
        public String modified;
        public String created;
        public int rating;
        public String title;
        public String nickname;
        public String headimg;

    }

}
