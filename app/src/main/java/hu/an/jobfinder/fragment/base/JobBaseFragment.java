package hu.an.jobfinder.fragment.base;

import android.content.Context;
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

    public void selectedForDetails(boolean fromList, JobItem jobItem){
        if (mListener != null){
            mListener.onJobSelectedForDetails(fromList, jobItem);
        }
    }

    public void selectedForFavorites(boolean isSaved, JobItem jobItem){
        if (mListener != null){
            mListener.onJobSelectedForFavorites(isSaved, jobItem);
        }
    }
}
