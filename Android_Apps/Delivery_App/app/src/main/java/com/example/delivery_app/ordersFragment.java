import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery_app.ItemListAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private String category;

    public OrdersFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        // Example: Populate RecyclerView with dummy data
        List<String> itemList = getDummyItemList();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemListAdapter(itemList));

        return view;
    }

    // Example: Dummy data for the item list
    private List<String> getDummyItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            itemList.add(category + " Item " + i);
        }
        return itemList;
    }
}
