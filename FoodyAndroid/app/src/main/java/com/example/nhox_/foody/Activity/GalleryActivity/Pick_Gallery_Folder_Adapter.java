package com.example.nhox_.foody.Activity.GalleryActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.R;

import java.util.List;

import Model.FolderImage;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class Pick_Gallery_Folder_Adapter extends BaseAdapter {
    Context context;
    List<FolderImage> lstfolderimage;
    public Pick_Gallery_Folder_Adapter(Context context, List<FolderImage> lstfolderimage) {
        this.context = context;
        this.lstfolderimage = lstfolderimage;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return getCount() == 0 ? 1 : getCount();
    }
    @Override
    public int getCount() {
        return lstfolderimage.size();
    }
    @Override
    public Object getItem(int position) {
        return lstfolderimage.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.gallery_folder_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FolderImage folder = this.lstfolderimage.get(position);

        holder.text_view_name_folder.setText(folder.getFolder());

        Glide.with(context).load("file://" + folder.getImageInFolder().get(0).getPath())
                .into(holder.image_view_sample);

        //holder.text_view_num_of_file.setText(item.getImagePath().size()+"");
        return convertView;
    }
    class ViewHolder {
        ImageView image_view_sample;
        TextView text_view_name_folder;
        // TextView text_view_num_of_file;
        View v;
        public ViewHolder(View v) {

            this.v = v;
            image_view_sample = (ImageView) v.findViewById(R.id.image_view_sample);
            text_view_name_folder = (TextView) v.findViewById(R.id.text_view_name_folder);
            //text_view_num_of_file=(TextView)v.findViewById(R.id.text_view_num_of_file);
        }
    }
}
