package com.example.mbcalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class showFragment extends Fragment implements OnClickListener {

	extra_touch_Fragment extra = new extra_touch_Fragment();
	normal_touch_Fragment normal = new normal_touch_Fragment();
	int i,tag = 0;
	static EditText showText = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_show, container, false);
		showText = (EditText) view.findViewById(R.id.fragmentTextErea);
		Button button17 = (Button) view.findViewById(R.id.operator_clear);
		Button button18 = (Button) view.findViewById(R.id.operator_backspace);
		Button button20 = (Button) view.findViewById(R.id.operator_more);


		button17.setOnClickListener(this); // 有空删掉MyClickListener,fragment的所有动作都写在fragment内
		button18.setOnClickListener(this);
		button20.setOnClickListener(this);    //不能由listener监听，否则transaction.commit()会报错？？？

		// Inflate the layout for this fragment
		return view;
	}

	@Override
	public void onClick(View v) 
	{
		
		if(MainActivity.clearflag==1)
		{
			showFragment.showText.append("\r\n"+"\r\n");
			MainActivity.s="";
	    	MainActivity.d1=0.0;MainActivity.d2=0.0;
	    	MainActivity.s0.clear();
	    	MainActivity.s1.clear();
			MainActivity.clearflag=0;
		}
		
		switch (v.getId()) 
		{
		case R.id.operator_clear:
	    	showFragment.showText.setText("");
	    	MainActivity.s="";
	    	MainActivity.d1=0.0;MainActivity.d2=0.0;
	    	MainActivity.s0.clear();
	    	MainActivity.s1.clear();
	    	break;
	    case R.id.operator_backspace:
	    	if(MainActivity.s!=""&&MainActivity.s.length()!=1)    //s非空，则是如355这种形式，删掉最后一个字符变成35
	    	{
	    		MainActivity.s=MainActivity.s.substring(0, MainActivity.s.length()-1);
	    		showFragment.showText.setText(showFragment.showText.getText().toString().substring(0,showFragment.showText.getText().length()-1));  //删掉最后一个字符，显示
	    	}
	    	else if(MainActivity.s.length()==1)
	    	{
	    		MainActivity.s="";
	    		showFragment.showText.setText(showFragment.showText.getText().toString().substring(0,showFragment.showText.getText().length()-1));
	    	}
	    	else if(MainActivity.s==""&&MainActivity.s0.size()!=0)   //s空，要删掉的是操作符，如加减乘除括号等
	    	{
	    		MainActivity.s0.remove(MainActivity.s0.size()-1);  //remove会改变容器大小,remove后size减小1
	    		for(i=0;i<MainActivity.s0.size();i++)
	    		{
	    			MainActivity.s+=MainActivity.s0.get(i);
	    		}
	    		showFragment.showText.setText(MainActivity.s);
	    		MainActivity.s="";
	    	}
	    	break;
		case R.id.operator_more:
			FragmentManager fm = getFragmentManager(); // 要使用getFragmentManager,必须extends
														// Activity
			FragmentTransaction transaction = fm.beginTransaction();
			// TODO Auto-generated method stub
			switch (tag) 
			{
			case 0:
				if (extra == null) {
					extra = new extra_touch_Fragment();
				}
				transaction.replace(R.id.id_content, extra); // replace操作不能放在MyClickListener中？
				transaction.commit();
				tag = 1;
				((Button) v).setText("<");
				break;
			case 1:
				if (normal == null) {
					normal = new normal_touch_Fragment();
				}
				transaction.replace(R.id.id_content, normal);
				transaction.commit();
				tag = 0;
				((Button) v).setText(">");
				break;
			}
			break;
		}
	}
}