package mayphoo.mpk.sfc.components;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by User on 11/29/2017.
 */

public class SmartScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSmartScrollListener {
        void onListEndReach();
    }

    private int visibleItemCount, pastVisibleItems, totalItemCount;
    private boolean isListEndReached = false;
    private int previousDy, currentDy;

    private OnSmartScrollListener mSmartScrollListener;

    public SmartScrollListener(OnSmartScrollListener mSmartScrollListener) {
        this.mSmartScrollListener = mSmartScrollListener;
    }

    // call this method, after user scrolled
    @Override
    public void onScrolled(RecyclerView rv, int dx, int dy) {
        super.onScrolled(rv, dx, dy);

        currentDy = dy;
        if(currentDy > previousDy){
            //from top to bottom
        } else if (currentDy < previousDy){
            //from bottom to top
            isListEndReached = false;
        }

        //get visible item count (not all child view item count)
        visibleItemCount = rv.getLayoutManager().getChildCount();
        //get total item count in RV
        totalItemCount = rv.getLayoutManager().getItemCount();
        pastVisibleItems = ((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();

        previousDy = currentDy;
    }

    /* call this method when
     * 1. SCROLL_STATE_IDLE -> The RecyclerView is not currently scrolling.
     * 2. SCROLL_STATE_DRAGGING -> The RecyclerView is currently being dragged by outside input such as user touch input.
     * 3. SCROLL_STATE_SETTLING -> The RecyclerView is currently animating to a final position while not under outside control.
      */
    //call this method after user scrolled, so call callbackfunction in this method
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        super.onScrollStateChanged(recyclerView, scrollState);
        // when reach list end, calls callback method
        // if(visibleItemCount + pastVisibleItems) = totalItemCount, reach list end
        // isListEndReached -> see last item
        if(scrollState == RecyclerView.SCROLL_STATE_IDLE){
            if((visibleItemCount + pastVisibleItems)>= totalItemCount
                    && !isListEndReached) {
                isListEndReached = true;
                mSmartScrollListener.onListEndReach();
            }
        }
    }

}
