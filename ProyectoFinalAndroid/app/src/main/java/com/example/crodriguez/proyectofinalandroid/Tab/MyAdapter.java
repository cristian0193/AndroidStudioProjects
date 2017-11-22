package com.example.crodriguez.proyectofinalandroid.Tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.crodriguez.proyectofinalandroid.R;
import com.example.crodriguez.proyectofinalandroid.fragment.ChatFragment;
import com.example.crodriguez.proyectofinalandroid.fragment.ExplorerFragment;
import com.example.crodriguez.proyectofinalandroid.fragment.FriendFragment;
import com.example.crodriguez.proyectofinalandroid.fragment.HomeFragment;


/**
 * Created by CRODRIGUEZ on 21/11/2017.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles ={"A","B","C","D"};
    int[] icon = new int[]{R.drawable.ic_home_white_36dp,R.drawable.ic_chat_white_36dp,R.drawable.ic_explore_white_36dp,R.drawable.ic_close_black_36dp};
    private int heightIcon;

    public MyAdapter(FragmentManager fm, Context c){
        super(fm);
        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int)(24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag= null;

        if(position ==0){
            frag = new HomeFragment();
        }else if(position == 1){
            frag = new ExplorerFragment();
        }else if(position == 2){
            frag = new ChatFragment();
        }else if(position == 3){
            frag = new FriendFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    public CharSequence getPageTitle(int position){
        Drawable d = mContext.getResources().getDrawable(icon[position]);
        d.setBounds(0,0,heightIcon,heightIcon);
        ImageSpan is = new ImageSpan(d);

        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

}