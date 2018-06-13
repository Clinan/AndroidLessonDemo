package cn.edu.hdu.lessiontest5.fragment;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by arter on 2018/4/20.
 */

public class BaseFragment extends Fragment {
    protected Activity mActivity;

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
