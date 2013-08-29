package com.haihai.picture.viewer.tmp;

import com.haihai.picture.viewer.R;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
	private TypedArray tableNames ; 

	public DemoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
        
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DemoObjectFragment();
       
        Bundle args = new Bundle();
        // Our object is just an integer :-P
       // args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	switch(position){
    	case 0: return "Welcome";
    	case 1: return "Categories";
    	case 2: return "About";
    	default:return "Welcome";
    	}
    }

}
