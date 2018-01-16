
package com.example.nhox_.foody.Fragment;





import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.nhox_.foody.Adapter.PagerAdapterForImageSlider;
import com.example.nhox_.foody.Adapter.RecyclerViewAdapter;
import com.example.nhox_.foody.CustomView.CustomScrollView;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.myListFragmentfordiadiem1;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import DbConnect.DataBaseHelper;
import Model.District;
import Model.Image;
import Model.Meal;
import UtilPackage.AsyncResponse;
import UtilPackage.DbAdapter;
import UtilPackage.EnumItemDiaDiemClick;
import UtilPackage.EnumTaskOption;
import UtilPackage.GlobalVariable;
import UtilPackage.INotifier;
import UtilPackage.INotifierFromDiaDiemFragment;
import UtilPackage.InfoFragmentClick;
import UtilPackage.NotifierToParent1;
import UtilPackage.ScrollViewListener;
import UtilPackage.TaskCallRest.CallRestTaskgetImageQuanAn;
import UtilPackage.TaskCallRest.CallRestTaskgetList;
import UtilPackage.Utility;


public class MainFragment1 extends Fragment implements ScrollViewListener, ViewPager.OnPageChangeListener, INotifier, INotifierFromDiaDiemFragment, NotifierToParent1 {
    LinearLayout imageContainer;
    TabLayout tabLayout;
    Context context;
    RecyclerView rView;
    RecyclerView maincontainer1;
    CustomScrollView scrollView;
    View view;
    LinearLayout tabcontent;
    private GridLayoutManager lLayout;
    private GridLayoutManager gridlayoutformonan;
    RecycleViewAdapterForMonAn radaptermonan;

    BottomNavigationViewEx bottomview=null;

    /////////////
    // input:
    // purpose: Lấy hình ảnh
    // output: Trả về danh sách hình ảnh
    /////////////
    private List<Image> getimage() {
        return DbAdapter.getInstace(context).getListImageInType();

    }

    /*/////////////
    // input:
    // purpose: Lấy món ăn mới nhất theo thành phố
    // output: Trả về danh sách món ăn mới nhất
    /////////////
    private List<Meal> getmeal() {
        return new DbAdapter(context).getMonAnMoiNhatTheoCity(GlobalVariable.currenttyperestid_1,GlobalVariable.currentcityid_1);
    }*/

    /////////////
    // input: Chuỗi id của thành phố
    // purpose: Lấy danh sách các quận huyện thị xã
    // output: Trả về danh sách các quận huyện thị xã
    /////////////
    private List<District> getTinh(String query) {
        return DbAdapter.getInstace(context).getTinhTheoTP(query);
    }

    /////////////
    // input:
    // purpose: Tạo ra fragment MainFragment1
    // output: Trả về đối tượng MainFragment1
    /////////////
    public static MainFragment1 newInstance() {
        MainFragment1 f = new MainFragment1();
        return f;
    }


    ////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        getimage();
        getTinh("tphcm");
    }

    /////////////
    // input: Chuỗi tab1,tab2,tab3 là chuỗi hiện thị trên tab và là tag để xác định fragment
    // purpose: Tạo tab cho tablayout và các fragment chứa các mục lựa chọn khi người dùng mở tab
    // output:
    /////////////
    private void createContent(String tab1,String tab2,String tab3){
        List<String> ls = Utility.convertArraytolistString(GlobalVariable.text_for_angi_fragmentmoinhat);
        createTabForTabLayout(tab1,tab2,tab3);
        createFragmentTabContent(tab1, myListFragment1.newInstance(ls,null,true,GlobalVariable.oldpositiontabinlistview1click_1));
        createFragmentTabContent(tab2,myListFragment1.newInstance1(getimage(),false,GlobalVariable.oldpositiontabinlistview2click_1));
        createFragmentTabContent(tab3, myListFragmentfordiadiem1.newInstance1(getTinh(GlobalVariable.currentcityid_1),GlobalVariable.oldpositiontabinlistview3click_1,1));
    }
    /////////////
    // input:
    // purpose: Xác định các view trong layout
    // output:
    /////////////
    private void Identify(){
        scrollView = (CustomScrollView) view.findViewById(R.id.myscrollview1);
        bottomview = (BottomNavigationViewEx)getActivity().findViewById(R.id.bottom_navigation);
        imageContainer = (LinearLayout)view.findViewById(R.id.imagecontainer1);
        rView = (RecyclerView)view.findViewById(R.id.recycler_view1);
        maincontainer1 = (RecyclerView)view.findViewById(R.id.maincontainer1);
        tabcontent = (LinearLayout)view.findViewById(R.id.tabcontainer1);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout1);

        scrollView.setScrollViewListener(this);
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    List<Meal> lstmeal = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mainfragment1, container, false);
        List<Meal> meallist =null;
        try {
            offsetmonan = 1;
            meallist = (List<Meal>)new CallRestTaskgetList().execute(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentcityid_1,1,offsetmonan).get();
            if(meallist!=null){
                lstmeal.addAll(meallist);
                offsetmonan = lstmeal.get(lstmeal.size()-1).getMealid();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        Identify();
        imageContainer.addView(CreateViewPagerForImage(getActivity()));
        lLayout = new GridLayoutManager(view.getContext(), 2);
        gridlayoutformonan = new GridLayoutManager(view.getContext(),2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(view.getContext(), GlobalVariable.item_image_for_recycleview,GlobalVariable.text_for_item_recycleview);
        rView.setAdapter(rcAdapter);
        maincontainer1.setLayoutManager(gridlayoutformonan);


        radaptermonan = new RecycleViewAdapterForMonAn();
        maincontainer1.setAdapter(radaptermonan);




        createContent(GlobalVariable.tabtext1display_1,GlobalVariable.tabtext2display_1,GlobalVariable.tabtext3display_1);
        tabcontent.setVisibility(View.GONE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabcontent.setVisibility(View.VISIBLE);
                imageContainer.setVisibility(View.GONE);
                rView.setVisibility(View.GONE);
                maincontainer1.setVisibility(View.GONE);
                bottomview.setVisibility(View.GONE);
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

    /////////////
    // input: tab mà người dùng click,giá trị màu thay đổi
    // purpose: Đổi màu chữ của tab khi người dùng click tab
    // output:
    /////////////
    private void changeTextTabColor(TabLayout.Tab tab,int colorid){
        TextView t = (TextView)tab.getCustomView().findViewById(R.id.tabsText);
        t.setTextColor(getResources().getColor(colorid));
    }

    /////////////
    // input: tab mà người dùng click,giá trị màu thay đổi
    // purpose: Đổi màu background của tab khi người dùng click
    // output:
    /////////////
    private void changeBackGroundTabColor(TabLayout.Tab tab,int drawableid){
        LinearLayout t = (LinearLayout) tab.getCustomView().findViewById(R.id.tabsLayout);
        t.setBackground(getResources().getDrawable(drawableid));
    }

    //Unhandle
    @Override
    public void notify(Object data) {
        FragmentManager fragmentManager = getChildFragmentManager();
        Fragment someOtherNestFrag = (Fragment) fragmentManager.findFragmentByTag(((InfoFragmentClick) data).tagoffragment);
        if(someOtherNestFrag != null &&((InfoFragmentClick)data).positionlistview!=-1 )
        {
        }else if(((InfoFragmentClick)data).positionlistview==-1 && ((InfoFragmentClick)data).hideFragment==true){
        }
    }


    /////////////
    // input: Chuỗi tag xác định fragment
    // purpose: Ẩn hiện fragment
    // output:
    /////////////
    private void replaceFragment(String tag){
        if(tabcontent!=null){
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
            getChildFragmentManager().beginTransaction().add(R.id.tabcontainer1,frag,tag).commit();
        }

    }


    /////////////
    // input: context
    // purpose: Tạo slide ảnh
    // output: trả về view cho image slider
    /////////////
    private View CreateViewPagerForImage(final Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.layout_for_imageslider, null);
        prev = (Button)v.findViewById(R.id.button_previous);
        next = (Button)v.findViewById(R.id.button_next);
        next.setBackgroundResource(R.drawable.ic_circle_viewflipper_enable);
        prev.setBackgroundResource(R.drawable.ic_circle_viewflipper);
        ViewPager vpg = (ViewPager)v.findViewById(R.id.pager);
        PagerAdapterForImageSlider adapter = new PagerAdapterForImageSlider(getActivity(),mResource);
        vpg.setAdapter(adapter);
        vpg.setOnPageChangeListener(this);
        return v;
    }
    Button next;
    Button prev;
    int[] mResource = {R.drawable.banner1,R.drawable.banner2};



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

    /////////////
    // input: tab trong tablayout mà người dùng click vào
    // purpose: Xử lý khi người dùng mở tab
    // output:
    /////////////
    private void openTab(TabLayout.Tab tab){
        tabcontent.setVisibility(View.GONE);
        bottomview.setVisibility(View.VISIBLE);
        maincontainer1.setVisibility(View.VISIBLE);
        rView.setVisibility(View.VISIBLE);
        imageContainer.setVisibility(View.VISIBLE);
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
                    maincontainer1.setVisibility(View.GONE);
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
        bottomview.setVisibility(View.GONE);
        maincontainer1.setVisibility(View.GONE);
        rView.setVisibility(View.GONE);
        imageContainer.setVisibility(View.GONE);
        changeTextTabColor(tab,R.color.colorNavbar);
        changeBackGroundTabColor(tab,R.color.colorofbackgroundfragment);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
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

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click chọn các tiêu chí khác
    // output :
    /////////////////////
    @Override
    public void notifytoparent(Object data) {

        if (((InfoFragmentClick) data).hideFragment) {
            openTab(tabLayout.getTabAt(((InfoFragmentClick) data).positionoftab));
        } else {
            TextView tv = (TextView) tabLayout.getTabAt(((InfoFragmentClick) data).positionoftab).getCustomView().findViewById(R.id.tabsText);
            tv.setText(((InfoFragmentClick) data).tabtextdisplay);
            openTab(tabLayout.getTabAt(((InfoFragmentClick) data).positionoftab));
            if (((InfoFragmentClick) data).positionoftab == 0) {
                GlobalVariable.oldpositiontabinlistview1click_1 = (((InfoFragmentClick) data).positionlistview);
                GlobalVariable.tabtext1display_1 = (((InfoFragmentClick) data).tabtextdisplay);
            } else if (((InfoFragmentClick) data).positionoftab == 1) {
                GlobalVariable.currenttyperestid_1 = (((InfoFragmentClick) data).tagtypeid);
                GlobalVariable.tabtext2display_1 = (((InfoFragmentClick) data).tabtextdisplay);
                GlobalVariable.oldpositiontabinlistview2click_1 = (((InfoFragmentClick) data).positionlistview);
            } else if (((InfoFragmentClick) data).positionoftab == 2) {
                if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.ThanhPhoClick){
                    GlobalVariable.currentcityid_1 = ((InfoFragmentClick)data).tagcityid;
                    GlobalVariable.currentstateExpandableclick_1 = EnumItemDiaDiemClick.ThanhPhoClick;
                    GlobalVariable.groupposition_1 = -1;
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.QuanClick){
                    GlobalVariable.currentdistid = ((InfoFragmentClick)data).tagdistid;
                    GlobalVariable.currentstateExpandableclick_1 = EnumItemDiaDiemClick.QuanClick;
                    GlobalVariable.groupposition_1 = -1;
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.DuongClick){
                    System.out.println(" mynotifiparent hihi");
                    GlobalVariable.currentstreetid = ((InfoFragmentClick)data).streetid;
                    GlobalVariable.currentstateExpandableclick_1 = EnumItemDiaDiemClick.DuongClick;
                    GlobalVariable.groupposition_1 = ((InfoFragmentClick)data).groupposition;

                }else{
                    System.out.println("infofragment click itemclick = NoneClick Alert ");
                }

                GlobalVariable.oldpositiontabinlistview3click_1 = (((InfoFragmentClick) data).positionlistview);
                GlobalVariable.tabtext3display_1 = (((InfoFragmentClick) data).tabtextdisplay);
            }
            reset();
            try {
                offsetmonan = 1;
                List<Meal> newmealist = null;
                if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.ThanhPhoClick){
                    newmealist =((List<Meal>)new CallRestTaskgetList().execute(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentcityid_1,1,offsetmonan).get());
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.QuanClick){
                    newmealist =((List<Meal>)new CallRestTaskgetList().execute(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentdistid_1,0,offsetmonan).get());
                }else if(((InfoFragmentClick)data).itemclick == EnumItemDiaDiemClick.DuongClick){
                    newmealist =((List<Meal>)new CallRestTaskgetList().execute(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentstreetid_1,2,offsetmonan).get());
                }
                if(newmealist!=null){
                    lstmeal.addAll(newmealist);
                    radaptermonan.notifyDataSetChanged();
                    offsetmonan = lstmeal.get(lstmeal.size()-1).getMealid();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }


    }


    @Override
    public void notifytoparentofDiaDiemFragment (Object data){}


    ///////////////////
    // input :
    // purpose : Reset lại toàn bộ và xóa trắng các món ăn
    // output :
    /////////////////////
    public void reset(){
        offsetmonan=1;
        this.lstmeal.clear();
        radaptermonan.setLoaded(false);
        radaptermonan.notifyDataSetChanged();
    }
    public static int offsetmonan =1;
    public int extrasizelist;
    ///////////////////
    // input :
    // purpose : Lấy dữ liệu từ server
    // output :
    /////////////////////
    public void CallAsynctaskGetMeal(Object... params){

        try{
            new CallRestTaskgetList(new AsyncResponse() {
                @Override
                public void processFinish(final Object object, View v) {
                    System.out.println("process finish size"+((List<Meal>)object).size());
                    if(((List<Meal>)object).size()<5){
                        System.out.println("set true");
                        radaptermonan.setDone(true);
                    }
                    /*maincontainer1.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });*/
                    lstmeal.remove(lstmeal.size()-1);
                    radaptermonan.notifyItemRemoved(lstmeal.size());

                    lstmeal.addAll(((List<Meal>)object));



                    extrasizelist = ((List<Meal>)object).size();
                    final int positionStart = lstmeal.size() + 1;
                    radaptermonan.notifyItemRangeInserted(positionStart,extrasizelist);
                    radaptermonan.setLoaded(false);
                    offsetmonan = lstmeal.get(lstmeal.size()-1).getMealid();

                }
            },null).execute(params[0],params[1],params[2],params[3],offsetmonan);
        }catch (Exception e){

        }
    }

///////////////////
    // input :
    // purpose : Lấy món ăn hiển thị lên màn hình
    // output :
    /////////////////////

    public void getMeal(){
        if(lstmeal.size()!=0 && lstmeal!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Remove loading item
                    if(GlobalVariable.currentstateExpandableclick_1==EnumItemDiaDiemClick.ThanhPhoClick){
                        System.out.println("load meal thanh pho");
                        CallAsynctaskGetMeal(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentcityid_1,1);
                    }else if(GlobalVariable.currentstateExpandableclick_1==EnumItemDiaDiemClick.QuanClick){
                        System.out.println("load meal quan");
                        CallAsynctaskGetMeal(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentdistid_1,0);
                    }else if(GlobalVariable.currentstateExpandableclick_1 ==EnumItemDiaDiemClick.DuongClick){
                        System.out.println("load meal duong");
                        CallAsynctaskGetMeal(EnumTaskOption.getMonAnMoiNhatTheoDistorCity,GlobalVariable.currenttyperestid_1,GlobalVariable.currentstreetid_1,2);
                    }else if(GlobalVariable.currentstateExpandableclick_1 ==EnumItemDiaDiemClick.NoneClick){

                    }else{

                    }


                }
            }, 1000);

        }
    }

    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng scroll xuống
    // output :
    /////////////////////
    @Override
    public void onScrollChanged(CustomScrollView scrollView, int x, int y, int oldx, int oldy) {
        View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
        if (diff == 0) {
            if (!radaptermonan.getLoaded() && !radaptermonan.isDone()) {
                radaptermonan.setLoaded(true);
                lstmeal.add(null);
                radaptermonan.notifyItemInserted(lstmeal.size()-1);
                getMeal();

            }

        }
    }


    class RecycleViewAdapterForMonAn extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public int l;
        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;

        public boolean isDone() {
            return isDone;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        private boolean isDone = false;
        private boolean isLoading = false;


        public RecycleViewAdapterForMonAn() {
            gridlayoutformonan.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch(radaptermonan.getItemViewType(position)){
                        case VIEW_TYPE_ITEM:
                            return 1;
                        case VIEW_TYPE_LOADING:
                            return 2;
                        default:
                            return -1;
                    }
                }
            });

        }


        ///////////////////
        // input :
        // purpose : Khởi tạo View holder cho Recycleview Adapter
        // output :
        /////////////////////
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_for_monan, parent, false);
                return new RecycleViewAdapterForMonAn.RecycleViewHolderForMonAn(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
                return new RecycleViewAdapterForMonAn.LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return lstmeal.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        ///////////////////
        // input :
        // purpose : Tạo giao diện cho các item chứ trong adapter
        // output :
        /////////////////////


        //Tạo giao diện cho item trong recycleview
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof RecycleViewAdapterForMonAn.RecycleViewHolderForMonAn) {
                final RecycleViewAdapterForMonAn.RecycleViewHolderForMonAn mealHolder = (RecycleViewAdapterForMonAn.RecycleViewHolderForMonAn) holder;
                mealHolder.diachiquan.setText(lstmeal.get(position).getRest().getAddress());
                mealHolder.tenmonan.setText(lstmeal.get(position).getNamemeal());
                mealHolder.tenquan.setText(lstmeal.get(position).getRest().getNamerest());
                mealHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                System.out.println("mark : "+GlobalVariable.resourceurl+lstmeal.get(position).getImage().getFilepath());
                Glide.with(context).load(GlobalVariable.resourceurl+lstmeal.get(position).getImage().getFilepath()).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .dontAnimate()
                        .into(new SimpleTarget<Bitmap>() {

                            @Override
                            public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                                mealHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                mealHolder.imageView.setImageBitmap(arg0);
                            }
                        });
            }else if (holder instanceof RecycleViewAdapterForMonAn.LoadingViewHolder) {
                RecycleViewAdapterForMonAn.LoadingViewHolder loadingViewHolder = (RecycleViewAdapterForMonAn.LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
                loadingViewHolder.progressBar.setVisibility(View.GONE);
            }

        }





        public boolean getLoaded(){
            return  this.isLoading;
        }

        public void setLoaded(boolean ONOFF) {
            isLoading= ONOFF? true:false;
        }
        //Trả về số lượng item trong recycleview
        @Override public int getItemCount() {
            return lstmeal == null ? 0 : lstmeal.size();
        }


// Define a view holder for Footer view



        class LoadingViewHolder extends RecyclerView.ViewHolder {
            ProgressBar progressBar;
            LoadingViewHolder(View itemView) {
                super(itemView);
                progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            }
        }
        class RecycleViewHolderForMonAn extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tenmonan;
            TextView tenquan;
            TextView diachiquan;
            ImageView imageView;
            RecycleViewHolderForMonAn(View itemView) {
                super(itemView);
                tenmonan = (TextView)itemView.findViewById(R.id.monan);
                tenquan = (TextView)itemView.findViewById(R.id.tenquan);
                diachiquan = (TextView)itemView.findViewById(R.id.diachi);
                imageView = (ImageView)itemView.findViewById(R.id.icon_button);
            }

            @Override
            public void onClick(View v) {

            }
        }
    }



}
