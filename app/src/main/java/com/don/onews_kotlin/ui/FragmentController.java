package com.don.onews_kotlin.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.don.onews_kotlin.ui.home.HomeMainFragment;
import com.don.onews_kotlin.ui.search.SearchFragment;
import com.don.onews_kotlin.ui.setting.SettingFragment;
import com.don.onews_kotlin.ui.video.fragment.VideoFragment;

import java.util.ArrayList;


public class FragmentController {

	private int containerId;
	private FragmentManager fm;
	private ArrayList<Fragment> fragments;

	private static FragmentController controller;

	public static FragmentController getInstance(FragmentActivity activity, int containerId) {
		if (controller == null) {
			controller = new FragmentController(activity, containerId);
		}
		return controller;
	}

	public static void onDestroy() {
		controller = null;
	}

	private FragmentController(FragmentActivity activity, int containerId) {
		this.containerId = containerId;
		fm = activity.getSupportFragmentManager();
		initFragment();
	}

	private void initFragment() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeMainFragment());
		fragments.add(new VideoFragment());
		fragments.add(new SearchFragment());
		fragments.add(new SettingFragment());

		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			ft.add(containerId, fragment);
		}
//		ft.commit();
		ft.commitAllowingStateLoss();
	}

	public void showFragment(int position) {
		hideFragments();
		Fragment fragment = fragments.get(position);
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragment);
//		ft.commit();
		ft.commitAllowingStateLoss();
	}

	public void hideFragments() {
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			if(fragment != null) {
				ft.hide(fragment);
			}
		}
//		ft.commit();
		ft.commitAllowingStateLoss();
	}

	public Fragment getFragment(int position) {
		return fragments.get(position);
	}
}