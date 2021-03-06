package com.example.hoonianAgent.view.adapter.recycler;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hoonianAgent.R;
import com.example.hoonianAgent.model.content.acl.ModelMore;
import com.example.hoonianAgent.presenter.callback.RecyclerListener;
import com.example.hoonianAgent.view.viewholder.base.VHMore;

import java.util.ArrayList;

public class AdapterMore extends BaseAdapter<ModelMore>{
    public AdapterMore(ArrayList<ModelMore> listItem, RecyclerListener recyclerListener) {
        super(listItem, recyclerListener);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new VHMore(createView(parent, R.layout.adapter_more), recyclerListener);
    }
}
