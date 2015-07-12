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


		button17.setOnClickListener(this); // �п�ɾ��MyClickListener,fragment�����ж�����д��fragment��
		button18.setOnClickListener(this);
		button20.setOnClickListener(this);    //������listener����������transaction.commit()�ᱨ������

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
	    	if(MainActivity.s!=""&&MainActivity.s.length()!=1)    //s�ǿգ�������355������ʽ��ɾ�����һ���ַ����35
	    	{
	    		MainActivity.s=MainActivity.s.substring(0, MainActivity.s.length()-1);
	    		showFragment.showText.setText(showFragment.showText.getText().toString().substring(0,showFragment.showText.getText().length()-1));  //ɾ�����һ���ַ�����ʾ
	    	}
	    	else if(MainActivity.s.length()==1)
	    	{
	    		MainActivity.s="";
	    		showFragment.showText.setText(showFragment.showText.getText().toString().substring(0,showFragment.showText.getText().length()-1));
	    	}
	    	else if(MainActivity.s==""&&MainActivity.s0.size()!=0)   //s�գ�Ҫɾ�����ǲ���������Ӽ��˳����ŵ�
	    	{
	    		MainActivity.s0.remove(MainActivity.s0.size()-1);  //remove��ı�������С,remove��size��С1
	    		for(i=0;i<MainActivity.s0.size();i++)
	    		{
	    			MainActivity.s+=MainActivity.s0.get(i);
	    		}
	    		showFragment.showText.setText(MainActivity.s);
	    		MainActivity.s="";
	    	}
	    	break;
		case R.id.operator_more:
			FragmentManager fm = getFragmentManager(); // Ҫʹ��getFragmentManager,����extends
														// Activity
			FragmentTransaction transaction = fm.beginTransaction();
			// TODO Auto-generated method stub
			switch (tag) 
			{
			case 0:
				if (extra == null) {
					extra = new extra_touch_Fragment();
				}
				transaction.replace(R.id.id_content, extra); // replace�������ܷ���MyClickListener�У�
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