package com.models;

import java.io.Serializable;

public class Materi implements Serializable {

	private Integer minggu;
	private String materi;

	public Materi(Integer minggu, String materi) {
		super();
		this.minggu = minggu;
		this.materi = materi;
	}

	public Integer getMinggu() {
		return minggu;
	}

	public void setMinggu(Integer minggu) {
		this.minggu = minggu;
	}

	public String getMateri() {
		return materi;
	}

	public void setMateri(String materi) {
		this.materi = materi;
	}

}
