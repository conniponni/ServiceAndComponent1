package se.iths.connie.serviceandcomponent.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.connie.serviceandcomponent.service.ATMService;

@Controller
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/")
    public String getBalance(Model model) {
        int balance = atmService.getBalance();
        model.addAttribute("balance", balance);
        return "balance";
    }
}
