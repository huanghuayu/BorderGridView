package com.feinno.border;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feinno.view.BorderGridView;

public class MainActivity extends Activity {

    private BorderGridView borderGridView;

    private String[] icbcNames = {"融e购", "网点预约", "融e行", "医疗健康", "e缴费",
            "e生活", "财富吧", "e投资", "计算器"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        borderGridView = (BorderGridView)findViewById(R.id.border_grid_view);

        borderGridView.setAdapter(new BorderGridViewAdapter(this, icbcNames));
    }

    /**适配器**/
    class BorderGridViewAdapter extends BaseAdapter {
        private Context context;
        private String[] icbcs;

        public BorderGridViewAdapter(Context context, String[] icbcs){
            this.context = context;
            this.icbcs = icbcs;
        }

        @Override
        public int getCount() {
            if(icbcs != null && icbcs.length > 0){
                return icbcs.length;
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if(view == null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.border_gv_item, null);
                viewHolder.icon_txt = (TextView) view.findViewById(R.id.icon_txt);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.icon_txt.setText(icbcs[i]);

            return view;
        }

        class ViewHolder{
            TextView icon_txt;
        }
    }
}
