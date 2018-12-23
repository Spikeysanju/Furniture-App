package com.spikeysanju98gmail.complaint.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spikeysanju98gmail.complaint.Activity.ChairsActivity;
import com.spikeysanju98gmail.complaint.Models.Category;
import com.spikeysanju98gmail.complaint.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private View v;
    private RecyclerView chairsRV;
    private ImageButton backBtn;
    private ImageButton changeToList;
    private DatabaseReference categoryDB;
    private FirebaseRecyclerAdapter<Category,CategoryViewHolder> categoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_category, container, false);



        //Initialising Recyclerview
        chairsRV = (RecyclerView)v.findViewById(R.id.chairsRV);

        //Setting up DB path reference
        categoryDB = FirebaseDatabase.getInstance().getReference().child("Category");

        //Recyclerview Components settings
        chairsRV.setLayoutManager(new GridLayoutManager(getActivity(),2));
        chairsRV.setHasFixedSize(true);
        chairsRV.setNestedScrollingEnabled(false);

        //Init Recyclerview in oncreate method
        initCategory();


        return v;
    }

    private void initCategory() {

        //Query to display data
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(categoryDB,Category.class)
                .build();



        categoryAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position, @NonNull Category model) {


                //Binding Data Here
                holder.setName(model.getName());
                holder.setImage(getActivity(), model.getImage());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent chairsDetails = new Intent(getActivity(),ChairsActivity.class);
                        chairsDetails.putExtra("categoryID",categoryAdapter.getRef(position).getKey());
                        startActivity(chairsDetails);

                    }
                });

            }


            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_layout,parent,false);

                return new CategoryViewHolder(view);
            }
        };


        //Setting Up recylerview with adapter
        chairsRV.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

    }



    //ViewHolder Class for Category
    public static class CategoryViewHolder extends  RecyclerView.ViewHolder{

        View mView;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName(String name){

            TextView categoryTitle = (TextView) mView.findViewById(R.id.categoryTitle);

            categoryTitle.setText(name);


        }
        public void setImage(Context ctx, String image) {

            ImageView categoryImage = (ImageView) mView.findViewById(R.id.categoryIMG);

            Picasso.with(ctx).load(image).into(categoryImage);


        }
    }

    @Override
    public void onStart() {
        super.onStart();
        categoryAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }
}
