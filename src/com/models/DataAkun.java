package com.models;

import java.io.Serializable;
import java.util.Collection;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "data_akun")
public class DataAkun implements Serializable{
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = "id")
	private Long id;
	@DatabaseField(columnName = "nama_peserta")
	private String namaPeserta;
	@DatabaseField(columnName = "nama_pengajar")
	private String namaPengajar;
	@DatabaseField(columnName = "nama_asisten")
	private String namaAsisten;
	@DatabaseField(columnName = "nama_kelas")
	private String namaKelas;
	@DatabaseField(columnName = "email")
	private String email;
	@ForeignCollectionField(eager = true)
	private Collection<NilaiMingguan> listNilaiMingguan;
	
	public DataAkun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataAkun(String namaPeserta, String namaPengajar,
			String namaAsisten, String namaKelas) {
		super();
		this.namaPeserta = namaPeserta;
		this.namaPengajar = namaPengajar;
		this.namaAsisten = namaAsisten;
		this.namaKelas = namaKelas;
	}

	public DataAkun(Long id, String namaPeserta, String namaPengajar,
			String namaAsisten, String namaKelas) {
		super();
		this.id = id;
		this.namaPeserta = namaPeserta;
		this.namaPengajar = namaPengajar;
		this.namaAsisten = namaAsisten;
		this.namaKelas = namaKelas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaPeserta() {
		return namaPeserta;
	}

	public void setNamaPeserta(String namaPeserta) {
		this.namaPeserta = namaPeserta;
	}

	public String getNamaPengajar() {
		return namaPengajar;
	}

	public void setNamaPengajar(String namaPengajar) {
		this.namaPengajar = namaPengajar;
	}

	public String getNamaAsisten() {
		return namaAsisten;
	}

	public void setNamaAsisten(String namaAsisten) {
		this.namaAsisten = namaAsisten;
	}

	public String getNamaKelas() {
		return namaKelas;
	}

	public void setNamaKelas(String namaKelas) {
		this.namaKelas = namaKelas;
	}

	
	public Collection<NilaiMingguan> getListNilaiMingguan() {
		return listNilaiMingguan;
	}

	public void setListNilaiMingguan(
			Collection<NilaiMingguan> listNilaiMingguan) {
		this.listNilaiMingguan = listNilaiMingguan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataAkun other = (DataAkun) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
