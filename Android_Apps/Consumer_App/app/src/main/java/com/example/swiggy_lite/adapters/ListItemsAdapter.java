package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.R;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.FoodItem;
import com.openapi.deliveryApp.model.OrderItem;
import com.android.volley.toolbox.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.viewHolder> {
    List<FoodItem> searchList;
    Context context;
    String searchText = "";

    public void setList(List<FoodItem> updated_list, String searchText) {
        this.searchList = updated_list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public ListItemsAdapter(List<FoodItem> list, Context context) {
        searchList = list;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private static ListItemsAdapter.OnItemClickListener listener;
    public static void setOnItemClickListener(ListItemsAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    @NonNull
    @Override
    public ListItemsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemsAdapter.viewHolder holder, int position) {
        FoodItem singleItem = searchList.get(position);
        String name = singleItem.getName();

        int startIndex = name.toLowerCase().indexOf(searchText.toLowerCase());

        if (startIndex != -1) {
            SpannableString spannableString = new SpannableString(name);
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(boldSpan, startIndex, startIndex + searchText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.name.setText(spannableString);
        } else {
            holder.name.setText(name);
        }

        holder.itemType.setText("Dish");
        holder.name.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
        holder.itemType.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
        holder.picImage.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));

        Log.d("myTag", "thumbnail "+singleItem.getThumbnail());
        getFile(singleItem.getThumbnail(),holder.picImage);


//        int drawableId = holder.itemView.getResources()
//                .getIdentifier("pizza_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(context)
//                .load(drawableId)
//                .into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView name, itemType;
        ImageView picImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_item_name_textView);
            itemType = itemView.findViewById(R.id.list_type_textView);
            picImage = itemView.findViewById(R.id.food_item_imageView);
        }
    }

    public void getFile(String fileId, ImageView view){
        SharedPreferences prefLogin = context.getSharedPreferences(AppConstants.PREF_LOGIN,Context.MODE_PRIVATE);
        String sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
        DefaultApi api = new DefaultApi();
        api.getFile(fileId, sessionId, new Response.Listener<File>() {
            @Override
            public void onResponse(File file) {
                Log.d("myTag", "file "+file.toString());
                Log.d("myTag", "file "+file.getAbsolutePath());
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    view.setImageBitmap(bitmap);
//                    Glide.with(context)
//                            .asBitmap()
//                            .load(file)
//                            .into(view);
                } catch (Exception e) {
                    Log.d("myTag", "exception "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myTag", "unable to get file");
                Log.d("myTag", "error is "+error);
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    Log.d("myTag", "data "+data);
                    Log.d("myTag", "status "+statusCode);
                } catch (Exception e) {
                    Log.d("myTag", "error e "+e);
                }
            }
        });
    }

    // Method to store a bitmap in shared preferences
    public static void saveBitmapToPrefs(Context context, Bitmap bitmap, String IMAGE_KEY) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.PREF_IMAGE_STORAGE, Context.MODE_PRIVATE).edit();
        editor.putString(IMAGE_KEY, encodeBitmapToString(bitmap));
        editor.apply();
    }

    // Method to retrieve a bitmap from shared preferences
    public static Bitmap getBitmapFromPrefs(Context context,String IMAGE_KEY) {
        SharedPreferences prefs = context.getSharedPreferences(AppConstants.PREF_IMAGE_STORAGE, Context.MODE_PRIVATE);
        String imageString = prefs.getString(IMAGE_KEY, null);
        if (imageString != null) {
            return decodeStringToBitmap(imageString);
        }
        return null;
    }

    // Helper method to encode bitmap to Base64 string
    private static String encodeBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    // Helper method to decode Base64 string to bitmap
    private static Bitmap decodeStringToBitmap(String imageString) {
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}

