package hu.an.jobfinder.fragment.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import hu.an.jobfinder.interfaces.OnFragmentInteractionListener;
import hu.an.jobfinder.model.JobItem;

public abstract class JobBaseFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void selectedForDetails(boolean fromList, JobItem jobItem) {
        if (mListener != null) {
            mListener.onJobSelectedForDetails(fromList, jobItem);
        }
    }

    public void selectedForFavorites(JobItem jobItem) {
        if (mListener != null) {
            mListener.onJobSelectedForFavorites(jobItem);
        }
    }

    public void search(String description, String location, int pageIndex) {
        if (mListener != null) {
            mListener.onJobSearch(description, location, pageIndex);
        }
    }

    public void showMessage(@StringRes int messageRes) {
        if (mListener != null){
            mListener.onShowMessage(messageRes);
        }
    }

    public void getNextItemPage(int pageIndex){
        if (mListener != null){
            mListener.onGetNextItemPage(pageIndex);
        }
    }
}
