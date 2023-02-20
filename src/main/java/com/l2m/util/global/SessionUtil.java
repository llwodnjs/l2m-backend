package com.l2m.util.global;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.l2m.model.CustomUserDetails;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Session 관련 Util
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class SessionUtil {

	/**
	 * Session 값 가져오기
	 * 
	 * @return
	 */
	public static CustomUserDetails getSession() {
		/**
		 * 3가지 타입이 있음
		 * getContext()까지만 있고 인증 주체자체가 없는 경우
		 * 인증이 안되서 anomymous인 경우
		 * 인증이 되서 ExtendPrincipal이 된 경우
		 * 
		 * 여기서 1번째 경우는 Zuul 및 해당 시큐리티 설정에 의해 일어날 일이 없음
		 * 2번째의 경우 ResourceConfigurer에서 principal을 ExtendPrincipal로 오도록 설정해두었음
		 * 
		 * 그에 의한 타입 체크 처리를 진행하고 타입이 맞지 않으면 미등록자로서 인식함
		 * 
		 * 2021-03-25 필터 자체가 없을 시 getAuthentication자체가 없는 문제가 있어서 추가 처리 원아원과 맞춤
		 */
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getClass().isAssignableFrom(AnonymousAuthenticationToken.class) ? new CustomUserDetails() : (CustomUserDetails) authentication.getPrincipal();
	}

}
