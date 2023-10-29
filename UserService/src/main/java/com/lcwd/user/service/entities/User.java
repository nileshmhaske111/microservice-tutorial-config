package com.lcwd.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
@Entity
@Table(name = "micro_users")
public class User {
	@Id
	@Column(name="id")
	private String userId;
	private String name;
	private String email;
	private String about;
	@Transient
	private List<Rating> ratings = new ArrayList<>();
}
