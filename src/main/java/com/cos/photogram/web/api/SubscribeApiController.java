package com.cos.photogram.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogram.config.auth.PrincipalDetails;
import com.cos.photogram.service.SubscribeService;
import com.cos.photogram.web.dto.auth.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {
	
	private final SubscribeService subscribeService;

	@PostMapping("/api/subscribe/{to_user_id}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long to_user_id) {
		subscribeService.subs(principalDetails.getUser().getId(), to_user_id);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독하기 완료", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{to_user_id}")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long to_user_id) {
		subscribeService.unSubs(principalDetails.getUser().getId(), to_user_id);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독취소 완료", null), HttpStatus.OK);
	}
}