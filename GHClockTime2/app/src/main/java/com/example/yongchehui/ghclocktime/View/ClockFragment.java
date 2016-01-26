package com.example.yongchehui.ghclocktime.View;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.yongchehui.ghclocktime.R;


/**
 * Created by yongcheHui on 16/1/22.
 */
public class ClockFragment extends Fragment{
    private ListView lv = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clock_frag, container, false);
        lv = (ListView)view.findViewById(R.id.clocklist);
        lv.setAdapter(new ClockListAdapter(this.getActivity()));
        return view;
    }

    String inflater = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater layoutInflater = null;
    class ClockListAdapter extends BaseAdapter
    {
        private Context context;
        //构造函数
        public ClockListAdapter(Context context)
        {
            this.context = context;
            layoutInflater = (LayoutInflater) context
                    .getSystemService(inflater);
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //对listview布局
            RelativeLayout item_layout = (RelativeLayout) layoutInflater.inflate(
                    R.layout.clock_item, null);
            return item_layout;
        }
    }
}
