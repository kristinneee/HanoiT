package com.example.demo.web;

import com.example.demo.model.DiskNumberModel;
import com.example.demo.service.HanoiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HanoiController {

    private final HanoiService hanoiService;

    public HanoiController(HanoiService hanoiService) {
        this.hanoiService = hanoiService;
    }

    @GetMapping("/hanoi")
    public String hanoi(Model model){
        model.addAttribute("disksResult", "");
        return "hanoi";
    }

   @PostMapping("/hanoi")
    public String hanoiPost(@Valid DiskNumberModel diskNumberModel,
                          Model model) {
        if(diskNumberModel.getNum() > 0 && diskNumberModel.getNum() < 100){
            List<String> res = hanoiService.hanoi(diskNumberModel.getNum());
            model.addAttribute("disksResult", res);
        }
        return "result";
    }

}
