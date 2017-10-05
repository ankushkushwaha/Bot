package com.starcut.bot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ankush on 10/5/17.
 */

public class RecycleriVewAdapter extends RecyclerView.Adapter<RecycleriVewAdapter.ViewHolder> {

//    private List<String> values;
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView textView;
//        public View layout;
//
//        public ViewHolder(View v) {
//            super(v);
//            layout = v;
//            textView = (TextView) v.findViewById(R.id.textView);
//        }
//    }
//
//
//
//    public RecycleriVewAdapter(List<String> myDataset) {
//        values = myDataset;
//    }
//
//    @Override
//    public RecycleriVewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
//                                                   int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(
//                parent.getContext());
//        View v =
//                inflater.inflate(R.layout.item_recycler_view, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//         final String name = values.get(position);
//        holder.textView.setText(name);
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Logger.i("Clicked index");
//            }
//        });
//
//        holder.textView.setText(name);
//    }
//
//    @Override
//    public int getItemCount() {
//        return values.size();
//    }
//
//}


    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private final List<String> items;
    private final OnItemClickListener listener;

    public RecycleriVewAdapter(List<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            name.setText(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
