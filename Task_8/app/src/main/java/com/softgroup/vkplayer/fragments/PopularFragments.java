package com.softgroup.vkplayer.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softgroup.vkplayer.R;
import com.softgroup.vkplayer.adapters.PopularAdapter;
import com.softgroup.vkplayer.data.PopularListItem;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiAudio;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

public class PopularFragments extends Fragment {

    private RecyclerView mRecyclerView;
    private PopularAdapter mAdapter;
    private ArrayList<PopularListItem> mItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.listViewStartMusic);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);

        mItems = new ArrayList<>();

        VKRequest popular = VKApi.audio().getPopular(VKParameters.from(VKApiConst.COUNT, 15));
        popular.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList<VKApiAudio> list = (VKList) response.parsedModel;

                for (VKApiAudio audio : list) {
                    mItems.add(new PopularListItem(audio.title));
                }
                mAdapter = new PopularAdapter(mItems);

                mRecyclerView.setAdapter(mAdapter);
            }
        });


        return v;
    }

}
