package actiknow.com.motivationalthoughts.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import actiknow.com.motivationalthoughts.R;
import actiknow.com.motivationalthoughts.model.QuotesEnglish;
import actiknow.com.motivationalthoughts.utils.SetTypeFace;


public class QuotesEnglishAdapter extends RecyclerView.Adapter<QuotesEnglishAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<QuotesEnglish> QuotesEnglishList = new ArrayList<QuotesEnglish>();
    
    public QuotesEnglishAdapter(Activity activity, List<QuotesEnglish> QuotesEnglishList) {
        this.activity = activity;
        this.QuotesEnglishList = QuotesEnglishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final QuotesEnglish quotesEnglish = QuotesEnglishList.get (position);
    
        holder.tvQuotesEnglish.setTypeface (SetTypeFace.getTypeface (activity));

    
        holder.tvQuotesEnglish.setText (quotesEnglish.getQuotesEnglish ());

        final ViewHolder tempholder = holder;
    }

    @Override
    public int getItemCount() {
        return QuotesEnglishList.size ();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvQuotesEnglish;



        public ViewHolder(View view) {
            super(view);
            tvQuotesEnglish = (TextView) view.findViewById(R.id.tvQuotesEnglish);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            FAQ FAQ = FAQList.get (getLayoutPosition ());
        }
    }
}