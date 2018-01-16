package UtilPackage;

/**
 * Created by nhox_ on 1/4/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.Street;
import DbConnect.DataBaseHelper;
import Model.City;
import Model.Comment;
import Model.District;
import Model.Image;
import Model.Meal;
import Model.Restaurant;
import Model.TypeRestaurant;
import Model.User;

/**
 * Created by Devpro on 2/22/2016.
 */
public class DbAdapter extends DataBaseHelper {
    private static DbAdapter instance = null;
    protected DbAdapter(Context con) {
        super(con);
    }
    public static DbAdapter getInstace(Context con){
        if(instance==null){
            instance = new DbAdapter(con);
        }
        return instance;
    }


    /////////////
    // input:
    // purpose: Lấy ảnh theo danh mục quán ăn
    // output: Trả về một bộ các hình ảnh
    /////////////
    public ArrayList<Image> getListImageInType() {
        ArrayList<Image> listimage = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from image_typerest_view", null);
            if(cs==null)System.out.println("khong co du lieu");
            Image image;
            while (cs.moveToNext()) {
                image = new Image(cs.getInt(0),new TypeRestaurant(cs.getString(1),cs.getString(3)), cs.getString(2));
                listimage.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listimage;
    }


    /////////////
    // input: Chuỗi id của thành phố hoặc tỉnh
    // purpose: Lấy các quận huyện thị xã dựa theo thành phố hoặc tỉnh
    // output: Trả về một bộ các quận huyện thị xã theo thành phố hoặc tỉnh mà người dùng chọn
    /////////////
    public ArrayList<District> getTinhTheoTP(String strquery) {
        ArrayList<District> listdist = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT dist_tbl.distid,dist_tbl.namedist,city_tbl.namecity," +
                "dist_tbl.cityid from city_tbl INNER JOIN dist_tbl ON city_tbl.cityid = dist_tbl.cityid where dist_tbl.cityid=='"+strquery+"';", null);
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            District dist;
            while (cs.moveToNext()) {
                dist = new District(cs.getString(0), cs.getString(1),new City(cs.getString(3),cs.getString(2)));
                listdist.add(dist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listdist;
    }

    //Define option in this context
    //0 Dist
    //1 City
    /////////////
    // input: Chuỗi id của quận huyện thị xã hoặc tỉnh thành phố và loại nhà hàng mà người dùng chọn
    // purpose: Lấy các quán ăn theo tiêu chí mà người dùng lựa chọn
    // output: Trả về một bộ các quán theo Quận,huyện,thị xã hoặc theo thành phố, tỉnh
    /////////////
    /*public ArrayList<Restaurant> getQuanAnTheoDistOrCity(String strquery,String typeid,int option) {
        ArrayList<Restaurant> listrest = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = null;
            if(option ==0){
                cs = database.rawQuery("select * from rest_view_all_infomation where distid ='"+strquery+"'"
                        +" and typerestid='"+typeid+"'", null);
            }else if(option==1){
                cs = database.rawQuery("select * from rest_view_all_infomation where cityid ='"+strquery+"'"
                        +" and typerestid='"+typeid+"'", null);
            }
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            Restaurant rest;
            while (cs.moveToNext()) {
                rest = new Restaurant(cs.getInt(0),
                        cs.getString(1),
                        cs.getString(2),
                        new District(cs.getString(3), cs.getString(4), new City(cs.getString(5),cs.getString(6))),
                        new TypeRestaurant(cs.getString(7),cs.getString(8)));
                listrest.add(rest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listrest;
    }*/

    /////////////
    // input:
    // purpose: Lấy các tỉnh,thành phố trong một nước để hiện lên activity
    // output: Trả về một bộ các tỉnh,thành phố
    /////////////
    public ArrayList<City> getTinhThanhForChonTinhThanhActivity() {
        ArrayList<City> listcity = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from city_tbl", null);
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            City city;
            while (cs.moveToNext()) {
                city = new City(cs.getString(0),cs.getString(1));
                listcity.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listcity;
    }



    /////////////
    // input: Chuỗi id của loại quán ăn
    // purpose: Lấy quán ăn dựa trên loại nhà hàng
    // output: Trả về một bộ các nhà hàng theo loại nhà hàng mà người dùng chọn
    /////////////
    /*public ArrayList<Restaurant> getQuanAnTheoDanhMuc(String strquery) {
        ArrayList<Restaurant> listrest = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from rest_view_all_infomation where typerestid = '"+strquery+"'", null);
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            Restaurant rest;
            while (cs.moveToNext()) {
                rest = new Restaurant(cs.getInt(0),
                        cs.getString(1),
                        cs.getString(2),
                        new District(cs.getString(3), cs.getString(4), new City(cs.getString(5),cs.getString(6))),
                        new TypeRestaurant(cs.getString(7),cs.getString(8)));
                listrest.add(rest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listrest;
    }*/

    /////////////
    // input: Chuỗi id của loại nhà hàng và chuỗi id của quận huyện thị xã
    // purpose: Lấy các nhà hàng theo quận huyện thị xã và loại nhà hàng mới nhất vừa đăng kí của phần mềm
    // output: Trả về một bộ các quận huyện thị xã theo theo loại nhà hàng và quận huyện thị xã
    /////////////
    /*public ArrayList<Restaurant> getQuanAnMoiNhatTheoDist(String typerestid,String distid) {
        ArrayList<Restaurant> listrest = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from view_moi_nhat where typerestid ='"+typerestid+"'" +
                    "and distid='"+distid+"'", null);
            if(cs==null)System.out.println("Khong co du lieu hihi");
            Restaurant rest;
            while (cs.moveToNext()) {
                rest = new Restaurant(cs.getInt(0),
                        cs.getString(1),
                        cs.getString(2),
                        new District(cs.getString(3), cs.getString(4), new City(cs.getString(5),cs.getString(6))),
                        new TypeRestaurant(cs.getString(7),cs.getString(8)));
                listrest.add(rest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listrest;
    }*/

    /////////////
    // input: Chuỗi id của loại nhà hàng và chuỗi id của tỉnh thành phố
    // purpose: Lấy các nhà hàng theo tỉnh thành phố và loại nhà hàng mới nhất vừa đăng kí của phần mềm
    // output: Trả về một bộ các quận huyện thị xã theo theo loại nhà hàng và quận huyện thị xã
    /////////////
    /*public ArrayList<Restaurant> getQuanAnMoiNhatTheoCity(String typerestid,String cityid) {
        ArrayList<Restaurant> listrest = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from view_moi_nhat where typerestid ='"+typerestid+"'" +
                    "and cityid='"+cityid+"'", null);
            if(cs==null)System.out.println("Khong co du lieu hihi");
            Restaurant rest;
            while (cs.moveToNext()) {
                rest = new Restaurant(cs.getInt(0),
                        cs.getString(1),
                        cs.getString(2),
                        new District(cs.getString(3), cs.getString(4), new City(cs.getString(5),cs.getString(6))),
                        new TypeRestaurant(cs.getString(7),cs.getString(8)));
                listrest.add(rest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listrest;
    }*/

    /////////////
    // input: Chuỗi id của nhà hàng
    // purpose: Đếm số lượng comment để xác định mức độ phổ biến
    // output: Trả về số comment của một nhà hàng
    /////////////
    public int countCommentToSpecifyPhoBien(int restid) {
        int count = 0;
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select count(*) from comment_tbl where restid="+restid, null);
            if(cs==null)System.out.println("Khong co du lieu hihi");
            cs.moveToFirst();
            count = cs.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return count;
    }


    /////////////
    // input: Chuỗi id của nhà hàng
    // purpose: Lấy hình ảnh theo id của nhà hàng
    // output: Trả về một bộ các hình ảnh của nhà hàng
    /////////////
    public ArrayList<Image> getImageTheoRest(int idquanan) {
        ArrayList<Image> listimagetheorest = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from view_image_rest where restid="+idquanan, null);
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            Image image;
            while (cs.moveToNext()) {
                image = new Image(cs.getInt(0),new Restaurant(cs.getInt(1),cs.getString(2),cs.getString(3),
                        new District(cs.getString(4),null,null),new TypeRestaurant(null,null),null),
                        cs.getString(5));
                listimagetheorest.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listimagetheorest;
    }

    /////////////
    // input: Chuỗi id của nhà hàng
    // purpose: Lấy các bình luận của nhà hàng để hiện lên màn hình
    // output: Trả về một bộ các bình luận mà nhà hàng có
    /////////////
    public ArrayList<Comment> getCommentTheoRest(int idquanan) {
        ArrayList<Comment> lstcomment = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select a.restid,a.userid,b.nameuser,a.comment from comment_tbl as a,user_tbl as b  where restid="+idquanan+"" +
                    "  and a.userid=b.userid limit 2;", null);
            if(cs==null)System.out.println("Khong co du lieu lieu hihi");
            Comment comm;
            while (cs.moveToNext()) {
                comm = new Comment(new Restaurant(cs.getInt(0),null,null,null,null,null),
                        new User(cs.getInt(1),cs.getString(2),null,0),cs.getString(3));
                lstcomment.add(comm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return lstcomment;
    }










    public List<Meal> getMonAnMoiNhatTheoDist(String typerestid, String distid) {
        ArrayList<Meal> listmeal = new ArrayList<>();

        // mo ket noi
        try {
            openDataBase();
            Cursor cs_meal = database.rawQuery("select * from view_moi_nhat_meal where typerestid ='"+typerestid+"'" +
                    "and distid='"+distid+"'", null);
            if(cs_meal==null)System.out.println("Khong co du lieu hihi");
            Meal meal;
            while (cs_meal.moveToNext()) {
                meal = new Meal(cs_meal.getInt(0),
                        cs_meal.getString(1),
                new Restaurant(cs_meal.getInt(2),cs_meal.getString(3),cs_meal.getString(6),
                                new District(cs_meal.getString(4),null,
                                        new City(cs_meal.getString(9),null)),
                        new TypeRestaurant(cs_meal.getString(5),null),null),
                        new Image(cs_meal.getInt(10),new Meal(cs_meal.getInt(0),null,null),cs_meal.getString(11)));
                listmeal.add(meal);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listmeal;
    }

    public List<Meal> getMonAnMoiNhatTheoCity(String typerestid, String cityid) {
        ArrayList<Meal> listmeal = new ArrayList<>();

        // mo ket noi
        try {
            openDataBase();
            Cursor cs_meal = database.rawQuery("select * from view_moi_nhat_meal where typerestid ='"+typerestid+"'" +
                    "and cityid='"+cityid+"'", null);
            if(cs_meal==null)System.out.println("Khong co du lieu hihi");
            Meal meal;
            while (cs_meal.moveToNext()) {
                meal = new Meal(cs_meal.getInt(0),
                        cs_meal.getString(1),
                        new Restaurant(cs_meal.getInt(2),cs_meal.getString(3),cs_meal.getString(6),
                                new District(cs_meal.getString(4),null,
                                        new City(cs_meal.getString(9),null)),
                                new TypeRestaurant(cs_meal.getString(5),null),null),
                        new Image(cs_meal.getInt(10),new Meal(cs_meal.getInt(0),null,null),cs_meal.getString(11)));
                listmeal.add(meal);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listmeal;
    }





    public List<Street> getStreet(String query) {
        ArrayList<Street> lststreet = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from street_tbl where dist_id = '"+query+"'", null);
            if(cs==null)System.out.println("Khong co du lieu hihi");
            Street street;
            while (cs.moveToNext()) {
                street = new Street(cs.getInt(0),cs.getString(1),new District(cs.getString(2),null,new City(cs.getString(3),null)));
                lststreet.add(street);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lststreet;
    }

    public List<TypeRestaurant> getTypeRestaurant() {
        List<TypeRestaurant> lsttyperestaurant = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from typerest_tbl", null);
            if(cs==null)System.out.println("Khong co du lieu hihi");
            TypeRestaurant type;
            while (cs.moveToNext()) {
                type = new TypeRestaurant(cs.getString(0),cs.getString(1));
                lsttyperestaurant.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lsttyperestaurant;
    }







































































































    /*public boolean insertLopHoc(LopHoc lopHoc) {
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("malop", lopHoc.getMaLop());
            values.put("tenlop", lopHoc.getTenLop());
            values.put("siso", lopHoc.getSiSo());
            long rs = database.insert("lophoc", null, values);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public boolean updateLopHoc(LopHoc lopHoc) {
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("malop", lopHoc.getMaLop());
            values.put("tenlop", lopHoc.getTenLop());
            values.put("siso", lopHoc.getSiSo());
            int rs = database.update("lophoc", values, "id=" + lopHoc.getID(), null);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public boolean deleteLopHoc(int id) {
        boolean result = false;
        try {

            openDataBase();
            //
            int rs = database.delete("lophoc", "id=" + id, null);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }*/
}

