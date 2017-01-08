package badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.OnboardUser;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.VerifyUser;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 30-10-2016.
 */
public class SlideshowDialogFragment extends DialogFragment {
    private String TAG = SlideshowDialogFragment.class.getSimpleName();
    private Image images;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblCount, lblTitle, lblDate;
    private int selectedPosition = 0;
    boolean isappIntro = false;
    boolean isFromHome = false;
    //PhotoViewAttacher mAttacher;

    public static SlideshowDialogFragment newInstance() {
        SlideshowDialogFragment f = new SlideshowDialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_slider, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        lblCount = (TextView) v.findViewById(R.id.lbl_count);
        lblTitle = (TextView) v.findViewById(R.id.title);
        lblDate = (TextView) v.findViewById(R.id.date);
        isappIntro = getArguments().getBoolean("isappintro");
        isFromHome = getArguments().getBoolean("isFromHome",false);
        if (isappIntro) {
            images = new Image(null, Constants.toarray(Constants.Arrays.Pics.intro_imgs), "Malhar 2K17", "Welcome to malhar 2K17 ");
            selectedPosition = getArguments().getInt("position");
        } else {
            images = (Image) getArguments().getSerializable("images");
            selectedPosition = getArguments().getInt("position");


        }
        Log.e(TAG, "position: " + selectedPosition);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        setCurrentItem(selectedPosition);

        return v;
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
        if (isappIntro){

            lblCount.setText((position + 1) + " of 5" );
        }else{
            lblCount.setText((position + 1) + " of " + images.getImages().size());}

        lblTitle.setText(images.getEvent_name());
        lblDate.setText(images.getEvent_desc());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    //  adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            TouchImageView imageViewPreview = (TouchImageView) view.findViewById(R.id.thumbnail);


            if (isappIntro) {
                if (position==images.getImages_base().size()){
                    if (!isFromHome){
                    Utils.launch(getContext(), VerifyUser.class,true);}
                }else{

                    Glide.with(getActivity()).load(images.getImages_base().get(position))
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.progress_bar)
                            .into(imageViewPreview);
                }

            } else {
                Log.d(TAG, "instantiateItem: " + images.getImages().get(position));
                Glide.with(getActivity()).load(Constants.Arrays.base_url + images.getImages().get(position))
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.progress_bar)
                        .into(imageViewPreview);
            }

            container.addView(view);


            return view;
        }

        @Override
        public int getCount() {
            if (isappIntro) {
                return images.getImages_base().size()+1;
            } else {
                return images.getImages().size();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}