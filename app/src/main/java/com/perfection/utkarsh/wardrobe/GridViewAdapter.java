package com.perfection.utkarsh.wardrobe;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.perfection.utkarsh.wardrobe.R;

public class GridViewAdapter extends BaseAdapter
{
    private Activity activity;
    private String[] filepath;
    private static LayoutInflater inflater = null;
    Bitmap bmp = null;

    public GridViewAdapter (Activity a, String[] fpath)
    {
        activity = a;
        filepath = fpath;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount ()
    {
        return filepath.length;
    }

    public Object getItem (int position)
    {
        return position;
    }

    public long getItemId (int position)
    {
        return position;
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.grid_item, null);
        ImageView image = (ImageView)vi.findViewById(R.id.image);
        TextView text = (TextView) vi.findViewById(R.id.itemtext);
        int targetWidth = 500;
        int targetHeight = 500;
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath[position], bmpOptions);
        int currHeight = bmpOptions.outHeight;
        int currWidth = bmpOptions.outWidth;
        int sampleSize = 1;
        System.out.println("PRINTING CURRENT HEIGHT AND WIDTH");
        System.out.printf("%d %d %d %d", targetHeight, currHeight, targetWidth, currWidth);
        if (currHeight > targetHeight || currWidth > targetWidth)
        {
            if (currWidth > currHeight)
                sampleSize = Math.round((float)currHeight
                        / (float)targetHeight);
            else
                sampleSize = Math.round((float)currWidth
                        / (float)targetWidth);
        }
        bmpOptions.inSampleSize = sampleSize;
        bmpOptions.inJustDecodeBounds = false;
//        try {
            bmp = BitmapFactory.decodeFile(filepath[position], bmpOptions);
            image.setImageBitmap(bmp);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            bmp = null;
//        } catch (NullPointerException n) {
//            bmp = null;
//        }
        text.setText(filepath[position].toString());
        return vi;
    }
}