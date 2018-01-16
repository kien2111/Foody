package UtilPackage;

/**
 * Created by nhox_ on 2/4/2017.
 */

/////////////
// input:
// purpose: Lưu thông tin khi người dùng click lên các list fragment
// output:
/////////////
public class InfoFragmentClick{

    public InfoFragmentClick(String tagoffragment
            ,int positionlistview
            ,boolean hideFragment
            ,int positionoftab
            ,String tabtextdisplay
            ,String tagdistid
            ,String tagcityid
            ,String tagtypeid
            ,int streetid
            ,EnumItemDiaDiemClick itemclick
            ,int groupposition){
        this.hideFragment = hideFragment;
        this.tagoffragment = tagoffragment;
        this.positionlistview = positionlistview;
        this.positionoftab = positionoftab;
        this.tabtextdisplay = tabtextdisplay;
        this.tagcityid = tagcityid;
        this.tagdistid = tagdistid;
        this.tagtypeid = tagtypeid;
        this.streetid = streetid;
        this.itemclick = itemclick;
        this.groupposition=groupposition;
    }
    public EnumItemDiaDiemClick itemclick;
    public int streetid;
    public String tagdistid;
    public String tagcityid;
    public String tagtypeid;
    public String tabtextdisplay;
    public int positionoftab;
    public boolean hideFragment;
    public int positionlistview;
    public String tagoffragment;
    public int groupposition;

    @Override
    public String toString() {
        return "[EnumItemDiaDiemClick]="+this.itemclick+" [streetid]="+this.streetid+" [tagdistid]="+this.tagdistid
                +" [tagcityid]="+this.tagcityid+" [tagtypeid]="+this.tagtypeid+" [tabtextdisplay]="+this.tabtextdisplay
                +" [positionoftab]="+this.positionoftab+" [hideFragment]="+this.hideFragment+" [positionlistview]="+this.positionlistview
                +" [tagoffragment]="+this.tagoffragment+" [groupposition]="+this.groupposition;
    }
}
