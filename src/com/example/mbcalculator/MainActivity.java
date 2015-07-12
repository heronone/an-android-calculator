package com.example.mbcalculator;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private normal_touch_Fragment touch;
	static String s="";   //s=""才是空字符串，s=null不是空字符串
	static Double d1=0.0;         //尝试使用泛型？？？？？
	static Double d2=0.0;
	static Vector<String> s0=new Vector<String>();  
	static Vector<String> s1=new Vector<String>();
	static Vector<Integer> position=new Vector<Integer>();
	static int i;
	static int j;
	static int p1;
	static int p2;
	static int presize;
	static int clearflag=0;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		setDefaultFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId())
		{
		case R.id.action_search:
			return true;
		case R.id.action_settings:
			return true;
		default:
		    return super.onOptionsItemSelected(item);
		}
	}
	
	public void setDefaultFragment()
	{
		FragmentManager fm = getFragmentManager();  
        FragmentTransaction transaction = fm.beginTransaction();  
        touch = new normal_touch_Fragment();  
        transaction.add(R.id.id_content, touch);  
        transaction.commit();  
	}

    public static String cal(Vector<String> s0)
    {
    	try {
			//  第一个左括号与其之后的第一个右括号未必成一对，但第一个右括号与其反向寻找的第一个左括号必定成一对
			//  括号优先级最高，先算括号
			if(s0.indexOf(")")!=-1)    //第一个右括号
			{
				for(i=s0.lastIndexOf("(", s0.indexOf(")"))+1;i<s0.indexOf(")");i++)
				{
					s1.add(s0.elementAt(i));     //s1用于计算括号中的内容，计算完毕后清空，不保存
				}
				p2=s0.lastIndexOf("(", s0.indexOf(")"));
				presize=s1.size();
				try {
					//此处把左括号替换成了运算结果,cal(s1)中运算涉及到i，换一个标记来标记原左括号的位置
					s0.set(s0.lastIndexOf("(", s0.indexOf(")")),cal(s1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					s0.set(0, "error");
					e.printStackTrace();
				}     
				s1.clear();     //s1每使用完一次必须清空一次，否则会有上次的结果存留
				for(p1=p2+1;p1<s0.size()-(presize+2-1);p1++)
				{
					s0.set(p1, s0.get(p1+presize+2-1));
				}
				s0.setSize(s0.size()-(presize+2-1));
				cal(s0);
			}
			
			//sin,cos,tan,sqrt,ln,lg优先级低于括号，高于加减乘除
			else if(s0.indexOf("sin")!=-1||s0.indexOf("cos")!=-1||s0.indexOf("tan")!=-1||s0.indexOf("sqrt")!=-1||s0.indexOf("ln")!=-1||s0.indexOf("lg")!=-1)
			{
				position.add(s0.lastIndexOf("sin"));
				position.add(s0.lastIndexOf("cos"));
				position.add(s0.lastIndexOf("tan"));
				position.add(s0.lastIndexOf("sqrt"));
				position.add(s0.lastIndexOf("ln"));
				position.add(s0.lastIndexOf("lg"));
				for(i=0,j=0;j<position.size();j++)
				{
					if(position.get(j)>i)
						i=position.get(j);
				}
				position.clear();
				if(s0.elementAt(i).equals("sin"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.sin(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
					}
				else if(s0.elementAt(i).equals("cos"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.cos(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
				}
				else if(s0.elementAt(i).equals("tan"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.tan(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
				}
				else if(s0.elementAt(i).equals("ln"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.log(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
				}
				else if(s0.elementAt(i).equals("lg"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.log10(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
				}
				else if(s0.elementAt(i).equals("sqrt"))
				{
					if(s0.elementAt(i+1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i+1));
					d1=Math.sqrt(d1);
					s0.set(i,d1.toString());   //把sinx的结果替换掉sin
					for(i=i+1;i<s0.size()-1;i++)
					{
						s0.set(i, s0.get(i+1));
					}
					s0.setSize(s0.size()-1);                 
					cal(s0);
				}
			}
			
			//乘方优先级高于乘除，低于以上
			else if(s0.indexOf("^")!=-1)
			{
				i=s0.indexOf("^");
				if(s0.elementAt(i-1).equals("e"))
					d1=Math.E;
				else if(s0.elementAt(i-1).equals("π"))
					d1=Math.PI;
				else
					d1=Double.valueOf(s0.elementAt(i-1));
				if(s0.elementAt(i+1).equals("e"))
					d2=Math.E;
				else if(s0.elementAt(i+1).equals("π"))
					d2=Math.PI;
				else
					d2=Double.valueOf(s0.elementAt(i+1));
				d1=Math.pow(d1, d2);
				s0.set(i-1,d1.toString());
				for(;i<s0.size()-2;i++)
				{
					s0.set(i, s0.get(i+2));
				}
				s0.setSize(s0.size()-2);                 
				cal(s0);
			}
			
			
			
			//算乘除
			else if(s0.indexOf("*")!=-1||s0.indexOf("/")!=-1)
			{
				if(s0.indexOf("*")==-1)
					i=s0.indexOf("/");
				else if(s0.indexOf("/")==-1)
					i=s0.indexOf("*");
				else
					i=Math.min(s0.indexOf("*"),s0.indexOf("/"));
				if(s0.elementAt(i)=="*")
				{
					if(s0.elementAt(i-1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i-1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i-1));
					if(s0.elementAt(i+1).equals("e"))
						d2=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d2=Math.PI;
					else
						d2=Double.valueOf(s0.elementAt(i+1));
					d1*=d2;
					s0.set(i-1,d1.toString());
					for(;i<s0.size()-2;i++)
					{
						s0.set(i, s0.get(i+2));
					}
					s0.setSize(s0.size()-2);                 
					cal(s0);
				}
				else if(s0.elementAt(i)=="/")           //if...else if 只执行其中之一,判断满足后不再继续判断；if...if每条分支都判断
				{
					if(s0.elementAt(i-1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i-1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i-1));
					if(s0.elementAt(i+1).equals("e"))
						d2=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d2=Math.PI;
					else
						d2=Double.valueOf(s0.elementAt(i+1));
					d1/=d2;
					s0.set(i-1,d1.toString());
					for(;i<s0.size()-2;i++)           //处理数组时，注意运算后的数组长度缩减和相应项的替换
					{
						s0.set(i, s0.get(i+2));
					}
					s0.setSize(s0.size()-2);
					cal(s0);
				}
			}
			
			//算加减
			else if(s0.indexOf("+")!=-1||s0.indexOf("-")!=-1)
			{
				if(s0.indexOf("-")==-1)
					i=s0.indexOf("+");
				else if(s0.indexOf("+")==-1)
					i=s0.indexOf("-");
				else
					i=Math.min(s0.indexOf("+"),s0.indexOf("-"));
				if(s0.elementAt(i)=="+")
				{
					if(s0.elementAt(i-1).equals("e"))
						d1=Math.E;
					else if(s0.elementAt(i-1).equals("π"))
						d1=Math.PI;
					else
						d1=Double.valueOf(s0.elementAt(i-1));
					if(s0.elementAt(i+1).equals("e"))
						d2=Math.E;
					else if(s0.elementAt(i+1).equals("π"))
						d2=Math.PI;
					else
						d2=Double.valueOf(s0.elementAt(i+1));
					d1+=d2;
					s0.set(i-1,d1.toString());
					for(;i<s0.size()-2;i++)
					{
						s0.set(i, s0.get(i+2));
					}
					s0.setSize(s0.size()-2);
					cal(s0);
				}
				else if(s0.elementAt(i)=="-")
				{
					if(i!=0)
					{
						if(s0.elementAt(i-1).equals("e"))
							d1=Math.E;
						else if(s0.elementAt(i-1).equals("π"))
							d1=Math.PI;
						else
							d1=Double.valueOf(s0.elementAt(i-1));
						if(s0.elementAt(i+1).equals("e"))
							d2=Math.E;
						else if(s0.elementAt(i+1).equals("π"))
							d2=Math.PI;
						else
							d2=Double.valueOf(s0.elementAt(i+1));
						d1-=d2;
						s0.set(i-1,d1.toString());
						for(;i<s0.size()-2;i++)
						{
							s0.set(i, s0.get(i+2));
						}
						s0.setSize(s0.size()-2);
						cal(s0);
					}
					if(i==0)
					{
						d1=0.0;
						if(s0.elementAt(i+1).equals("e"))
							d2=Math.E;
						else if(s0.elementAt(i+1).equals("π"))
							d2=Math.PI;
						else
							d2=Double.valueOf(s0.elementAt(i+1));
						d1-=d2;
						s0.set(i,d1.toString());
						for(i=i+1;i<s0.size()-1;i++)
						{
							s0.set(i, s0.get(i+1));
						}
						s0.setSize(s0.size()-1);
						cal(s0);
					}
				}
			}
			
			return s0.get(0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			s0.set(0, "error");
			e.printStackTrace();
			return "error";
		}
    	
    }
    
    public static int countelement(String s,Vector<String> s0)
    {
    	int i,j=0;
    	for(i=0;i<s0.size();i++)
    	{
    		if(s0.get(i).equals(s))
    		{
    			j++;
    		}
    	}
    	return j;
    }
    
    
    
    //ln lg sin cos tan sqrt等
    public static void add_operator(String s)
	{
		if(MainActivity.s!="")
    	{
    		MainActivity.s0.add(MainActivity.s);
    		MainActivity.s0.add("*");
    		MainActivity.s0.add(s);
    		showFragment.showText.append("*");
    		showFragment.showText.append(s);
    		MainActivity.s="";
    	}
    	else if(MainActivity.s0.size()==0)
    	{
    		MainActivity.s0.add(s);
    		showFragment.showText.append(s);
    	}
    	else if(MainActivity.s==""&&MainActivity.s0.get(MainActivity.s0.size()-1).equals(")"))
    	{
    		MainActivity.s0.add("*");
    		MainActivity.s0.add(s);
    		showFragment.showText.append("*");
    		showFragment.showText.append(s);
    	}
    	else if(MainActivity.s==""&&MainActivity.s0.get(MainActivity.s0.size()-1).equals("e"))
    	{
    		MainActivity.s0.add("*");
    		MainActivity.s0.add(s);
    		showFragment.showText.append("*");
    		showFragment.showText.append(s);
    	}
    	else if(MainActivity.s==""&&MainActivity.s0.get(MainActivity.s0.size()-1).equals("π"))
    	{
    		MainActivity.s0.add("*");
    		MainActivity.s0.add(s);
    		showFragment.showText.append("*");
    		showFragment.showText.append(s);
    	}
    	else if(MainActivity.s=="")
    	{
    		MainActivity.s0.add(s);
    		showFragment.showText.append(s);
    	}
	}
	
    
    //加减乘除乘方
	public static void another_add_operator(String s)
	{
		if(MainActivity.s0.size()!=0||MainActivity.s!="")
    	{
	    	if(MainActivity.s!="")
	    	{
	    		MainActivity.s0.add(MainActivity.s);
	    		MainActivity.s0.add(s);
	    		MainActivity.s="";
	    		showFragment.showText.append(s);
	    	}
	    	else if(MainActivity.s=="")
	    	{
	    		//其他符号同理，复制过去的，注释不改了
	    		//如果前面是右括号，可以添加加号
	    		if(MainActivity.s0.get(MainActivity.s0.size()-1).equals(")"))
	    		{
	    			MainActivity.s0.add(s);
	    			showFragment.showText.append(s);
	    		}
	    		//如果是PI和E，可以添加加号
	    		else if(MainActivity.s0.get(MainActivity.s0.size()-1).equals("e"))
	    		{
	    			MainActivity.s0.add(s);
	    			showFragment.showText.append(s);
	    		}
	    		else if(MainActivity.s0.get(MainActivity.s0.size()-1).equals("π"))
	    		{
	    			MainActivity.s0.add(s);
	    			showFragment.showText.append(s);
	    		}
	    		//如果不是括号，而是加减乘除等，将其替换为加号
	    		else
	    		{
	    			MainActivity.s0.remove(MainActivity.s0.size()-1);  //remove会改变容器大小,remove后size减小1
		    		for(i=0;i<MainActivity.s0.size();i++)
		    		{
		    			MainActivity.s+=MainActivity.s0.get(i);
		    		}
		    		showFragment.showText.setText(MainActivity.s);
		    		MainActivity.s="";
		    		MainActivity.s0.add(s);
		    		showFragment.showText.append(s);
	    		}
	    	}
    	}
	}
    
	public static void add_number(String s)
	{
		if(MainActivity.s0.size()==0)
		{
			MainActivity.s+=s;
			showFragment.showText.append(s);
		}
		else if(MainActivity.s0.size()!=0)
		{
			if(!MainActivity.s0.get(MainActivity.s0.size()-1).equals("e")&&
			   !MainActivity.s0.get(MainActivity.s0.size()-1).equals("π")&&
			   !MainActivity.s0.get(MainActivity.s0.size()-1).equals(")"))
			{
				MainActivity.s+=s;
				showFragment.showText.append(s);
			}
			
		}
	}
	
}
