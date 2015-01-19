// Generated code from Butter Knife. Do not modify!
package com.example.antariksaassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.antariksaassistant.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296318, "method 'onClickButtonTambahData'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickButtonTambahData(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296317, "method 'onClickButtonAkunDataBaru'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickButtonAkunDataBaru(p0);
        }
      });
  }

  public static void reset(com.example.antariksaassistant.MainActivity target) {
  }
}
