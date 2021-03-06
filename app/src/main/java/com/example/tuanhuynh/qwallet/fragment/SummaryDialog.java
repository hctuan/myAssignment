package com.example.tuanhuynh.qwallet.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.AddNewActivity;
import com.example.tuanhuynh.qwallet.MainActivity;
import com.example.tuanhuynh.qwallet.R;
import com.example.tuanhuynh.qwallet.ReportActivity;

import java.util.ArrayList;

/**
 * Created by tuan.huynh on 6/22/2016.
 */
public class SummaryDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_dialog_layout,container,false);
        TextView tvIncom = (TextView)view.findViewById(R.id.tv_income);
        TextView tvExpense = (TextView)view.findViewById(R.id.tv_expense);
        TextView tvBalance = (TextView)view.findViewById(R.id.tv_balance);
        final long valueIncome = getArguments().getLong("income");
        final long valueExpense = getArguments().getLong("expense");
        String expense = String.valueOf(valueExpense);
        String income = String.valueOf(valueIncome);
        long valueBalance = valueIncome-valueExpense;

        tvBalance.setText(convertToMoneyString(String.valueOf(valueBalance))+" VND");
        tvIncom.setText(convertToMoneyString(income)+" VND");
        tvExpense.setText(convertToMoneyString(expense)+" VND");
        final String month = getArguments().getString("title");
        TextView tvTitle = (TextView)view.findViewById(R.id.tv_month);
        tvTitle.setText(getNameMonth(month));

        Button btnCloseDialog = (Button)view.findViewById(R.id.btn_close_dialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valueIncome==0&&valueExpense==0){

                } else {
                    Intent in = new Intent(getContext(), ReportActivity.class);
                    in.putExtra("month", month);
                    in.putExtra("year", getArguments().getString("year"));
                    startActivity(in);
                }
            }
        });
        return view;
    }

    String getNameMonth(String s){
        switch (s){
            case "01": return "January";
            case "02": return "February";
            case "03": return "March";
            case "04": return "April";
            case "05": return "May";
            case "06": return "June";
            case "07": return "July";
            case "08": return "August";
            case "09": return "September";
            case "10": return "October";
            case "11": return "November";
            case "12": return "December";
            default: return "Month";
        }
    }

    String convertToMoneyString(String strInput){

        ArrayList<String> list = new ArrayList<String>();
        for(int i =0; i < strInput.length(); i++)
            list.add(strInput.substring(i, i+1));

        String listString = "";

        if(list.size()==0){
            return listString+"0";
        }
        int temp=2-list.size()%3;
        if(list.get(0).equals("-")){
            temp -=3;
        }
        if(list.size()%3==0){
            temp -=3;
        }
        for (String s : list)
        {
            temp++;
            if(temp==3){
                listString += ",";
                listString += s;
                temp=0;
            }else listString += s;
        }
        return listString;
    }
}
