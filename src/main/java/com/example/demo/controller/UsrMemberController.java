package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.empty(loginId)) {
			return "아이디를 입력해주세요";
		}
		if (Util.empty(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		if (Util.empty(name)) {
			return "이름을 입력해주세요";
		}
		if (Util.empty(nickname)) {
			return "닉네임을 입력해주세요";
		}
		if (Util.empty(cellphoneNum)) {
			return "전화번호를 입력해주세요";
		}
		if (Util.empty(email)) {
			return "이메일을 입력해주세요";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return Util.f("이미 사용중인 아이디(%s) 입니다", loginId);
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return memberService.getMemberById(id);
	}
}