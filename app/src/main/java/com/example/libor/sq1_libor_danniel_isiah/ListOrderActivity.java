package com.example.libor.sq1_libor_danniel_isiah;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<OrderDetailClass> orderList;
    TextView lblTotal;
    Intent home;

    TeaListDataAdapter teaAdapter;
    MilkTeaClass teaDetail;
    OrderDetailClass detailClass;
    int teaOrderNo, addOnOrderNo, orderSize, orderSugar;
    int quant;
    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        home = new Intent(ListOrderActivity.this, MainActivity.class);

        teaDetail = new MilkTeaClass();
        detailClass = new OrderDetailClass();
        orderList = (ArrayList<OrderDetailClass>) getIntent().getSerializableExtra("orderList");

        lblTotal = (TextView) findViewById(R.id.txtTotalPrice);

        listView = (ListView) findViewById(R.id.listView);
        teaAdapter = new TeaListDataAdapter(getBaseContext(), R.layout.custom_listview_layout, orderList);
        listView.setAdapter(teaAdapter);

        //compute total
        for (OrderDetailClass orbobj: orderList) {
            int teaOrderNo = orbobj.getTeaNo();
            int addOnOrderNo = orbobj.getAddOn();
            int orderSize = orbobj.getSize();
            int quant = orbobj.getQty();

            totalPrice += ((teaDetail.getTeaPrice(teaOrderNo,orderSize) + teaDetail.getAddOnsPrice(addOnOrderNo)) * quant);
            lblTotal.setText("Total Amount: ₱ " + String.format("%,.2f", totalPrice));
        }
    }
    private class TeaListDataAdapter extends ArrayAdapter<OrderDetailClass> {

        private ArrayList<OrderDetailClass> teaList;
        int layoutResID;

        public TeaListDataAdapter(Context context, int resourceLayoutID,
                                  ArrayList<OrderDetailClass> listObj) {
            super(context, resourceLayoutID, listObj);
            // TODO Auto-generated constructor stub
            this.layoutResID = resourceLayoutID;
            this.teaList = new ArrayList<OrderDetailClass>();
            this.teaList.addAll(listObj);
        }

        //adding new set of arraylist (custom)
        public void addAll(ArrayList<OrderDetailClass> obj) {
            // TODO Auto-generated method stub
            teaList.clear();
            teaList.addAll(obj);
        }

        @Override
        public void remove(OrderDetailClass object) {
            // TODO Auto-generated method stub
            super.remove(object);
            teaList.remove(object);

            totalPrice = 0.00;
            //compute total
            for (OrderDetailClass orbobj : teaList) {
                teaOrderNo = orbobj.getTeaNo();
                addOnOrderNo = orbobj.getAddOn();

                quant = orbobj.getQty();

                totalPrice += ((teaDetail.getTeaPrice(teaOrderNo,orderSize) + teaDetail.getAddOnsPrice(addOnOrderNo)) * quant);
                lblTotal.setText("Total Amount: ₱ " + String.format("%,.2f", totalPrice));
            }
        }

        private class ViewHolder {

            TextView lblName, lblPrice, lblOrderType, lblQty, lblSugarSize, lblAddOn;
            ImageView imgtea, imgaddon;
            Button btndelete;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder = null;
            View view = convertView;

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(layoutResID, null);

                holder = new ViewHolder();
                holder.lblName = (TextView) view.findViewById(R.id.txtOrderName);
                holder.lblAddOn = (TextView) view.findViewById(R.id.txtOrderAddOn);
                holder.lblPrice = (TextView) view.findViewById(R.id.txtOrderPrice);
                holder.lblOrderType = (TextView) view.findViewById(R.id.txtOrderType);
                holder.lblSugarSize = (TextView) view.findViewById(R.id.txtOrderSugarSize);
                holder.lblQty = (TextView) view.findViewById(R.id.txtOrderQty);
                holder.imgtea = (ImageView) view.findViewById(R.id.teaOrderImg);
                holder.imgaddon = (ImageView) view.findViewById(R.id.addOnOrderImg);
                holder.btndelete = (Button) view.findViewById(R.id.btnDelete);
                view.setTag(holder);

                holder.btndelete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Button btnTag = (Button) v;
                        OrderDetailClass iconObj = (OrderDetailClass) btnTag.getTag();
                        teaAdapter.remove(iconObj);
                        teaAdapter.notifyDataSetChanged();

                        totalPrice = (totalPrice * 2) - ((teaDetail.getTeaPrice(teaOrderNo,orderSize) + teaDetail.getAddOnsPrice(addOnOrderNo)) * quant);
                        lblTotal.setText("Total Amount: ₱ " + String.format("%,.2f", totalPrice));

                        if (totalPrice <= 0.00)
                        {
                            lblTotal.setText("Total Amount: ₱ 0.00");
                            AlertDialog.Builder alert = new AlertDialog.Builder(ListOrderActivity.this);
                            alert.setTitle("Continue shopping?");
                            alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(home);
                                }
                            }).create();
                            alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to execute after dialog closed
                                }
                            }).create();
                            alert.show();
                        }
                    }
                });
            }else{
                holder = (ViewHolder) view.getTag();

            }

            try {
                OrderDetailClass teaObj = teaList.get(position);
                int teaOrderNo = teaObj.getTeaNo();
                String PaymentSTR = "";
                if (teaObj.isOrder()) {
                    PaymentSTR = "Dine - in";
                }else {
                    PaymentSTR = "Take - out";

                }

                orderSize = teaObj.getSize();
                orderSugar = teaObj.getSugar();
                addOnOrderNo = teaObj.getAddOn();

                holder.lblName.setText(teaDetail.getTeaFlavor(teaOrderNo));
                holder.lblPrice.setText("Price/Tea: ₱ " +  String.format("%,.2f", teaDetail.getTeaPrice(teaOrderNo,orderSize)));
                holder.lblAddOn.setText("Add On: " + teaDetail.getTeaAddOns(addOnOrderNo) + " - ₱ "
                        + String.format("%,.2f", teaDetail.getAddOnsPrice(addOnOrderNo)));
                holder.lblSugarSize.setText("Sugar: " + (teaDetail.getSugarLevel(orderSugar) + " Sugar | " +
                        "Size: " + teaDetail.getTeaSize(orderSize)));
                holder.lblOrderType.setText("Order: " +  PaymentSTR);
                holder.lblQty.setText("No. of Order(s): " + teaObj.getQty());
                holder.imgtea.setBackgroundResource(teaDetail.getTeaImg(teaOrderNo));
                holder.imgaddon.setBackgroundResource(teaDetail.getAddOnsImg(addOnOrderNo));
                holder.btndelete.setTag(teaObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return view;
        }
    }
}