package com.example.mbcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class normal_touch_Fragment extends Fragment implements OnClickListener {

	int i;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_normal_touch, container, false);
		Button button0=(Button) view.findViewById(R.id.number0);
		Button button1=(Button) view.findViewById(R.id.number1);
		Button button2=(Button) view.findViewById(R.id.number2);
		Button button3=(Button) view.findViewById(R.id.number3);
		Button button4=(Button) view.findViewById(R.id.number4);
		Button button5=(Button) view.findViewById(R.id.number5);
		Button button6=(Button) view.findViewById(R.id.number6);
		Button button7=(Button) view.findViewById(R.id.number7);
		Button button8=(Button) view.findViewById(R.id.number8);
		Button button9=(Button) view.findViewById(R.id.number9);
		Button button10=(Button) view.findViewById(R.id.operator_add);
		Button button11=(Button) view.findViewById(R.id.operator_reduce);
		Button button12=(Button) view.findViewById(R.id.operator_multiply);
		Button button13=(Button) view.findViewById(R.id.operator_divide);
		Button button14=(Button) view.findViewById(R.id.operator_point);
		Button button19=(Button) view.findViewById(R.id.operator_equal);
		
		
		button0.setOnClickListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);
		button10.setOnClickListener(this);
		button11.setOnClickListener(this);
		button12.setOnClickListener(this);
		button13.setOnClickListener(this);
		button14.setOnClickListener(this);
		button19.setOnClickListener(this);
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
		case R.id.number0:
			MainActivity.add_number("0");
		    break;
		case R.id.number1:
			MainActivity.add_number("1");
			break;
		case R.id.number2:
			MainActivity.add_number("2");
		    break;
		case R.id.number3:
			MainActivity.add_number("3");
			break;
		case R.id.number4:
			MainActivity.add_number("4");
		    break;
		case R.id.number5:
			MainActivity.add_number("5");
			break;
		case R.id.number6:
			MainActivity.add_number("6");
		    break;
		case R.id.number7:
			MainActivity.add_number("7");
			break;
		case R.id.number8:
			MainActivity.add_number("8");
		    break;
		case R.id.number9:
			MainActivity.add_number("9");
			break;
		case R.id.operator_point:
			if(MainActivity.s.indexOf(".")!=-1)
				break;
			else if(MainActivity.s!="")
			{
				MainActivity.s+=".";
				showFragment.showText.append(".");
			}
			else
				break;
			break;
	    case R.id.operator_add:          
	    	MainActivity.another_add_operator("+");
	    	break;
	    case R.id.operator_reduce:
	    	MainActivity.another_add_operator("-");
		    break;
	    case R.id.operator_multiply:
	    	MainActivity.another_add_operator("*");
	    	break;
	    case R.id.operator_divide:
	    	MainActivity.another_add_operator("/");
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
