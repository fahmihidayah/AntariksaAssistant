package com.models;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nilai_mingguan")
public class NilaiMingguan implements Serializable{
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Long id;
	@DatabaseField(columnName = "nama_materi")
	private String namaMateri;
	@DatabaseField(columnName = "pekan")
	private long pekan;
	@DatabaseField(columnName = "hadir")
	private boolean hadir;
	@DatabaseField(columnName = "keterangan_kehadiran")
	private String keteranganKehadiran = "";
	@DatabaseField(columnName = "gkb")
	private boolean gkb;
	@DatabaseField(columnName = "keterangan_gkb")
	private String keteranganGkb;
	@DatabaseField(columnName = "nilai_gkb")
	private int nilaiGkb = 0;
	@DatabaseField(columnName = "nilai_isian")
	private int nilaiIsian = 0;
	@DatabaseField(columnName = "nilai_pilihan_ganda")
	private int nilaiPilihanGanda = 0;
	@DatabaseField(columnName = "nilai_benar_salah")
	private int nilaiBenarSalah = 0;
	@DatabaseField(columnName = "total_nilai")
	private int totalNilai = 0;
	@DatabaseField(columnName = "keterangan_isian")
	private String keteranganIsian;
	@DatabaseField(columnName = "keterangan_pilihan_ganda")
	private String keteranganPilihanGanda;
	@DatabaseField(columnName = "keterangan_benar_salah")
	private String keteranganBenarSalah;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, columnName = "data_akun_id")
	private DataAkun dataAkun;
	
	public NilaiMingguan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaMateri() {
		return namaMateri;
	}

	public void setNamaMateri(String namaMateri) {
		this.namaMateri = namaMateri;
	}

	public Long getPekan() {
		return pekan;
	}

	public void setPekan(Long pekan) {
		this.pekan = pekan;
	}

	public Boolean getHadir() {
		return hadir;
	}

	public void setHadir(Boolean hadir) {
		this.hadir = hadir;
	}

	public String getKeteranganKehadiran() {
		return keteranganKehadiran;
	}

	public void setKeteranganKehadiran(String keteranganKehadiran) {
		this.keteranganKehadiran = keteranganKehadiran;
	}

	public Boolean getGkb() {
		return gkb;
	}

	public void setGkb(Boolean gkb) {
		this.gkb = gkb;
	}

	public String getKeteranganGkb() {
		return keteranganGkb;
	}

	public void setKeteranganGkb(String keteranganGkb) {
		this.keteranganGkb = keteranganGkb;
	}

	public Integer getNilaiGkb() {
		return nilaiGkb;
	}

	public void setNilaiGkb(Integer nilaiGkb) {
		this.nilaiGkb = nilaiGkb;
	}

	public Integer getNilaiIsian() {
		return nilaiIsian;
	}

	public void setNilaiIsian(Integer nilaiIsian) {
		this.nilaiIsian = nilaiIsian;
	}

	public Integer getNilaiPilihanGanda() {
		return nilaiPilihanGanda;
	}

	public void setNilaiPilihanGanda(Integer nilaiPilihanGanda) {
		this.nilaiPilihanGanda = nilaiPilihanGanda;
	}

	public Integer getNilaiBenarSalah() {
		return nilaiBenarSalah;
	}

	public void setNilaiBenarSalah(Integer nilaiBenarSalah) {
		this.nilaiBenarSalah = nilaiBenarSalah;
	}
	

	public DataAkun getDataAkun() {
		return dataAkun;
	}

	public void setDataAkun(DataAkun dataAkun) {
		this.dataAkun = dataAkun;
	}

	public String getKeteranganIsian() {
		return keteranganIsian;
	}

	public void setKeteranganIsian(String keteranganIsian) {
		this.keteranganIsian = keteranganIsian;
	}

	public String getKeteranganPilihanGanda() {
		return keteranganPilihanGanda;
	}

	public void setKeteranganPilihanGanda(String keteranganPilihanGanda) {
		this.keteranganPilihanGanda = keteranganPilihanGanda;
	}

	public String getKeteranganBenarSalah() {
		return keteranganBenarSalah;
	}

	public void setKeteranganBenarSalah(String keteranganBenarSalah) {
		this.keteranganBenarSalah = keteranganBenarSalah;
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
		NilaiMingguan other = (NilaiMingguan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void hitungTotalNilai(){
		if(hadir||keteranganKehadiran.equalsIgnoreCase("sakit")){
			Integer totalSementara = 0;
			if(gkb){
				totalSementara = totalSementara + nilaiGkb;
			}
			totalSementara = totalSementara + nilaiIsian + nilaiPilihanGanda + nilaiBenarSalah;
			totalNilai = totalSementara / 4;
		}
		else {
			totalNilai  = 0;
		}
	}
	
	public String getNilaiTotalHuruf(){
		if(totalNilai >= 85){
			return "A";
		}
		else if(totalNilai >= 75 && totalNilai < 85){
			return "B";
		}
		else if(totalNilai >= 50 && totalNilai < 75){
			return "C";
		}
		else if(totalNilai > 2 && totalNilai < 50){
			return "D";
		}
		else {
			return "E";
		}
	}
	public int getTotalNilai() {
		return totalNilai;
	}
}
