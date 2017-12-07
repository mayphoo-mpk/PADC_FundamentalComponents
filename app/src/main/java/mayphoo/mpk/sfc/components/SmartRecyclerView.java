package mayphoo.mpk.sfc.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 11/25/2017.
 */

public class SmartRecyclerView extends RecyclerView {

    //attribute that creates Empty view
    private View mEmptyView;

    //call dataObserver call back method when changes occur in adapter view that is attached with smart recycler view
    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }
    };

    public SmartRecyclerView(Context context) {
        super(context);
    }

    public SmartRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        //check old adapter and data observer that pointed old adapter is already exist
        // if exist, unregister data observer
        Adapter oldAdapter = getAdapter();
        if(oldAdapter != null){
            oldAdapter.unregisterAdapterDataObserver(dataObserver);
        }

        super.setAdapter(adapter);
        // attach with data observer with adapter
        if(adapter != null){
            adapter.registerAdapterDataObserver(dataObserver);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView){
        mEmptyView = emptyView;
    }

    /**
     * check if adapter connected to SRV is empty. If so, show emptyView.
     */
    private void checkIfEmpty(){
        // get adapter that attached Recycler View
        boolean isEmpty = getAdapter().getItemCount() == 0;
        if(mEmptyView != null) {
            mEmptyView.setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
            // control visible/invisible adapter view items that attaches RecyclerView
            setVisibility(isEmpty ? View.INVISIBLE : View.VISIBLE);
        }
    }

}
