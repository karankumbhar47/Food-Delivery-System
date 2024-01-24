package com.example.swiggy_lite;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView numberText;
    ImageView plusIcon, minusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ImageView item_pic = findViewById(R.id.item_profile_imageView);

        int drawableId = getResources()
                .getIdentifier("food_image","drawable",ItemDetailsActivity.this.getPackageName());

        Glide.with(this)
                .load(drawableId)
                .into(item_pic);

        numberText = findViewById(R.id.numberText);
        plusIcon = findViewById(R.id.plusIcon);
        minusIcon = findViewById(R.id.minusIcon);


        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(numberText.getText().toString());
                if (currentValue < Integer.MAX_VALUE) {
                    animateNumberChange(++currentValue, true);

                }
            }
        });

        CardView add_button = findViewById(R.id.add_button_cardView);
        ConstraintLayout number_picker = findViewById(R.id.numberPicker_constrainLayout);
        minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(numberText.getText().toString());
                if (currentValue > 0) {
                    animateNumberChange(--currentValue,false);
                }
                else{
                    number_picker.setVisibility(number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                    add_button.setVisibility(add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                }
            }
        });



        RecyclerView recyclerView = findViewById(R.id.item_details_recyclerView);
        RecyclerView.Adapter adapter = new ItemDetailsAdapter(DummyData.foodItemList, ItemDetailsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ItemDetailsActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        add_button.setOnClickListener(v -> {
            add_button.setVisibility(add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
            number_picker.setVisibility(number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
        });
        /*TextView cart_button = findViewById(R.id.cart_button);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailsActivity.this,PlaceOrderActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void animateNumberChange(int value, boolean isIncrease) {
        String newValue = String.valueOf(value);
        ObjectAnimator oldAnimator = ObjectAnimator.ofFloat(numberText,
                "translationY",
                0f,
                isIncrease ? -numberText.getHeight() : numberText.getHeight()
        );

        oldAnimator.setDuration(150);
        oldAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                numberText.setText(newValue);
                //numberText.setTranslationY(2*numberText.getHeight());

                ObjectAnimator newAnimator = ObjectAnimator.ofFloat(numberText,
                        "translationY",
                        isIncrease ? numberText.getHeight() : -numberText.getHeight(),
                        0f
                );
                newAnimator.setDuration(150);
                newAnimator.start();
            }
        });
        oldAnimator.start();
    }

}