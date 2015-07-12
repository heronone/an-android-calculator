package com.example.mbcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class extra_touch_Fragment extends Fragment implements OnClickListener {

	int i;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view=inflater.inflate(R.layout.fragment_extra_touch, container, false);
		Button button15=(Button) view.findViewById(R.id.operator_left_bracket);
		Button button16=(Button) view.findViewById(R.id.operator_right_bracket);
		Button button21=(Button) view.findViewById(R.id.operator_sin);
		Button button22=(Button) view.findViewById(R.id.operator_cos);
		Button button23=(Button) view.findViewById(R.id.operator_tan);
		Button button24=(Button) view.findViewById(R.id.operator_pow);
		Button button25=(Button) view.findViewById(R.id.operator_sqrt);
		Button button26=(Button) view.findViewById(R.id.operator_ln);
		Button button27=(Button) view.findViewById(R.id.operator_lg);
		Button button28=(Button) view.findViewById(R.id.operator_pi);
		Button button29=(Button) view.findViewById(R.id.operator_e);
		Button button30=(Button) view.findViewById(R.id.operator_equal);
		
		
		button15.setOnClickListener(this);
		button16.setOnClickListener(this);
		button21.setOnClickListener(this);
		button22.setOnClickListener(this);
		button23.setOnClickListener(this);
		button24.setOnClickListener(this);
		button25.setOnClickListener(this);
		button26.setOnClickListener(this);
		button27.setOnClickListener(this);
		button28.setOnClickListener(this);
		button29.setOnClickListener(this);
		button30.setOnClickListener(this);
	        // Inflate the layout for this fragment
	    return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(MainActivity.clearflag==1)
		{
			showFragment.showText.append("\r\n"+"\r\n");
			MainActivity.s="";
	    	MainActivity.d1=0.0;MainActivity.d2=0.0;
	    	MainActivity.s0.clear();
	    	MainActivity.s1.clear();
			MainActivity.clearflag=0;
		}
		
		switch(v.getId())
		{
		case R.id.operator_left_bracket:
	    	//s0.add(s);    括号都用在加减乘除后面，s已经被清空，不用再加s.若加s，将在Vector<String>中产生一项空的字符串，影响计算
			if(MainActivity.s!="")
	    	{
	    		MainActivity.s0.add(MainActivity.s);
	    		MainActivity.s0.add("*");
	    		showFragment.showText.append("*");
	    	}
	    	MainActivity.s0.add("(");
		    MainActivity.s="";
		    showFragment.showText.append("(");
	    	break;
	    case R.id.operator_right_bracket:
	    	if(MainActivity.countelement("(",MainActivity.s0)<=MainActivity.countelement(")",MainActivity.s0))
	    		break;
	    	else
	    	{
	    		if(MainActivity.s!="")
		    		MainActivity.s0.add(MainActivity.s);
		    	MainActivity.s0.add(")");
			    MainActivity.s="";
			    showFragment.showText.append(")");
	    	}
	    	break;
	    case R.id.operator_sin:
	    	MainActivity.add_operator("sin");
	    	break;
	    case R.id.operator_cos:
	    	MainActivity.add_operator("cos");
	    	break;
	    case R.id.operator_tan:
	    	MainActivity.add_operator("tan");
	    	break;
	    case R.id.operator_ln:
	    	MainActivity.add_operator("ln");
	    	break;
	    case R.id.operator_lg:
	    	MainActivity.add_operator("lg");
	    	break;
	    case R.id.operator_sqrt:
	    	MainActivity.add_operator("sqrt");
	    	break;
	    case R.id.operator_pow:
	    	MainActivity.another_add_operator("^");
		    break;
	    case R.id.operator_e:
	    	MainActivity.add_operator("e");
	    	break;
	    case R.id.operator_pi:
	    	MainActivity.add_operator("π");
	    	break;
	    case R.id.operator_equal:
	    	if(MainActivity.s!="")
	    		MainActivity.s0.add(MainActivity.s);
	    	MainActivity.s0.add("=");
	    	MainActivity.s="";
	    	if(MainActivity.countelement("(",MainActivity.s0)==MainActivity.countelement(")",MainActivity.s0))
	    	{
	    		MainActivity.cal(MainActivity.s0);
	    		//showFragment.showText.append("\r\n"+"="+MainActivity.d1.toString());
		    	showFragment.showText.append("\r\n"+"="+MainActivity.s0.get(0));
		    	MainActivity.clearflag=1;
	    	}
	    	else
	    		Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
	    	break;
		}
	}
	
}

