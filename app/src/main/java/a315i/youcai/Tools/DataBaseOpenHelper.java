package a315i.youcai.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhouzunxian on 2017/7/11.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public DataBaseOpenHelper(  Context mContex){
        super(mContex,"youcai_product",null,1);

    }
    public static final String youcai_Table = "create table youcai_Table ("

            + "id integer primary key autoincrement, "
            + "title text, "
            + "mprice integer, "
            + "imgs text, "
            + "type integer,"
            + "unit text,"
            + "maxpacks integer,"
            + "freight integer,"
            + "quantity integer,"
            + "id integer,"
            + "remains integer,"
            + "price integer,"
            + "subcate integer,"
            + "link text,"
            + "buyCout integer)"
            ;
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(youcai_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
