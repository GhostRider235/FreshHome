package com.FreshHome.app.model.dto;

import com.FreshHome.app.model.RolesSesiones;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class response {
	private String tokenJWT;
}
