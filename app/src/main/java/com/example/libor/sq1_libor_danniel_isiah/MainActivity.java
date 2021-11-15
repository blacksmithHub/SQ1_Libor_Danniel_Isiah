package com.example.libor.sq1_libor_danniel_isiah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btnMilktea,btnAddons;
    SeekBar skbarSugarlevel,skbarSize;
    TextView txtMsg,txtFlavor,txtSugarlevel,txtSize,txtPrice,txtAddOns,txtAddonPrice,txtExPrice,txtNoOrder,txtTotalPrice;
    Button btnAddToCart, btnViewCart;
    ToggleButton orderType;

    ArrayList<OrderDetailClass> orderTea;
    MilkTeaClass teaDetails;

    public int imageteacntr=0;
    public int imageaddonscntr=0;
    public int progresssugar,progresssize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teaDetails = new MilkTeaClass();
        orderTea = new ArrayList<OrderDetailClass>();

        orderType=(ToggleButton)findViewById(R.id.toggleOrderType);
        btnAddToCart=(Button)findViewById(R.id.btnAddtoCart);
        btnViewCart=(Button)findViewById(R.id.btnView);
        btnMilktea=(ImageButton)findViewById(R.id.imageButtonTea);
        btnAddons=(ImageButton) findViewById(R.id.imageButtonAddons);
        txtSugarlevel=(TextView) findViewById(R.id.textSugarlevel);
        txtFlavor=(TextView)findViewById(R.id.textFlavor);
        txtSize=(TextView) findViewById(R.id.textSize);
        skbarSize=(SeekBar) findViewById(R.id.seekBarSize);
        skbarSugarlevel=(SeekBar) findViewById(R.id.seekBarSugarLevel);
        txtMsg=(TextView) findViewById(R.id.textViewMsg);
        txtPrice=(TextView) findViewById(R.id.textPrice);
        txtAddOns=(TextView) findViewById(R.id.textAddons) ;
        txtAddonPrice=(TextView) findViewById(R.id.textAddonPrice);
        txtExPrice=(TextView) findViewById(R.id.textExPrice);
        txtNoOrder=(EditText) findViewById(R.id.textNoOrder);
        txtTotalPrice=(TextView) findViewById(R.id.textTotalPrice);

        setOnDefault();

        skbarSugarlevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progresssugar=progress;
                refreshdisplay(imageteacntr,progresssugar,progresssize,imageaddonscntr);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progresssize=progress;
                refreshdisplay(imageteacntr,progresssugar,progresssize,imageaddonscntr);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnMilktea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageteacntr<4)
                    imageteacntr=imageteacntr+1;
                else
                    imageteacntr=0;

                skbarSugarlevel.setProgress(1);

                refreshdisplay(imageteacntr,progresssugar,progresssize,imageaddonscntr);

            }
        });

        btnAddons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageaddonscntr<6)
                    imageaddonscntr=imageaddonscntr+1;
                else
                    imageaddonscntr=0;

                refreshdisplay(imageteacntr,progresssugar,progresssize,imageaddonscntr);
            }
        });

        txtNoOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtNoOrder.length() != 0) {
                    btnAddToCart.setEnabled(true);
                    btnViewCart.setEnabled(true);
                } else {
                    if(txtNoOrder.getText().length() == 0) {
                        txtNoOrder.setError("This field cannot be blank");
                    }
                    btnAddToCart.setEnabled(false);
                    btnViewCart.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(txtNoOrder.getText().toString()) == 0) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Order Error");
                    alert.setMessage("Please add at least one quantity per order...");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed

                        }
                    }).create();
                    alert.show();

                }
                else
                {

                    OrderDetailClass newOrder = new OrderDetailClass();
                    newOrder.setTeaNo(imageteacntr);
                    newOrder.setAddOn(imageaddonscntr);
                    newOrder.setOrder(orderType.isChecked());
                    newOrder.setQty(Integer.parseInt(txtNoOrder.getText().toString()));
                    newOrder.setSize(progresssize);
                    newOrder.setSugar(progresssugar);

                    orderTea.add(newOrder);
                    setOnDefault();

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Add Order");
                    alert.setMessage("Please successfully added new order...");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                        }
                    }).create();
                    alert.show();
                }
            }
        });

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(orderTea.size() == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Order Error");
                        alert.setMessage("Please add at least one order in cart...");
                        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog closed

                            }
                        }).create();
                        alert.show();
                    } else {

                        Intent openAct = new Intent(MainActivity.this, ListOrderActivity.class);
                        openAct.putExtra("orderList", orderTea);
                        startActivity(openAct);
                    }
                }
                catch(Exception e)
                {

                }
            }

        });

    }

    public void refreshdisplay(int mimageteacnt,int msugar,int msize,int mimagecntraddons)
    {
        btnMilktea.setImageResource(teaDetails.getTeaImg(mimageteacnt));
        btnAddons.setImageResource(teaDetails.getAddOnsImg(mimagecntraddons));
        txtSugarlevel.setText(teaDetails.getSugarLevel(msugar));
        txtSize.setText(teaDetails.getTeaSize(msize));
        txtFlavor.setText("**"+teaDetails.getTeaFlavor(mimageteacnt)+"**"+teaDetails.getTeaSize(msize)
                +"***"+teaDetails.getSugarLevel(msugar)+" Sugar"+"**");
        txtPrice.setText(""+String.format("%.2f",teaDetails.getTeaPrice(mimageteacnt,msize)));
        txtAddonPrice.setText(""+String.format("%.2f",teaDetails.getAddOnsPrice(mimagecntraddons)));
        txtAddOns.setText("Add "+teaDetails.getTeaAddOns(mimagecntraddons));
        double extPrice=Double.parseDouble(txtPrice.getText().toString())+Double.parseDouble(txtAddonPrice.getText().toString());
        txtExPrice.setText(""+String.format("%.2f",extPrice));
        try {
            double totalPrice=Double.parseDouble(txtExPrice.getText().toString())*Double.parseDouble((txtNoOrder.getText().toString()));
            txtTotalPrice.setText(""+String.format("%,.2f",totalPrice));
        } catch (NumberFormatException e) {
            txtTotalPrice.setText(""+String.format("%,.2f",0.00));
        }

    }
    public void setOnDefault()
    {
        skbarSugarlevel.setProgress(1);
        skbarSize.setProgress(0);
        imageteacntr=(int)(Math.random()*4);
        progresssugar = 1;
        refreshdisplay(imageteacntr,progresssugar,0,0);
    }
}