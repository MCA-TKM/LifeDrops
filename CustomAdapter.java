package com.example.faris.lifedrops;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faris.lifedrops.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtMail;
        TextView txtAge;
        Button info;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(mContext, "jjj", Toast.LENGTH_SHORT).show();

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;


        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dataModel.phone, null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(intent);



    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtMail = (TextView) convertView.findViewById(R.id.mail);
            viewHolder.txtAge = (TextView) convertView.findViewById(R.id.age);
            viewHolder.info = (Button) convertView.findViewById(R.id.call);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtMail.setText(dataModel.getType());
        viewHolder.txtAge.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
