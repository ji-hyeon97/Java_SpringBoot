package ithings.backend.controller;

import ithings.backend.domain.MemberBasic;
import ithings.backend.service.MemberService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

@Getter@Setter
public class MemberForm {
    private String name;
    private String date;
    private int wavelength;
    private int printPower;
    private int researchTime;
    private int control;
    private int experiment;
}
