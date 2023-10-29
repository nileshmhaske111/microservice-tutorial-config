package com.lcwd.user.service.entities;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(builderClassName = "Builder")
public class Hotel {
	
	private String id;
	private String name;
	private String location;
	private String about;
}