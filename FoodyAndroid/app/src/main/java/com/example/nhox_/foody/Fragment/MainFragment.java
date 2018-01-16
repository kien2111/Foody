package com.example.nhox_.foody.Fragment;

/**
 * Created by nhox_ on 17/3/2017.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.Adapter.PagerAdapterForImageSlider;
import com.example.nhox_.foody.Adapter.RecycleViewAdaptersForQuanAn;
import com.example.nhox_.foody.Adapter.RecyclerViewAdapter;
import com.example.nhox_.foody.Adapter.RestaurantAdapter;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.myListFragmentfordiadiem;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import DbConnect.DataBaseHelper;
import Model.Comment;
import Model.District;
import Model.Image;
import Model.Restaurant;
import UtilPackage.AsyncResponse;
import UtilPackage.DbAdapter;
import UtilPackage.EnumItemDiaDiemClick;
import UtilPackage.EnumTaskOption;
import UtilPackage.GlobalVariable;
import UtilPackage.INotifier;
import UtilPackage.INotifierFromDiaDiemFragment;
import UtilPackage.InfoFragmentClick;
import UtilPackage.MyInterface.IOnLoadMore;
import UtilPackage.NotifierToParent;
import UtilPackage.TaskCallRest.CallRestTaskgetImageQuanAn;
import UtilPackage.TaskCallRest.CallRestTaskgetList;
import UtilPackage.Utility;
import UtilPackage.updateFragment;


public class MainFragment extends Fragment implements IOnLoadMore,SwipeRefreshLayout.OnRefreshListener, ViewPager.OnPageChangeListener, NotifierToParent, INotifierFromDiaDiemFragment, INotifier, updateFragment {

    TabLayout tabLayout;
    Context context;

    View view;
    LinearLayout tabcontent;
    ListView listviewcontainrestaurant;
    SwipeRefreshLayout swipe_refresh_layout;
    private List<Restaurant> lstRestaurant;
    private GridLayoutManager rmLayoutManagerl;
    private Bundle savedInstanceState;
    RestaurantAdapter restadapter;
    //

    //view of parent
    BottomNavigationViewEx bottomview=null;


    //public static View viewofparent;
    /////////////
    // input:
    // purpose: Tạo ra fragment MainFragment
    // output: Trả về đối tượng MainFragment
    /////////////
    public static MainFragment newInstance() {
        MainFragment f = new MainFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    /*/////////////
    // input: Chuỗi trên tab và vị trí tab trong tablayout
    // purpose: Tạo ra fragment MainFragment
    // output: Trả về đối tượng MainFragment
    /////////////
    public static MainFragment newInstance(String tabtextdisplay,int positionoftab) {
        MainFragment f = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("positiontab",positionoftab);
        args.putString("tabtextdisplay",tabtextdisplay);
        f.setArguments(args);
        return f;
    }*/


    ////////////////////////////////////////////////////////////////////////////////////////


    /////////////
    // input:
    // purpose: Lấy hình ảnh
    // output: Trả về danh sách hình ảnh
    /////////////
    private List<Image> getimage() {
        return DbAdapter.getInstace(context).getListImageInType();
    }

    /////////////
    // input: Chuỗi id của thành phố
    // purpose: Lấy danh sách các quận huyện thị xã
    // output: Trả về danh sách các quận huyện thị xã
    /////////////
    private List<District> getTinh(String query) {
        return DbAdapter.getInstace(context).getTinhTheoTP(query);
    }


    ////////////////////////////////////////////////////////////////////////////////////////

    /////////////
    // input:
    // purpose: Xác định các view trong layout
    // output:
    /////////////
    private void identify(){
        bottomview = (BottomNavigationViewEx)getActivity().findViewById(R.id.bottom_navigation);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabcontent = (LinearLayout)view.findViewById(R.id.tabcontainer);
        listviewcontainrestaurant = (ListView)view.findViewById(R.id.listviewcontainrestaurant);
        swipe_refresh_layout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);

    }

    /////////////
    // input: Chuỗi tab1,tab2,tab3 là chuỗi hiện thị trên tab và là tag để xác định fragment
    // purpose: Tạo tab cho tablayout và các fragment chứa các mục lựa chọn khi người dùng mở tab
    // output:
    /////////////
    private void createContent(String tab1,String tab2,String tab3){
        List<String> ls = Utility.convertArraytolistString(GlobalVariable.item_text_listfragment);
        List<Integer> lsint = Utility.convertArraytolistInteger(GlobalVariable.item_for_listfragment);
        createTabForTabLayout(tab1,tab2,tab3);
        createFragmentTabContent(tab1, myListFragment.newInstance(ls,lsint,true,GlobalVariable.oldpositiontabinlistview1click));
        createFragmentTabContent(tab2,myListFragment.newInstance1(getimage(),false,GlobalVariable.oldpositiontabinlistview2click));
        createFragmentTabContent(tab3, myListFragmentfordiadiem.newInstance1(getTinh(GlobalVariable.currentcityid),GlobalVariable.oldpositiontabinlistview3click));
    }




    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mainfragment, container, false);
        this.savedInstanceState = savedInstanceState;
        context = view.getContext();
        identify();
        lstRestaurant = new ArrayList<Restaurant>();
        createContent(GlobalVariable.tabtext1display,GlobalVariable.tabtext2display,GlobalVariable.tabtext3display);
        //RecreateFragmentContent(GlobalVariable.oldpositiontabinlistview1click);
        CreateViewPagerForImage(context);
        listviewcontainrestaurant.addHeaderView(CreateViewRecycleView(getContext()));
        try {
            offSet = 0;
            restadapter = new RestaurantAdapter(this.getActivity(),R.layout.layout_for_quanan_container,lstRestaurant);
            new CallRestTaskgetList(new AsyncResponse() {
                @Override
                public void processFinish(Object object,View v) {
                    restadapter.addAll((List<Restaurant>)object);
                }
            },null).execute(EnumTaskOption.getQuanAnMoiNhatTheoDistorCityorStreetLoadMore,GlobalVariable.currenttyperestid,GlobalVariable.currentcityid,1,offSet);

        } catch (Exception e) {
            e.printStackTrace();
        }


        listviewcontainrestaurant.setAdapter(restadapter);
        restadapter.setOnLoadMore(this);
        swipe_refresh_layout.setOnRefreshListener(this);
        tabcontent.setVisibility(View.GONE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabcontent.setVisibility(View.VISIBLE);
                bottomview.setVisibility(View.GONE);
                swipe_refresh_layout.setVisibility(View.GONE);
                handleTabOpen(tab.getPosition());
                changeTextTabColor(tab,R.color.colorNavbar);
                changeBackGroundTabColor(tab,R.color.colorofbackgroundfragment);
                selectTabHandle(tab.getPosition(),tab.getText().toString());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTextTabColor(tab,R.color.tabunselectedtextcolor);
                changeBackGroundTabColor(tab,R.drawable.shape_tab_of_tablayout);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tabLayout.getChildAt(tab.getPosition()).setBackgroundResource(R.drawable.tab_indicator);
                if(tabcontent.getVisibility()==View.GONE){
                    closeTab(tab);
                    handleTabOpen(tab.getPosition());
                }else{
                    openTab(tab);

                }

            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    //Create UI for mainfragment
    /////////////
    // input: Chuỗi hiễn thị trên tab
    // purpose: Tạo các tab cho tablayout
    // output:
    /////////////
    private void createTabForTabLayout(String tab1,String tab2,String tab3){
        tabLayout.addTab(tabLayout.newTab().setText(tab1));
        tabLayout.addTab(tabLayout.newTab().setText(tab2));
        tabLayout.addTab(tabLayout.newTab().setText(tab3));
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = createTabView(getActivity(),tab.getText().toString(),R.drawable.ic_keyboard_arrow_down_black_24dp);
            TextView tabTextView = (TextView) view.findViewById(R.id.tabsText);
            tabTextView.setText(tab.getText());
            tab.setCustomView(view);
        }

    }

    /////////////
    // input: Chuỗi tag xác định fragment và đối tượng fragment muốn khởi tạo
    // purpose: Tạo fragment khi không tìm được tag của fragment
    // output:
    /////////////
    private void createFragmentTabContent(String tag,Fragment frag){
        Fragment fragment = getChildFragmentManager().findFragmentByTag(tag);
        if(fragment!=null){
            replaceFragment(tag);
        }else{
            getChildFragmentManager().beginTransaction().add(R.id.tabcontainer,frag,tag).commit();

        }

    }

    /////////////
    // input: context, chuỗi hiển thị, icon hiển thị trong tab
    // purpose: Tạo view cho tab từ layout layoutfortab
    // output: Trả về view cho tab trong tablayout
    /////////////
    private static View createTabView(final Context context, final String text, int drawableiconint) {

        View view = LayoutInflater.from(context).inflate(R.layout.layoutfortab, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        ImageView im = (ImageView)view.findViewById(R.id.icon);
        tv.setText(text);
        im.setImageResource(drawableiconint);
        return view;

    }


    //Handle UI for mainfragment

    /////////////
    // input: tab mà người dùng click,giá trị màu thay đổi
    // purpose: Đổi màu chữ của tab khi người dùng click tab
    // output:
    /////////////
    private void changeTextTabColor(TabLayout.Tab tab,int colorid){
        if(tab!=null){
            TextView t = (TextView)tab.getCustomView().findViewById(R.id.tabsText);
            t.setTextColor(getResources().getColor(colorid));
        }
    }

    /////////////
    // input: tab mà người dùng click,giá trị màu thay đổi
    // purpose: Đổi màu background của tab khi người dùng click
    // output:
    /////////////
    private void changeBackGroundTabColor(TabLayout.Tab tab,int drawableid){
        if(tab!=null){
            LinearLayout t = (LinearLayout) tab.getCustomView().findViewById(R.id.tabsLayout);
            t.setBackground(getResources().getDrawable(drawableid));
        }
    }

    /////////////
    // input: tab trong tablayout mà người dùng click vào
    // purpose: Xử lý khi người dùng mở tab
    // output:
    /////////////
    private void openTab(TabLayout.Tab tab){
        tabcontent.setVisibility(View.GONE);
        swipe_refresh_layout.setVisibility(View.VISIBLE);
        bottomview.setVisibility(View.VISIBLE);
        changeBackGroundTabColor(tab,R.drawable.shape_tab_of_tablayout);
        changeTextTabColor(tab,R.color.tabunselectedtextcolor);
        selectTabHandle(tab.getPosition(),tab.getText().toString());
    }

    /////////////
    // input: Vị trí tab cần xử lý
    // purpose: xử lý đóng mở các list fragment
    // output:
    /////////////
    private void handleTabOpen(int position){
        if(tabcontent!=null){
            if(tabcontent.getChildCount()>0){
                if(tabcontent.getChildAt(position)!=null){
                    for(int i=0;i<tabcontent.getChildCount();i++){
                        tabcontent.getChildAt(i).setVisibility(View.GONE);
                    }
                    tabcontent.getChildAt(position).setVisibility(View.VISIBLE);
                }else{
                    System.out.println("tab child at "+position+" null");
                }
            }else{
                System.out.println("khong co tab content");
            }
        }else{
            System.out.println("tabcontent null");
        }
    }


    /////////////
    // input: tab trong tablayout mà người dùng click vào
    // purpose: Xử lý khi người dùng đóng tab
    // output:
    /////////////
    private void closeTab(TabLayout.Tab tab){
        tabcontent.setVisibility(View.VISIBLE);
        swipe_refresh_layout.setVisibility(View.GONE);
        bottomview.setVisibility(View.GONE);
        changeTextTabColor(tab,R.color.colorNavbar);
        changeBackGroundTabColor(tab,R.color.colorofbackgroundfragment);
    }

    /////////////
    // input: Chuỗi tag xác định fragment
    // purpose: Ẩn hiện fragment
    // output:
    /////////////
    private void replaceFragment(String tag){
        if(tabcontent.getVisibility()==View.GONE){

        }else{
            System.out.println("replace fragment");
            FragmentManager fragmentManager = getChildFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment!=null){
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                for(int i=0;i<fragmentManager.getFragments().size();i++){
                    fragmentTransaction.hide(fragmentManager.getFragments().get(i));
                }
                fragmentTransaction.show(fragment).addToBackStack(null).commit();
            }
        }
    }


    /////////////
    // input: vị trí tab và tag xác định fragment
    // purpose: Lựa chọn fragment để hiển thị
    // output:
    /////////////
    private void selectTabHandle(int position,String tagofFragment){
        switch(position){
            case 0:
                replaceFragment(tagofFragment);
                break;
            case 1:
                replaceFragment(tagofFragment);
                break;
            case 2:
                replaceFragment(tagofFragment);
                break;
        }
    }
    Button next;
    Button prev;
    int[] mResource = {R.drawable.banner1,R.drawable.banner2};


    //Create Image Slider


    /////////////
    // input: context
    // purpose: Tạo slide ảnh
    // output: trả về view cho image slider
    /////////////
    private void CreateViewPagerForImage(final Context context){
        //View v = View.inflate(context,R.layout.layout_for_imageslider, null);
        View v = getLayoutInflater(this.savedInstanceState).inflate(R.layout.layout_for_imageslider,listviewcontainrestaurant,false);
        prev = (Button)v.findViewById(R.id.button_previous);
        next = (Button)v.findViewById(R.id.button_next);
        next.setBackgroundResource(R.drawable.ic_circle_viewflipper_enable);
        prev.setBackgroundResource(R.drawable.ic_circle_viewflipper);
        ViewPager vpg = (ViewPager)v.findViewById(R.id.pager);
        PagerAdapterForImageSlider adapter = new PagerAdapterForImageSlider(getActivity(),mResource);
        vpg.setAdapter(adapter);
        listviewcontainrestaurant.addHeaderView(v);
        vpg.setOnPageChangeListener(this);
    }


    ///////////////////
    // input :
    // purpose : Tạo recycleview để add vào header listview
    // output : View
    /////////////////////
    private View CreateViewRecycleView(final Context context){
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setPadding(10,10,10,10);
        GridLayoutManager lLayout = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(view.getContext(), GlobalVariable.item_image_for_recycleview,GlobalVariable.text_for_item_recycleview);
        recyclerView.setAdapter(rcAdapter);
        return recyclerView;
    }



    /////////////
    // input: context,tên người bình luận,bình luận,hình ảnh đại diện của người bình luận
    // purpose: Tạo view cho mỗi bình luận về nhà hàng
    // output:View cho comment
    /////////////






    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    public static int offSet = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    ///////////////////
    // input :
    // purpose : Lấy các nhà hàng từ server
    // output :
    /////////////////////
    public void getRestaurant(){
        try{
            if(!restadapter.loadingMore){
                if(GlobalVariable.currentstateExpandableclick==EnumItemDiaDiemClick.ThanhPhoClick){
                    System.out.println("load rest thanh pho "+GlobalVariable.currentcityid);
                    CallAsynctaskGetRestaurant(EnumTaskOption.getQuanAnMoiNhatTheoDistorCityorStreetLoadMore,GlobalVariable.currenttyperestid,GlobalVariable.currentcityid,1);
                }else if(GlobalVariable.currentstateExpandableclick==EnumItemDiaDiemClick.QuanClick){
                    System.out.println("load rest quan");
                    CallAsynctaskGetRestaurant(EnumTaskOption.getQuanAnMoiNhatTheoDistorCityorStreetLoadMore,GlobalVariable.currenttyperestid,GlobalVariable.currentdistid,0);
                }else if(GlobalVariable.currentstateExpandableclick ==EnumItemDiaDiemClick.DuongClick){
                    System.out.println("load rest duong");
                    CallAsynctaskGetRestaurant(EnumTaskOption.getQuanAnMoiNhatTheoDistorCityorStreetLoadMore,GlobalVariable.currenttyperestid,GlobalVariable.currentstreetid,2);
                }else if(GlobalVariable.currentstateExpandableclick ==EnumItemDiaDiemClick.NoneClick){

                }else{

                }
                swipe_refresh_layout.setRefreshing(false);

            }else{
                swipe_refresh_layout.setRefreshing(false);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///////////////////
    // input :
    // purpose : Reset lại toàn bộ khi người dùng click các tiêu chí khác
    // output :
    /////////////////////
    public void Reset(){
        offSet = 0;
        restadapter.loadingMore=false;
        restadapter.DeleteAll();
        getRestaurant();
    }

    ///////////////////
    // input :
    // purpose : Lấy dữ liệu từ server
    // output :
    /////////////////////
    public void CallAsynctaskGetRestaurant(Object... params){
        try{
            new CallRestTaskgetList(new AsyncResponse() {
                @Override
                public void processFinish(Object object,View v) {
                    if(((List<Restaurant>)object).size()==0)
                        restadapter.loadingMore=true;
                    else{
                        restadapter.addAll((List<Restaurant>)object);
                    }


                }
            },null).execute(params[0],params[1],params[2],params[3],offSet);

        }catch (Exception e){

        }
    }



    //UnHandle Event When Receive
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void notify(Object data) {
    }
    @Override
    public void notifytoparentofDiaDiemFragment(Object data) {
    }
    @Override
    public void update() {
    }

    //Handle Event
    //Event of Image Slider
    /////////////
    // input: Vị trí hiện tại của image slider
    // purpose: Xử lý Sự kiện hai button hình tròn nằm trong slide ảnh khi người dùng khi slide ảnh
    // output:
    /////////////
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                next.setBackground(getResources().getDrawable(R.drawable.ic_circle_viewflipper_enable));
                prev.setBackground(getResources().getDrawable(R.drawable.ic_circle_viewflipper));
                break;
            case 1:
                next.setBackground(getResources().getDrawable(R.drawable.ic_circle_viewflipper));
                prev.setBackground(getResources().getDrawable(R.drawable.ic_circle_viewflipper_enable));
                break;
        }
    }


    //Event of ChildFragment To ParentFragment Fire When click on ChildFragment
    /////////////
    // input: dữ liệu mà người dùng chọn khi click vào fragment
    // purpose: Xử lý khi người click lên các list fragment
    // output:
    /////////////
    @Override
    public void notifytoparent(Object data) {
        if(((InfoFragmentClick)data).hideFragment){
            openTab(tabLayout.getTabAt(((InfoFragmentClick)data).positionoftab));
        }else{
            TextView tv = (TextView)tabLayout.getTabAt(((InfoFragmentClick)data).positionoftab).getCustomView().findViewById(R.id.tabsText);
            tv.setText(((InfoFragmentClick)data).tabtextdisplay);
            openTab(tabLayout.getTabAt(((InfoFragmentClick)data).positionoftab));
            if(((InfoFragmentClick)data).positionoftab==0){
                GlobalVariable.oldpositiontabinlistview1click = (((InfoFragmentClick)data).positionlistview);
                GlobalVariable.tabtext1display = (((InfoFragmentClick)data).tabtextdisplay);
            }else if(((InfoFragmentClick)data).positionoftab==1){
                GlobalVariable.currenttyperestid = (((InfoFragmentClick)data).tagtypeid);
                GlobalVariable.tabtext2display = (((InfoFragmentClick)data).tabtextdisplay);
                GlobalVariable.oldpositiontabinlistview2click = (((InfoFragmentClick)data).positionlistview);
            }else if(((InfoFragmentClick)data).positionoftab==2){
                if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.ThanhPhoClick){
                    GlobalVariable.currentcityid = ((InfoFragmentClick)data).tagcityid;
                    GlobalVariable.currentstateExpandableclick = EnumItemDiaDiemClick.ThanhPhoClick;
                    GlobalVariable.groupposition = -1;
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.QuanClick){
                    GlobalVariable.currentdistid = ((InfoFragmentClick)data).tagdistid;
                    GlobalVariable.currentstateExpandableclick = EnumItemDiaDiemClick.QuanClick;
                    GlobalVariable.groupposition = -1;
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.DuongClick){
                    System.out.println(" mynotifiparent hihi");
                    GlobalVariable.currentstreetid = ((InfoFragmentClick)data).streetid;
                    GlobalVariable.currentstateExpandableclick = EnumItemDiaDiemClick.DuongClick;
                    GlobalVariable.groupposition = ((InfoFragmentClick)data).groupposition;

                }else{
                    System.out.println("infofragment click itemclick = NoneClick Alert ");
                }

                GlobalVariable.oldpositiontabinlistview3click = (((InfoFragmentClick)data).positionlistview);
                GlobalVariable.tabtext3display = (((InfoFragmentClick)data).tabtextdisplay);

            }
            Reset();
        }
    }

    ///////////////////
    // input :
    // purpose : Thể hiện dữ liệu lên màn hình
    // output :
    /////////////////////
    @Override
    public void onRefresh() {
        swipe_refresh_layout.setRefreshing(true);
        getRestaurant();
    }


    @Override
    public void loadmore() {
        getRestaurant();
    }
}
