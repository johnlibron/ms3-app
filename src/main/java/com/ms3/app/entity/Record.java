package com.ms3.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TB_RECORD")
@DynamicInsert
public class Record {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
	private String H;
	private String I;
	private String J;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		this.A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		this.B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		this.C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		this.D = d;
	}
	public String getE() {
		return E;
	}
	public void setE(String e) {
		this.E = e;
	}
	public String getF() {
		return F;
	}
	public void setF(String f) {
		this.F = f;
	}
	public String getG() {
		return G;
	}
	public void setG(String g) {
		this.G = g;
	}
	public String getH() {
		return H;
	}
	public void setH(String h) {
		this.H = h;
	}
	public String getI() {
		return I;
	}
	public void setI(String i) {
		this.I = i;
	}
	public String getJ() {
		return J;
	}
	public void setJ(String j) {
		this.J = j;
	}
	
	@Override
	public String toString() {
		return "Record [id=" + id + ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + ", E=" + E + ", F=" + F + ", G="
				+ G + ", H=" + H + ", I=" + I + ", J=" + J + "]";
	}
	
}