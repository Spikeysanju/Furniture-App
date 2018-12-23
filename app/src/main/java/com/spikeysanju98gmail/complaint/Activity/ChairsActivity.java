package com.spikeysanju98gmail.complaint.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.spikeysanju98gmail.complaint.Models.Chairs;
import com.spikeysanju98gmail.complaint.R;
import com.squareup.picasso.Picasso;
import com.tapadoo.alerter.Alerter;

public class ChairsActivity extends AppCompatActivity {

    private RecyclerView chairsRecylcer;
    private DatabaseReference mChairsDB;
    private String categoryID ="";

    FirebaseRecyclerAdapter<Chairs,ChairsHolder> chairAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);

        chairsRecylcer = (RecyclerView)findViewById(R.id.chairsRecycler);
        mChairsDB = FirebaseDatabase.getInstance().getReference().child("Chairs");

        chairsRecylcer.setHasFixedSize(true);
        chairsRecylcer.setLayoutManager(new LinearLayoutManager(this));
        chairsRecylcer.setNestedScrollingEnabled(false);

        if (getIntent()!=null){
            categoryID = getIntent().getStringExtra("categoryID");
            if (!categoryID.isEmpty() && categoryID!=null){

                initChairs(categoryID);

                Toast.makeText(this, ""+ categoryID, Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void initChairs(String categoryID) {


        Query chairQuery = mChairsDB.orderByChild("menuID").equalTo(categoryID);


        FirebaseRecyclerOptions<Chairs> options = new FirebaseRecyclerOptions.Builder<Chairs>()
                .setQuery(chairQuery,Chairs.class)
                .build();


        chairAdapter = new FirebaseRecyclerAdapter<Chairs, ChairsHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ChairsHolder holder, int position, @NonNull final Chairs model) {

                holder.setName(model.getName());
                holder.setImage(getApplicationContext(), model.getImage());



                holder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Alerter.create(ChairsActivity.this).setTitle(model.getName())
                                .setText(model.getPrice())
                                .setIcon(R.drawable.ic_shopping_cart_white)
                                .setBackgroundColorRes(R.color.colorAccent)
                                .setDuration(3000)
                                .enableSwipeToDismiss()// or setBackgroundColorInt(Color.CYAN)
                                .show();
                    }
                });
            }

            @NonNull
            @Override
            public ChairsHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chairs_rv_layout,parent,false);

                return new ChairsHolder(view);
            }
        };

        chairsRecylcer.setAdapter(chairAdapter);
        chairAdapter.notifyDataSetChanged();

    }



    public static class ChairsHolder extends  RecyclerView.ViewHolder{

        View mView;
        private ImageButton addToCart;
        public ChairsHolder(View itemView) {
            super(itemView);
            mView = itemView;

            addToCart = (ImageButton)mView.findViewById(R.id.addToCart);
        }



        public void setName(String name){

            TextView chairTitle = (TextView) mView.findViewById(R.id.chairTitle);

            chairTitle.setText(name);


        }
        public void setImage(Context ctx, String image) {

            ImageView categoryImage = (ImageView) mView.findViewById(R.id.chairImage);

            Picasso.with(ctx).load(image).into(categoryImage);


        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        chairAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        chairAdapter.stopListening();
    }
}
