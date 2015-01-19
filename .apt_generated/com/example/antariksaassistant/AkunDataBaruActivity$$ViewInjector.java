// Generated code from Butter Knife. Do not modify!
package com.example.antariksaassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AkunDataBaruActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.antariksaassistant.AkunDataBaruActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296320, "field 'listViewAkun' and method 'onItemLongClickListViewAkun'");
    target.listViewAkun = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemLongClickListener(
      new android.widget.AdapterView.OnItemLongClickListener() {
        @Override public boolean onItemLongClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          return target.onItemLongClickListViewAkun(p2);
        }
      });
    view = finder.findRequiredView(source, 2131296322, "field 'textViewJumlahPeserta'");
    target.textViewJumlahPeserta = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296321, "method 'onClickTambah'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickTambah(p0);
        }
      });
  }

  public static void reset(com.example.antariksaassistant.AkunDataBaruActivity target) {
    target.listViewAkun = null;
    target.textViewJumlahPeserta = null;
  }
}
