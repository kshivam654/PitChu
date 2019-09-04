package com.app.sharma.pitchu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.sharma.pitchu.Common.Common;
import com.app.sharma.pitchu.Interface.ItemClickListner;
import com.app.sharma.pitchu.Model.CategoryItem;
import com.app.sharma.pitchu.R;
import com.app.sharma.pitchu.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    public static CategoryFragment INSTANCE = null;
    FirebaseDatabase database;
    DatabaseReference category_background;
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;
    RecyclerView recyclerView;

    public CategoryFragment() {
        // Required empty public constructor
        database = FirebaseDatabase.getInstance();
        category_background = database.getReference(Common.STR_CATEGORY_BACKGROUND);

        options = new FirebaseRecyclerOptions.Builder<CategoryItem>().
                setQuery(category_background, CategoryItem.class).build();

        adapter = new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final CategoryItem model) {

                Picasso.with(getActivity()).load(model.getImageLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.background_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                                // we have to try online for the cashe data
                                Picasso.with(getActivity()).load(model.getImageLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.background_image, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("PitChu Error", "Couldn't fetch image");
                                            }
                                        });
                            }
                        });
                holder.category_name.setText(model.getName());

                holder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View view, int position) {
                        //code late for detail catgory
                    }
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemview = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_category_item, parent, false);
                return new CategoryViewHolder(itemview);
            }
        };

    }

    public static CategoryFragment getInstance() {

        if (INSTANCE == null)
            INSTANCE = new CategoryFragment();
        return INSTANCE;
    }


    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceStage) {

        super.onCreate(savedInstanceStage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_category);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setCategory();
        return view;
    }

    private void setCategory() {

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }
}
