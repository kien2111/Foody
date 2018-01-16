package UtilPackage;

import com.example.nhox_.foody.R;

import java.util.List;

import UtilPackage.Abstract.RestCall;

/**
 * Created by nhox_ on 2/4/2017.
 */

public class GlobalVariable {
    public static Integer[] item_for_listfragment={R.drawable.newcircle,R.drawable.near,R.drawable.ico_chat,R.drawable.airplane_paper,
            R.drawable.e_card,R.drawable.foodsolid,R.drawable.bankcard,R.drawable.ic_delivery};
    public static String[] item_text_listfragment={"Mới nhất","Gần tôi","Phổ biến","Du khách","Ưu đãi E-card","Đặt chỗ","Ưu đãi thẻ","Đặt giao hàng"};
    public static Integer[] getItem_for_listfragmentCheck={R.drawable.newcircle_selected,R.drawable.near_selected,R.drawable.icon_chat_selected,
    R.drawable.airplane_paper_selected,R.drawable.e_card_selected,R.drawable.foodsolid_selected,R.drawable.bankcard_selected,R.drawable.ic_delivery_selected};
    public static String[] text_for_item_recycleview={"Gần tôi","Coupon",
            "Đặt chỗ ưu đãi","Đặt giao hàng",
            "E-card","Game & Fun",
            "Bình luận","Blogs",
            "Top thành viên","Video"};
    public static Integer[] item_image_for_recycleview={R.drawable.icon_gridview_nearby,R.drawable.icon_gridview_barcode,
            R.drawable.ticon,R.drawable.circle,
            R.drawable.ecard,R.drawable.game,
            R.drawable.icon_for_gridview,R.drawable.icon_gridview_text,
            R.drawable.user_gridview,R.drawable.icon_gridview_camera};
    public static String[] text_for_angi_fragmentmoinhat={
            "Mới nhất","Gần tôi","Xem nhiều","Du khách"
    };
    public static String resourceurl = RestCall.myurl+"/FoodyService/rest/ImageController/resource/";

    //////
    public static int oldpositiontabinlistview1click=0;
    public static int oldpositiontabinlistview2click=0;
    public static int oldpositiontabinlistview3click=-1;
    public static int oldpositionchontinhthanhactivityclick=0;


    public static boolean firstcreate = true;
    //////
    public static int tren_bao_nhieu_comment_la_pho_bien=1;

    //////
    public static EnumItemDiaDiemClick currentstateExpandableclick = EnumItemDiaDiemClick.ThanhPhoClick;
    public static int groupposition = -1;
    public static int currentstreetid = 0;
    public static String currentdistid = null;
    public static String currentcityid ="tphcm";
    public static String currenttyperestid="l1";

    public static String tabtext1display = "Mới nhất";
    public static String tabtext2display = "Danh mục";
    public static String tabtext3display = "TP.HCM";



    //
    public static EnumItemDiaDiemClick currentstateExpandableclick_1 = EnumItemDiaDiemClick.ThanhPhoClick;
    public static int groupposition_1 = -1;
    public static int oldpositiontabinlistview1click_1=0;
    public static int oldpositiontabinlistview2click_1=0;
    public static int oldpositiontabinlistview3click_1=-1;
    public static int oldpositionchontinhthanhactivityclick_1=0;
    //
    public static int currentstreetid_1 = 0;
    public static String currentdistid_1 = null;
    public static String currentcityid_1 ="tphcm";
    public static String currenttyperestid_1="l1";
    //
    public static String tabtext1display_1 = "Mới nhất";
    public static String tabtext2display_1 = "Danh mục";
    public static String tabtext3display_1 = "TP.HCM";

}
