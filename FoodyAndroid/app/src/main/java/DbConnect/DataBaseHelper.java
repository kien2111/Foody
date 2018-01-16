package DbConnect;

/**
 * Created by nhox_ on 1/4/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DataBaseHelper extends SQLiteOpenHelper {


    public String DB_PATH = "/data/data/%s/";
    // đường dẫn nơi chứa database
    private static String DB_NAME = "foodydb.sqlite";
    public SQLiteDatabase database;
    private final Context mContext;
    private static DataBaseHelper instance =null;
    public static DataBaseHelper getInstance(Context con){
        if(instance==null){
            instance = new DataBaseHelper(con);
        }
        return instance;
    }
    protected DataBaseHelper(Context con) {
        super(con, DB_NAME, null, 1);
        DB_PATH = String.format(DB_PATH, con.getPackageName());
        this.mContext = con;
    }


    /////////////
    // input:
    // purpose: Kiểm tra xem database đã được tạo hay chưa nếu chưa thì tạo ra rồi thì copy từ đường dẫn vào
    // output: kiểu luận lí true hoặc false
    /////////////
    public boolean isCreatedDatabase() throws IOException {
        // Default là đã có DB
        boolean result = true;
        // Nếu chưa tồn tại DB thì copy từ Asset vào Data
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                copyDataBase();
                result = false;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Error("Error copying database");
            }
        }else{
            mContext.deleteDatabase(DB_NAME);
        }
        /*boolean result = true;
        if (!checkExistDataBase())
        {
            isCreatedDatabase();
        }
            try {
                copyDataBase();
                result = false;
            } catch (Exception e) {
                throw new Error("Error copying database");
            }*/
        return result;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */

    /////////////
    // input:
    // purpose: Kiểm tra tồn tại file .sqlite trong thư mục asset
    // output: kiểu luận lí true hoặc false

    private boolean checkExistDataBase() {
        try {
            String myPath = DB_PATH + DB_NAME;
            System.out.println("check database : " +myPath);
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * copy database from assets folder to the device
     *
     * @throws IOException
     */

    /////////////
    // input:
    // purpose: Đọc file .sqlite trong thư mục asset và ghi file vào đường dẫn trong máy ảo
    // output:
    /////////////

    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        System.out.println("asset :");
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }



    /////////////
    // input:
    // purpose: xóa database trong máy ảo
    // output: kiểu luận lí true hoặc false
    /////////////
    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }


    /////////////
    // input:
    // purpose: Mở kết nối đến database
    // output:
    /////////////
    public void openDataBase() throws SQLException {

        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }

    /////////////
    // input: Tên bảng cần xóa dữ liệu
    // purpose: xóa dữ liệu trong bảng
    // output: kiểu luận lí true hoặc false
    /////////////

    public int deleteData_From_Table(String tbName) {
        int result = 0;
        try {
            openDataBase();
            database.beginTransaction();
            result = database.delete(tbName, null, null);
            if (result >= 0) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            database.endTransaction();
            close();
        } finally {
            database.endTransaction();
            close();
        }

        return result;
    }



}