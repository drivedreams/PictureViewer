package com.haihai.picture.viewer.tmp;

import com.haihai.picture.viewer.R;
 
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoObjectFragment extends Fragment {
	public static final String ARG_OBJECT = "object";
	TypedArray tableNames ; 
    public TypedArray getTableNames() {
		return tableNames;
	}
    
	public DemoObjectFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_collection_object, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        
        tableNames = rootView.getContext().obtainStyledAttributes(R.styleable.TableNames);  
        
        tableNames.recycle(); 
         
        
        return rootView;
    }
}
