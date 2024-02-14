package com.example.swiggy_lite.Cart_Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.OrderItem;
import com.openapi.deliveryApp.model.PlaceOrderRequest;
import com.openapi.deliveryApp.model.PlaceOrderRequestItemCartInner;

import java.security.PrivilegedAction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SelectAddressFragment extends Fragment {
    public interface OrderCallback {
        void onOrderPlaced(String orderId);
        void onOrderPlacementError(int statusCode, String errorMessage);
    }

    private EditText khanar_room, indravati_room , shivnath_room;
    private Spinner academic_areas;
    public String Address;
    private String sessionId;
    private CardView place_order;
    private RadioGroup radioGroup;
    private OrderModel orderModel;
    private DefaultApi api;
    private LoadingDialog loadingDialog;
    private SharedPreferences prefLogin;
    private SharedPreferences prefOrders;
    private SharedPreferences.Editor prefEdit;
    private SharedPreferences.Editor prefOrderEdit;

    public SelectAddressFragment(OrderModel orderModel){
        this.orderModel = orderModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_select_address, container, false);
        {
            api = new DefaultApi();
            loadingDialog = new LoadingDialog(requireActivity());
            prefLogin = getActivity().getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            prefOrders = getActivity().getSharedPreferences(AppConstants.PREF_ORDERS,Context.MODE_PRIVATE);
            prefEdit = prefLogin.edit();
            prefOrderEdit = prefOrders.edit();
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
            radioGroup = view.findViewById(R.id.radioGroup);
            khanar_room = view.findViewById(R.id.khanar_room_picker_editText);
            indravati_room = view.findViewById(R.id.indravati_room_picker_editText);
            shivnath_room = view.findViewById(R.id.shivnath_room_picker_editText);
            academic_areas = view.findViewById(R.id.academic_areas_dropdown);
            place_order = view.findViewById(R.id.place_order_cardView);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            academic_areas.setVisibility(View.GONE);
            indravati_room.setVisibility(View.GONE);
            khanar_room.setVisibility(View.GONE);
            shivnath_room.setVisibility(View.GONE);

            if (checkedId == R.id.khanar_hostel_radioButton) {
                khanar_room.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.shivnath_hostel_radioButton) {
                shivnath_room.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.girls_hostel_radioButton) {
                indravati_room.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.academic_area_radioButton) {
                setupDropdown();
                academic_areas.setVisibility(View.VISIBLE);
            }
        });
        place_order.setOnClickListener(v -> {
            boolean isAddressValid = false;
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = view.findViewById(selectedId);
                if (selectedId == R.id.khanar_hostel_radioButton) {
                    String room = khanar_room.getText().toString();
                    if (validateRoomNumber(room)) {
                        this.Address = selectedRadioButton.getText().toString() + " " + room;
                        isAddressValid = true;
                    } else {
                        khanar_room.setError("Enter valid room number");
                    }
                } else if (selectedId == R.id.shivnath_hostel_radioButton) {
                    String room = shivnath_room.getText().toString();
                    if (validateRoomNumber(room)) {
                        this.Address = selectedRadioButton.getText().toString() + " " + room;
                        isAddressValid = true;
                    } else {
                        khanar_room.setError("Enter valid room number");
                    }
                } else if (selectedId == R.id.girls_hostel_radioButton) {
                    String room = indravati_room.getText().toString();
                    if (validateRoomNumber(room)) {
                        this.Address = selectedRadioButton.getText().toString() + " " + room;
                        isAddressValid = true;
                    } else {
                        khanar_room.setError("Enter valid room number");
                    }
                } else if (selectedId == R.id.academic_area_radioButton) {
                    this.Address = "Academic Area " + this.Address;
                    isAddressValid = true;
                } else if (selectedId == R.id.director_house_radioButton) {
                    this.Address = selectedRadioButton.getText().toString();
                    this.Address = this.Address.replace("'","");
                    isAddressValid = true;
                } else {
                    this.Address = selectedRadioButton.getText().toString();
                    isAddressValid = true;
                }
                Toast.makeText(requireContext(), "Selected: " + Address, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Please select an address", Toast.LENGTH_SHORT).show();
            }

            if (isAddressValid) {
                Log.d("myTag", "address");
                String message = "Your order has been successfully placed!";
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

                //Inflate the layout for the dialog
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

                orderModel.setDeliveryAddress(this.Address);
                orderModel.setDate(dateFormat.format(currentDate));
                orderModel.setTime(timeFormat.format(currentDate));
                placeOrder(sessionId, new OrderCallback() {
                    @Override
                    public void onOrderPlaced(String response) {
                        String[] id_list = response.split(",");
                        for (int i = 0; i < id_list.length; i++) {
                            try{
                                String id = id_list[i].trim().replace("\"", "");
                                prefOrderEdit.putString(id,AppConstants.STATUS_ONGOING);
                                prefOrderEdit.apply();
                            }catch (Exception e){
                                Toast.makeText(requireContext(),"Order id "+id_list[i]+"not saved",Toast.LENGTH_SHORT).show();
                            }
                        }

                        View viewOrder = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_dialog, null);
                        LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);
                        TextView message = viewOrder.findViewById(R.id.dialog_message_textView);
                        message.setText("Your order has been successfully placed!");
                        animationView.setAnimation(R.raw.order_success);
                        animationView.playAnimation();

                        builder.setView(viewOrder)
                                .setTitle("Order Placed Successfully")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(requireContext(),MasterActivity.class));
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onOrderPlacementError(int statusCode, String errorMessage) {
                        View viewOrder = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_dialog, null);
                        LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);
                        TextView message = viewOrder.findViewById(R.id.dialog_message_textView);
                        message.setText("Your order has not been placed successfully!");
                        animationView.setAnimation(R.raw.failure_animation);
                        animationView.playAnimation();

                        builder.setView(viewOrder)
                                .setTitle("Error Occur !! ")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });


            }
        });

        return view;
    }

    private void setupDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Data Center and Data Center", "Engineering Department |", "Academic Block"}
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        academic_areas.setAdapter(adapter);

        academic_areas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Address = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private boolean validateRoomNumber(String roomNumber){
        if (roomNumber.length() == 4) {
            char firstChar = roomNumber.charAt(0);
            try {
                int secondDigit = Character.getNumericValue(roomNumber.charAt(1));
                int lastTwoDigits = Integer.parseInt(roomNumber.substring(2));

                if ("ABCDEFG".indexOf(firstChar) != -1 && secondDigit >= 1 && secondDigit <= 4
                        && lastTwoDigits >= 1 && lastTwoDigits <= 18) {
                    return true;
                }
            }catch (Exception ignored){
                return false;
            }
        }
        return false;
    }

    public void placeOrder(String sessionId, final OrderCallback callback) {
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
        placeOrderRequest.setItemCart(getItemList(orderModel.getOrderItemAdvanced()));
        placeOrderRequest.setLocation(orderModel.getDeliveryAddress());
        loadingDialog.startLoadingDialog();
        api.placeOrder(sessionId, placeOrderRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onOrderPlaced(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onOrderPlacementError(statusCode, data.trim().substring(1,41));;
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    callback.onOrderPlacementError(0, null);
                    loadingDialog.dismissDialog();
                }
            }
        });
    }

    public List<PlaceOrderRequestItemCartInner> getItemList(List<OrderItemAdvanced> orderItemList){
        List<PlaceOrderRequestItemCartInner> list = new ArrayList<>();
        for(OrderItemAdvanced orderItem : orderItemList) {
             PlaceOrderRequestItemCartInner cartItem = new PlaceOrderRequestItemCartInner();
             cartItem.setItemId(orderItem.getItemId());
             cartItem.setQuantity(orderItem.getQuantity());
             list.add(cartItem);
        }
        return list;
    }
}

