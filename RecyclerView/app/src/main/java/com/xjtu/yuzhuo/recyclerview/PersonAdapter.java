package com.xjtu.yuzhuo.recyclerview;

/**
 * Created by yuzhuo on 16/9/6.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by yuzhuo on 16/9/6.
 */
public class PersonAdapter extends RecyclerView.Adapter {
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = PersonAdapter.class.getSimpleName();
    private List<Person> list;

    public PersonAdapter(List<Person> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_test_item_person, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = i;
        Person person = list.get(i);
        holder.nameTv.setText(person.getName());
        holder.ageTv.setSpecifiedTextsColor("我的老家","老家", Color.parseColor("#FF4081"),18);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView nameTv;
        public SpannableTextView ageTv;
        public int position;

        public PersonViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (SpannableTextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }

}
