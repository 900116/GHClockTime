package com.example.yongchehui.ghclocktime.View;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yongchehui.ghclocktime.Base.UpdateEventHandler;
import com.example.yongchehui.ghclocktime.GHRealm;
import com.example.yongchehui.ghclocktime.Module.GHUserClock;
import com.example.yongchehui.ghclocktime.Module.GHUserClockType;
import com.example.yongchehui.ghclocktime.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by yongcheHui on 16/1/22.
 */
public class ClockFragment extends Fragment{
    private SliderListView lv = null;
    private List<GHUserClock> clocks = null;
    private ClockListAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clock_frag, container, false);
        lv = (SliderListView)view.findViewById(R.id.clocklist);
        adapter = new ClockListAdapter(this);
        lv.setAdapter(adapter);
        clocks = GHUserClock.getClockList(this.getActivity());
        return view;
    }


    public void reloadData()
    {
        clocks = GHUserClock.getClockList(this.getActivity());
        adapter.notifyDataSetChanged();
    }

    String inflater = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater layoutInflater = null;
    class ClockListAdapter extends BaseAdapter
    {
        private ClockFragment fragment;
        //构造函数
        public ClockListAdapter(ClockFragment fragment)
        {
            this.fragment = fragment;
            layoutInflater = (LayoutInflater) fragment.getActivity()
                    .getSystemService(inflater);
        }


        @Override
        public int getCount() {
            return fragment.clocks == null?0:fragment.clocks.size();
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
//            SliderView slideView = (SliderView) convertView;
            // 这里是我们的item
            RelativeLayout item_layout = (RelativeLayout) layoutInflater.inflate(R.layout.clock_item,  null );

//            slideView =  new SliderView(fragment.getActivity());
//            // 这里把item加入到slideView
//            slideView.setContentView(item_layout);
//            slideView.setOnSlideListener(null);

            final GHUserClock clock = fragment.clocks.get(position);
            Date date = clock.getTime();

            GHClockView clockView = (GHClockView) item_layout.findViewById(R.id.clocklistItem_clock);
            clockView.date = date;

            TextView timeTV = (TextView) item_layout.findViewById(R.id.clocklistItem_time);
            SimpleDateFormat format = new SimpleDateFormat("a hh:mm");
            timeTV.setText(format.format(date));

            TextView typeTV = (TextView) item_layout.findViewById(R.id.clocklistItem_type);
            typeTV.setText(GHUserClockType.valueOf(clock.getType()).toString());

            Switch sw = (Switch)item_layout.findViewById(R.id.clocklistItem_swtich);
            sw.setChecked(clock.isOpenState());
            sw.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    final  boolean tIsChecned = isChecked;
                    GHRealm.update(fragment.getActivity(), new UpdateEventHandler() {
                        @Override
                        public void execute() {
                            clock.setOpenState(tIsChecned);
                        }
                    });
                }
            });
            return  item_layout;
        }
    }
}
