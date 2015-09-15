package app.ui.fragment;

import mobi.kuaidian.qunakao.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import app.ui.BaseFragment;
import app.ui.activity.setting.AboutActivity;

public class SettingFragment extends BaseFragment implements OnClickListener {


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_about:
			startActivity(new Intent(getActivity(), AboutActivity.class));
			break;
		case R.id.layout_exit:
			getActivity().finish();
            System.exit(0);
			break;
		default:
			break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//设置监听器
		view.findViewById(R.id.layout_about).setOnClickListener(this);  
		view.findViewById(R.id.layout_exit).setOnClickListener(this);  
	}

}