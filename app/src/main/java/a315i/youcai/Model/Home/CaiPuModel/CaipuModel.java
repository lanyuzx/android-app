package a315i.youcai.Model.Home.CaiPuModel;

import java.util.List;

/**
 * Created by zhouzunxian on 2017/7/11.
 */

public class CaipuModel {

    public List<recipesModel> recipes;


    public class recipesModel {
        public int id;
        public int status;
        public String title;
        public List<String> imgs;
        public String nickname;
        public List<String> items;


    }
}
