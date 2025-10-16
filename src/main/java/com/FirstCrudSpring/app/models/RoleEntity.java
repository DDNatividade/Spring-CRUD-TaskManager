package com.FirstCrudSpring.app.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissionList = new HashSet<>();
    
    
	
    @OneToMany(mappedBy ="rol", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserEntity> users;



	public Long getId() {
		return role_id;
	}



	public void setId(Long id) {
		this.role_id = id;
	}



	public RoleEnum getRoleEnum() {
		return roleEnum;
	}



	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}



	public Set<PermissionEntity> getPermissionList() {
		return permissionList;
	}



	public void setPermissionList(Set<PermissionEntity> permissionList) {
		this.permissionList = permissionList;
	}



	public List<UserEntity> getUsers() {
		return users;
	}



	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
    
    
}
