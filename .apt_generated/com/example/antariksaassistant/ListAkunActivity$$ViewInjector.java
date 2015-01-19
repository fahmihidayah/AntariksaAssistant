// Generated code from Butter Knife. Do not modify!
package com.example.antariksaassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ListAkunActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.antariksaassistant.ListAkunActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296320, "field 'listViewAkun' and method 'onItemClickListViewMateri'");
    target.listViewAkun = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClickListViewMateri(p2);
        }
      });
  }

  public static void reset(com.example.antariksaassistant.ListAkunActivity target) {
    target.listViewAkun = null;
  }
}
