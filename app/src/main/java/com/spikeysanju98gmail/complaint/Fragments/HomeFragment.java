package com.spikeysanju98gmail.complaint.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spikeysanju98gmail.complaint.Models.ChairBanner;
import com.spikeysanju98gmail.complaint.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View v;
    private SliderLayout chairSlider;
    HashMap<String, String> image_list;
    private DatabaseReference chairDB;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        slideSetUP();

        return v;
    }


        private void slideSetUP() {

        chairSlider = (SliderLayout) v.findViewById(R.id.chair_slider);
        image_list = new HashMap<>();


        chairDB = FirebaseDatabase.getInstance().getReference().child("Banner");

        chairDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot PostSnapshot : dataSnapshot.getChildren()) {

                    ChairBanner banner = PostSnapshot.getValue(ChairBanner.class);
                    assert banner != null;
                    image_list.put(banner.getName() + "" + banner.getId(), banner.getImage());


                }

                for (String key : image_list.keySet()) {

                    String[] keySplit = key.split("-");
                    final String chair_Name = keySplit[0];
                    //   String idofDog = keySplit[1];

                    //Here i Create slider
                    final TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView.description(chair_Name).image(image_list.get(key))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {

                                    Toast.makeText(getActivity(), "Clicked" + chair_Name, Toast.LENGTH_SHORT).show();
                                }
                            });


                    chairSlider.addSlider(textSliderView);

                    chairDB.removeEventListener(this);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }




        });

        chairSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        chairSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        chairSlider.setCustomAnimation(new DescriptionAnimation());
        chairSlider.setDuration(4000);



    }

}
