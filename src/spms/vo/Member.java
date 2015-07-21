package spms.vo;

import java.util.Date;

public class Member {
	protected String no;
	protected String name;
	protected String email;
	protected String phonenum;
	protected String height;
	
	protected Date createdDate;
	protected Date modifiedDate;
	
	public String getHeight() {
		return height;
	}
	
	public Member setHeight(String height) {
		this.height = height;
		return this;
	}
	
	public String getNo(){
		return no;
	}
	
	public Member setNo(String no){
		this.no = no;
		return this;
	}
	public String getName(){
		return name;
	}
	public Member setName(String name){
		this.name = name;
		return this;
	}
	public String getEmail(){
		return email;
	}
	public Member setEmail(String email){
		this.email = email;
		return this;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public Member setPhonenum(String phonenum) {
		this.phonenum = phonenum;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	
}
