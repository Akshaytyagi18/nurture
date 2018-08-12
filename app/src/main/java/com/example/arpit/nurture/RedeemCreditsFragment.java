package com.example.arpit.nurture;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedeemCreditsFragment extends Fragment {


    public RedeemCreditsFragment() {
        // Required empty public constructor
    }

    ArrayList<CouponManager> cm = new ArrayList<>();
    ArrayList<Coupon> c = new ArrayList<>();
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_redeem_credits, container, false);
        lv = v.findViewById(R.id.coupons_list_view);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Adding 3 dummy coupons
        for(Integer i=0; i<3; i++)
        {
            CouponManager temp = new CouponManager();
            Coupon cTemp = new Coupon();
            temp.setType("Type" + Integer.toString(i));
            temp.setCreditsRequired(5 + i*10);
            temp.setAmount(100 + i*10);
            temp.setDescription("Redeemable on all products");

            switch (i){

                case 0:
                    temp.setImageID(R.drawable.fbb_logo);
                    break;
                case 1:
                    temp.setImageID(R.drawable.amazon_logo);
                    break;
                case 2:
                    temp.setImageID(R.drawable.background);
            }

            cm.add(temp);

            cm.get(i).createNewCoupon("qwerty" + i.toString());
            cTemp = cm.get(i).getCoup().get(0);
            cTemp.setImageID(cm.get(i).getImageID());
            c.add(cTemp);

            CouponAdapter adapter = new CouponAdapter(getActivity());
            lv.setAdapter(adapter);
        }
    }

    public class CouponAdapter extends ArrayAdapter{

        public CouponAdapter(@NonNull Context context) {
            super(context, R.layout.coupon_layout, cm);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.coupon_layout, parent, false);

            TextView title = v.findViewById(R.id.title);
            TextView description = v.findViewById(R.id.description);
            TextView creditsRequired = v.findViewById(R.id.credits_required);
            ImageView companyLogo = v.findViewById(R.id.company_logo);

            CouponManager t = cm.get(position);
            Coupon temp = t.getCoup().get(0);

            title.setText(temp.getType() + " (Rs " + temp.getAmount().toString() + ")");
            description.setText(temp.getDescription());
            creditsRequired.setText(temp.getCreditsRequired().toString());
            companyLogo.setImageResource(temp.getImageID());

            return v;
        }
    }

}
