package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	private String[] pages= {
		"food","goods"	
	};
	
	@PostMapping("reply/reply_insert.do")
	public String reply_insert(ReplyVO vo,HttpSession session,RedirectAttributes ra) {
		String id=(String) session.getAttribute("id");
		String name=(String) session.getAttribute("name");
		vo.setFno(vo.getFno());
		vo.setTypeno(vo.getTypeno());
		vo.setMsg(vo.getMsg());
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		ra.addAttribute("no", vo.getFno());
		ra.addAttribute("fno", vo.getFno());
		return "redirect:../"+pages[vo.getTypeno()]+"/detail.do";
	}
	@GetMapping("reply/reply_delete.do")
	public String reply_delete(ReplyVO vo,RedirectAttributes ra) {
		dao.replyDelete(vo.getNo(),vo.getTypeno());
		ra.addAttribute("no", vo.getFno());
		ra.addAttribute("fno", vo.getFno());
		return "redirect:../"+pages[vo.getTypeno()]+"/detail.do";
	}
	@PostMapping("reply/reply_update.do")
	public String reply_update(ReplyVO vo,RedirectAttributes ra) {
		vo.setNo(vo.getNo());
		vo.setTypeno(vo.getTypeno());
		vo.setMsg(vo.getMsg());
		dao.replyUpdate(vo);
		ra.addAttribute("no", vo.getFno());
		ra.addAttribute("fno", vo.getFno());
		return "redirect:../"+pages[vo.getTypeno()]+"/detail.do";
	}
}
