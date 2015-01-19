// Generated code from Butter Knife. Do not modify!
package com.example.antariksaassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PenilaianActivity$$ViewInjector {
  public static void inject(Finder finder, final com.example.antariksaassistant.PenilaianActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296346, "field 'editTextKeteranganTidakHadir'");
    target.editTextKeteranganTidakHadir = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296362, "field 'editTextKeteranganBenarSalah'");
    target.editTextKeteranganBenarSalah = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296351, "field 'textViewKeteranganGKB'");
    target.textViewKeteranganGKB = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296343, "field 'radioSakit'");
    target.radioSakit = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131296340, "field 'radioGroupKehadiran'");
    target.radioGroupKehadiran = (android.widget.RadioGroup) view;
    view = finder.findRequiredView(source, 2131296344, "field 'radioTanpaKeterangan'");
    target.radioTanpaKeterangan = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131296348, "field 'checkBoxmenyetorkan'");
    target.checkBoxmenyetorkan = (android.widget.CheckBox) view;
    view = finder.findRequiredView(source, 2131296364, "field 'textViewNilai'");
    target.textViewNilai = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296342, "field 'radioIzin'");
    target.radioIzin = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131296361, "field 'editTextBenarSalah'");
    target.editTextBenarSalah = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296341, "field 'radioHadir'");
    target.radioHadir = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131296356, "field 'editTextKeteranganIsian'");
    target.editTextKeteranganIsian = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296345, "field 'textViewKeteranganTidakHadir'");
    target.textViewKeteranganTidakHadir = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296339, "field 'textViewMateri'");
    target.textViewMateri = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296352, "field 'editTextKeteranganGKB'");
    target.editTextKeteranganGKB = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296358, "field 'editTextPilihanGanda'");
    target.editTextPilihanGanda = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296355, "field 'editTextIsian'");
    target.editTextIsian = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296350, "field 'spinnerKeterangan'");
    target.spinnerKeterangan = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296359, "field 'editTextKeteranganPilihanGanda'");
    target.editTextKeteranganPilihanGanda = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296363, "method 'onClickLihatNilaiTotal'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLihatNilaiTotal(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296366, "method 'onClickButtonReport'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickButtonReport(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296365, "method 'onClickButtonSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickButtonSave(p0);
        }
      });
  }

  public static void reset(com.example.antariksaassistant.PenilaianActivity target) {
    target.editTextKeteranganTidakHadir = null;
    target.editTextKeteranganBenarSalah = null;
    target.textViewKeteranganGKB = null;
    target.radioSakit = null;
    target.radioGroupKehadiran = null;
    target.radioTanpaKeterangan = null;
    target.checkBoxmenyetorkan = null;
    target.textViewNilai = null;
    target.radioIzin = null;
    target.editTextBenarSalah = null;
    target.radioHadir = null;
    target.editTextKeteranganIsian = null;
    target.textViewKeteranganTidakHadir = null;
    target.textViewMateri = null;
    target.editTextKeteranganGKB = null;
    target.editTextPilihanGanda = null;
    target.editTextIsian = null;
    target.spinnerKeterangan = null;
    target.editTextKeteranganPilihanGanda = null;
  }
}
