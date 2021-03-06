package com.example.hoonianAgent.view.fragment.project.unitType;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hoonianAgent.R;
import com.example.hoonianAgent.model.content.Media;
import com.example.hoonianAgent.model.content.property.UnitType;
import com.example.hoonianAgent.model.request.project.RequestGetUnitTypeDetail;
import com.example.hoonianAgent.model.response.project.ResponseGetUnitTypeDetail;
import com.example.hoonianAgent.presenter.base.impl.BaseImpl;
import com.example.hoonianAgent.presenter.utils.UtilsCurrency;
import com.example.hoonianAgent.presenter.utils.UtilsMenuFragment;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;

import lombok.Setter;

@EBean
public class UnitTypeDetailImpl extends BaseImpl<UnitTypeDetailView> implements UnitTypeDetailPres {
    @Bean
    protected UtilsMenuFragment utilsMenuFragment;
    @Setter
    protected UnitType unitType;

    private int currentVideoIndex;

    @Override
    public void init() {
        serviceManager.getUnitTypeDetail(new RequestGetUnitTypeDetail(unitType.getId(), unitType.getClusterId(), unitType.getProjectId()));
    }

    @Override
    public void initData(UnitType unitType) {
        currentVideoIndex = 0;
        loadImage(unitType.getImage(), viewAct.image());
        viewAct.typeName().setText(unitType.getName() + " Unit");
        viewAct.size().setText("Size : "+ unitType.getSize());
        viewAct.available().setText("Total Available : " + unitType.getAvailableUnit() + " units");
        viewAct.total().setText("Total Units : " + unitType.getTotalUnit() + " units");
        viewAct.price().setText("Price : " + UtilsCurrency.toString(unitType.getStartPrice()));
        if (unitType.getVr().size() == 0) {
            utilsLayout.hide(viewAct.vrLayout());
        }
        else {
            loadImage(unitType.getVr().get(0).getLink(), viewAct.vr());
        }

        if (unitType.getGallery().size() == 0) {
            utilsLayout.hide(viewAct.galleryLayout());
        }
        else {
            setDataCarousel(unitType.getGallery());
        }

        if (unitType.getVideos().size() == 0) {
            utilsLayout.hide(viewAct.videoLayout());
        }
        else {
            setVideoThumbnail();
        }
    }

    @Override
    @UiThread
    public void setDataCarousel(ArrayList<Media> listGallery) {
        if (viewAct.carousel() != null) {
            viewAct.carousel().setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    loadImage(listGallery.get(position).getLink(), imageView);
                }
            });

            viewAct.carousel().setPageCount(listGallery.size());
            viewAct.carousel().setImageClickListener(new ImageClickListener() {
                @Override
                public void onClick(int position) {
                }
            });
        }
    }

    @Override
    @UiThread
    public void setVideoThumbnail() {
        loadImage(unitType.getVideos().get(currentVideoIndex).getLink(), viewAct.video());
    }

    @Override
    public void playVideo() {
        intentManager.moveToVideoViewer(unitType.getVideos().get(currentVideoIndex));
    }

    @Override
    public void nextVideo() {
        if (currentVideoIndex != unitType.getVideos().size()) {
            currentVideoIndex++;
        }
        else {
            currentVideoIndex = 0;
        }
    }

    @Override
    public void prevVideo() {
        if (currentVideoIndex != 0) {
            currentVideoIndex--;
        }
        else {
            currentVideoIndex = unitType.getVideos().size();
        }
    }

    @Override
    public void onSuccessWithData(Object o) {
        super.onSuccessWithData(o);
        if (o instanceof ResponseGetUnitTypeDetail) {
            unitType = ((ResponseGetUnitTypeDetail) o).getUnitTpe();
            initData(unitType);
        }
    }
}
