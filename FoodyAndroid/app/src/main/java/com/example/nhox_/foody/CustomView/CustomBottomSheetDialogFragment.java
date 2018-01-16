package com.example.nhox_.foody.CustomView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nhox_.foody.Activity.Insert_Quan_An_Activity;
import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 5/4/2017.
 */
/////////////
// input:
// purpose: cài đặt class CustomBottomSheetDialogFragment kế thừa từ BottomSheetDialogFragment trong main_menu_fragment
// output:
/////////////
public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    LinearLayout tao_dia_diem;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
    ///////////////////
    // input :
    // purpose : Tạo dialog
    // output :
    /////////////////////
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.dialog_modal, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        tao_dia_diem = (LinearLayout) contentView.findViewById(R.id.tao_dia_diem);
        tao_dia_diem.setOnClickListener(this);
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view của layout
    // output :
    /////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tao_dia_diem:
                Tao_Dia_Diem_HandleClick();
                break;
            default:
                break;
        }
    }
    ///////////////////
    // input :
    // purpose : Chuyển sang Activity Insert_Quan_An_...
    // output :
    /////////////////////
    public void Tao_Dia_Diem_HandleClick(){
        Intent i = new Intent(getActivity(),Insert_Quan_An_Activity.class);
        startActivityForResult(i,1);
    }
}
