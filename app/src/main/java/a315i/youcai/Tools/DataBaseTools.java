package a315i.youcai.Tools;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import a315i.youcai.Model.Home.HomeModel;
/**
 * 　┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 */

/**
 * Created by zhouzunxian on 2017/7/11.
 */

public class DataBaseTools {

    private DataBaseOpenHelper openHelper;
    private static final String  youcai_Table = "youcai_Table";
    private Context mContex;
    //BlackNumberDao单例模式
    //1,私有化构造方法
    private DataBaseTools(Context context){
        //创建数据库已经其表机构
        openHelper = new DataBaseOpenHelper(context);
        this.mContex = context;
    }
    //2,声明一个当前类的对象
    private static DataBaseTools instance = null;
    //3,提供一个静态方法,如果当前类的对象为空,创建一个新的
    public static DataBaseTools getInstance(Context context){
        if(instance == null){
            instance = new DataBaseTools(context);

        }
        return instance;
    }

    public   void save(HomeModel.HomeChildModel type)  {
        //1,开启数据库,准备做写入操作
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(youcai_Table, new String[]{"id"}, "id = ?", new String[]{type.id + ""}, null, null,null);
        int id = 0;
        if(cursor.moveToNext()){
            id = cursor.getInt(0);
        }
        if (type.id == id){
            ContentValues contentValues = new ContentValues();
            contentValues.put("buyCout", type.buyCout);

            db.update(youcai_Table, contentValues, "id = ?", new  String[]{type.id+""});
        }else {
            ContentValues values = new ContentValues();
            values.put("title", type.title);
            values.put("mprice", type.mprice);
            values.put("imgs", type.imgs.get(0));
            values.put("type", type.type);
            values.put("unit", type.unit);
            values.put("maxpacks", type.maxpacks);
            values.put("freight", type.freight);
            values.put("quantity", type.quantity);
            values.put("id", type.id);
            values.put("remains", type.remains);
            values.put("price", type.price);
            values.put("subcate", type.subcate);
            values.put("link", type.link);
            values.put("buyCout", type.buyCout);
            db.insert(youcai_Table, null, values);
        }
        cursor.close();
        db.close();

    }
    public  List<HomeModel.HomeChildModel> findAll()  {
        //1,开启数据库,准备做写入操作
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(youcai_Table, null, null, null, null, null, null);
        List<HomeModel.HomeChildModel> modelList = new ArrayList<>();
        while(cursor.moveToNext()){
            HomeModel.HomeChildModel model = new HomeModel.HomeChildModel();
            List<String> imgsList = new ArrayList<>();
            model.title = cursor.getString(1);
            model.mprice = cursor.getInt(2);
            imgsList.add(cursor.getString(3));
            model.imgs = imgsList ;
            model.type = cursor.getInt(4);
            model.unit = cursor.getString(5);
            model.maxpacks = cursor.getInt(6);
            model.freight = cursor.getInt(7);
            model.quantity = cursor.getInt(8);
            model.id = cursor.getInt(9);
            model.remains = cursor.getInt(10);
            model.price = cursor.getInt(11);
            model.subcate = cursor.getInt(12);
            model.link = cursor.getString(13);
            model.buyCout = cursor.getInt(14);
            modelList.add(model);
        }
        cursor.close();
        db.close();
        return modelList;


    }
//    public  void update(HomeModel.HomeChildModel type)  {
//        SQLiteDatabase db = openHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("buyCout", type.buyCout);
//
//        db.update(youcai_Table, contentValues, "id = ?", new  String[]{type.id+""});
//
//        db.close();
//    }
    public  void delete(HomeModel.HomeChildModel type)  {
        SQLiteDatabase db = openHelper.getWritableDatabase();
      int result =  db.delete(youcai_Table, "id = ?", new String[]{type.id + ""});
        if (result == 1){
            ToastTools.showShort(mContex,"删除成功" + result);
        }else {
            ToastTools.showShort(mContex,"删除失败" + result);
        }


        db.close();
    }
}
