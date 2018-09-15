package com.eltendawy.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView expression,result;
    HorizontalScrollView container;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expression=findViewById(R.id.math_expression);
        container=findViewById(R.id.math_expression_container);
        container.fullScroll(HorizontalScrollView.FOCUS_FORWARD);
        ViewTreeObserver observer= container.getViewTreeObserver();
        if(observer.isAlive())
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    container.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            });
        result=findViewById(R.id.result);
    }

    public void onClick(View view)
    {

        int id=view.getId();
        StringBuilder current=new StringBuilder(expression.getText().toString());
        String input=((TextView)view).getText().toString();
        switch (input)
        {
            case "mc":
                if(toast!=null)
                toast.cancel();;
                toast=Toast.makeText(this,"not implmented",Toast.LENGTH_SHORT);
                toast.show();
                break;
            case "m+":
                if(toast!=null)
                    toast.cancel();;
                toast=Toast.makeText(this,"not implmented",Toast.LENGTH_SHORT);
                toast.show();
                break;
            case "m\\u2212":
                if(toast!=null)
                    toast.cancel();;
                toast=Toast.makeText(this,"not implmented",Toast.LENGTH_SHORT);
                toast.show();
                break;
            case "mr":
                if(toast!=null)
                    toast.cancel();;
                toast=Toast.makeText(this,"not implmented",Toast.LENGTH_SHORT);
                toast.show();
                break;
            case "C":
                current.delete(0,current.length());
                break;
            case "del":
                if(current.length()>0)current.deleteCharAt(current.length()-1);
                break;

            case "=":
                try {
                    result.setText(String.valueOf(EqulOpreation.getresult(current.toString().replace("÷","/").replace("\u2212","-"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                current.append(input);
                break;
        }
        expression.setText(current.toString());
    }
}
