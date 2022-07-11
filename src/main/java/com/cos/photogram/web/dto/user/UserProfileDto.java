package com.cos.photogram.web.dto.user;

import com.cos.photogram.domain.user.User;

import lombok.Data;

@Data
public class UserProfileDto {

	private User user;
	private boolean pageOwnerState;
	private int imageCount;
}
