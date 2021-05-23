package com.example.json_volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Exampleviewholder> {

    // Adapter Class Variables
    Context mContext;
    ArrayList<Example_item> mExampleList;
    private OnItemClickListener mlistener;



    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void SetOnItemClickListener (OnItemClickListener listener){
        mlistener = listener;

    }

    // Must needed constructor for adapter class
    public ExampleAdapter(Context mContext, ArrayList<Example_item> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
    }


    // method for Attached view layout in LayoutInflater
    @NonNull
    @Override
    public Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new Exampleviewholder(view);
    }

    // Method for set data based on current view
    @Override
    public void onBindViewHolder(@NonNull Exampleviewholder holder, int position) {

        Example_item current_item = mExampleList.get(position);

        String Image_url = current_item.getImageurl();
        String Creator_name = current_item.getCreator_name();
        int Likes = current_item.getLikes();

        Picasso.get().load(Image_url).into(holder.mImageView);
        holder.mTextViewCreator.setText("Creator : " + Creator_name);
        holder.mTextViewLikes.setText("Likes : " + Likes);


    }

    // method for count data based on arrayList size
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    // RecyclerView ViewHolder Class (Declare & Initialize XML View )
    public class Exampleviewholder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;


        public Exampleviewholder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mlistener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        mlistener.OnItemClick(position);
                    }
                    }


                }
            });


        }
    }


}
