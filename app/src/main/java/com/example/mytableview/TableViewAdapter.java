package com.example.mytableview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<TableModal> tableModalList;
    private static int TYPE_HEADER = 1;
    private static int TYPE_CONTENT = 2;

    public TableViewAdapter(List<TableModal> tableList) {
        this.tableModalList = tableList;
    }

    @Override
    public int getItemViewType(int position){
        if(position==12){
            return R.layout.button;
        }
        if(TextUtils.isEmpty(tableModalList.get(position).getType())){
                return TYPE_HEADER;
        }
        else {
            return TYPE_CONTENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

         if(viewType == TYPE_HEADER){
            itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.item_list, parent, false);
            return new HeaderViewHolder(itemView);
        }
        else{
            itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.content_item_list, parent, false);
            return new RowViewHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if(getItemViewType(position)==TYPE_HEADER){
            ((HeaderViewHolder)holder).setHeaderDetails(tableModalList.get(position));

            ((HeaderViewHolder) holder).btnCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = "";

                    for(int i=0; i < tableModalList.size(); ++i){
                        TableModal single = tableModalList.get(i);
                        if(single.isSelected()==true){
                            data =  data + "\n" + single.getTitle().toString();
                        }
                    }

                    Toast.makeText(v.getContext(),"Selected Students: \n" + data, Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            ((RowViewHolder)holder).setRowViewHolder(tableModalList.get(position));
            ((RowViewHolder) holder).txtCheckBox.setTag(tableModalList.get(position));

            ((RowViewHolder) holder).txtCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    TableModal tableModal = (TableModal) cb.getTag();
                    tableModal.setSelected(cb.isChecked());
                    tableModalList.get(position).setSelected(cb.isChecked());

                    //Toast.makeText(v.getContext(),"Clicked On Checkbox: "+ cb.getText() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return tableModalList.size(); // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtRank;
        protected TextView txtTitle;
        protected TextView txtSubtitle;
        protected TextView txtDate;
        protected TextView txtPriority;
        protected CheckBox txtCheckBox;
        protected TextView txtType;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtRank = itemView.findViewById(R.id.txtRank);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubtitle = itemView.findViewById(R.id.txtSubtitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPriority = itemView.findViewById(R.id.txtPriority);
            txtCheckBox = (CheckBox) itemView.findViewById(R.id.txtCheckBox);
            txtType = itemView.findViewById(R.id.txtType);

        }

        private void setRowViewHolder(TableModal modal)
        {
            txtRank.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtTitle.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtSubtitle.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtPriority.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtCheckBox.setBackgroundResource(R.drawable.table_content_cell_bg);
            txtType.setBackgroundResource(R.drawable.table_content_cell_bg);

            txtRank.setText(modal.getRank()+"");
            txtTitle.setText(modal.getTitle());
            txtSubtitle.setText(modal.getSubtitle()+"");
            txtDate.setText(modal.getDate()+"");
            txtCheckBox.setChecked(modal.isSelected());
            txtPriority.setText(modal.getPriority()+"");
            txtType.setText(modal.getType()+"");

        }

    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtRank;
        protected TextView txtTitle;
        protected TextView txtSubtitle;
        protected TextView txtDate;
        protected Button btnCheckBox;
        protected TextView txtPriority;
        protected TextView txtType;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            txtRank = itemView.findViewById(R.id.txtRank);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubtitle = itemView.findViewById(R.id.txtSubtitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPriority = itemView.findViewById(R.id.txtPriority);
            btnCheckBox = (Button) itemView.findViewById(R.id.btnCheckBox);
            txtType = itemView.findViewById(R.id.txtType);

        }
        private void setHeaderDetails(TableModal tableModal)
        {
            txtRank.setBackgroundResource(R.drawable.table_header_cell_bg);
            txtTitle.setBackgroundResource(R.drawable.table_header_cell_bg);
            txtSubtitle.setBackgroundResource(R.drawable.table_header_cell_bg);
            txtPriority.setBackgroundResource(R.drawable.table_header_cell_bg);
            txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            //btnCheckBox.setBackgroundResource(R.drawable.table_header_cell_bg);
            txtType.setBackgroundResource(R.drawable.table_header_cell_bg);

            txtRank.setText("Rank");
            txtTitle.setText("Title");
            txtSubtitle.setText("Subtitle");
            txtPriority.setText("Priority");
            txtDate.setText("Date");
            btnCheckBox.setText("Show\nSelected");
            txtType.setText("Type");

        }
    }



    public List<TableModal> getModalList(){

        return tableModalList;
    }

}
