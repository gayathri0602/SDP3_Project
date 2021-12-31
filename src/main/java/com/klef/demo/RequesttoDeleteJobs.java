package com.klef.demo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reqdel")
public class RequesttoDeleteJobs
{
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "id")
 	private int id;
  @Column(name = "jid")
  private int jid;
  @Column(name = "org")
  private String org;
  @Column(name = "role")
  private String role;

public int getJid() {
	return jid;
}
public void setJid(int jid) {
	this.jid = jid;
}
public String getOrg() {
	return org;
}
public void setOrg(String org) {
	this.org = org;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

   
  
  
   
   
   
  }