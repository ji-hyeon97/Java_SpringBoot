package ithings.backend.controller;

import ithings.backend.domain.MemberBasic;
import ithings.backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        MemberBasic member = new MemberBasic();
        member.setName(form.getName());
        member.setDate(form.getDate());
        member.setWavelength(form.getWavelength());
        member.setPrintPower(form.getPrintPower());
        member.setResearchTime(form.getResearchTime());
        member.setControl(form.getControl());
        member.setExperiment(form.getExperiment());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<MemberBasic> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}