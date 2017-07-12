package a315i.youcai.Model.Home.CaiPuModel;

import java.util.List;

import a315i.youcai.Model.Home.Detai.DetailModel;

/**
 * Created by zhouzunxian on 2017/7/12.
 */

public class CaipuDetailModel {

    public Object recipe;
    public boolean liked;
    public List<DetailModel.detailComments> comments;

    public class recipe{
        public int id;
        public String descr;
        public List<stepsModel> steps;
        public int likes;
        public String title;
        public String headimg;
        public List<itemsModel> items;
        public int cmntcnt;
        public int views;
        public int score;
        public Object tips;
        public String nickname;
        public int status;
        public List<String> imgs;
        public long created;
        public List<Integer> stars;
        public boolean liked;

        public class stepsModel{
            public String img;
            public String text;
        }
        public class itemsModel{
            public String dosage;
            public String title;
            public int id;
        }
    }

}
