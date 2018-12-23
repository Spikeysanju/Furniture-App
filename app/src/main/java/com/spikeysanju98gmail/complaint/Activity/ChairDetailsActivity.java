package com.spikeysanju98gmail.complaint.Activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spikeysanju98gmail.complaint.R;
import com.squareup.picasso.Picasso;

public class ChairDetailsActivity extends AppCompatActivity {
//
//    private TextView chairDesc;
//    private ImageView chairImg;
//    private DatabaseReference chairDB;
//    private CollapsingToolbarLayout collapsingToolbarLayoutt;
//    String chairDetails="";
//    Moments currentMoment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_details);


//
//        collapsingToolbarLayoutt = (CollapsingToolbarLayout)findViewById(R.id.collapsee);
//        collapsingToolbarLayoutt.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
//        collapsingToolbarLayoutt.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
//
//        momentsDB = FirebaseDatabase.getInstance().getReference().child("Moments");
//
//        Bundle extras = getIntent().getExtras();
//        if (extras!=null){
//            momentsDetails = extras.getString("momentsDetails");
//            if (!momentsDetails.isEmpty() && momentsDetails!=null){
//                fetchMomentDetails(momentsDetails);
//            }
//        }
//
//
//        moment_Desc = (TextView)findViewById(R.id.mom_Desc);
//        moments_Image = (ImageView)findViewById(R.id.mom_Image_d);






    }

//    private void fetchMomentDetails(String momentsDetails) {
//
//        momentsDB.child(momentsDetails).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                currentMoment = dataSnapshot.getValue(Moments.class);
//
//                Picasso.with(getApplicationContext()).load(currentMoment.getImage()).into(moments_Image);
//
//                moment_Desc.setText(currentMoment.getDescription());
//
//                collapsingToolbarLayoutt.setTitle(currentMoment.getTitle());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}


