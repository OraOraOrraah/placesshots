package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(of="username")
public class User implements Serializable {

	private static final long serialVersionUID = 4779269038264698987L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "icon")
	private String icon;
	
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_by")
	private String updateBy;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="user_connect", joinColumns = @JoinColumn(name="user_id", referencedColumnName="user_id"), inverseJoinColumns = {@JoinColumn(name="userId", referencedColumnName="userId"), @JoinColumn(name="providerId", referencedColumnName="providerId"), @JoinColumn(name="providerUserId", referencedColumnName="providerUserId")})
	private UserConnection userConnection;
	
}