// Generated code from Butter Knife. Do not modify!
package com.example.antariksaassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DataAkunActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.antariksaassistant.DataAkunActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296332, "field 'editTextNamaAsisten'");
    target.editTextNamaAsisten = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296328, "field 'editTextEmail'");
    target.editTextEmail = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296326, "field 'editTextNamaPeserta'");
    target.editTextNamaPeserta = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296330, "field 'editTextNamaPengajar'");
    target.editTextNamaPengajar = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296334, "field 'editTextNamaKelas'");
    target.editTextNamaKelas = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296335, "method 'onClickSimpan'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickSimpan(p0);
        }
      });
  }

  public static void reset(com.example.antariksaassistant.DataAkunActivity target) {
    target.editTextNamaAsisten = null;
    target.editTextEmail = null;
    target.editTextNamaPeserta = null;
    target.editTextNamaPengajar = null;
    target.editTextNamaKelas = null;
  }
}
