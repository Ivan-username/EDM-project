package com.example.lyceum.controllers;


import com.example.lyceum.models.jpa.domain.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/lyceum-edm")
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {

        if (authentication == null) return "login";

        AuthUser authenticationPrincipalEntity = (AuthUser) authentication.getPrincipal();


        return "profile";
    }

    @PostMapping("/profile/update")
    public String profileUpdate(){
        return "profile";
    }


//    public static void main(String[] args) {
//        Sheppard sheppard = new Sheppard("bark");
//
//        Integer integer = 2;
//
//        System.out.println(sheppard.getVoice());
//
//        Object spring = sheppard;
//
//        System.out.println(spring.getClass());
//
//
//        Object spring2 = integer;
//
//        Voicable spring21 = (Voicable) spring2;
//
//        System.out.println(spring.getClass());
//
//
//    }
//
//    interface Voicable {
//        String getVoice();
//    }
//
//    static class Dog  implements Voicable{
//        protected String voice;
//
//        public String getVoice() {
//            return voice;
//        }
//
//        public void setVoice(String voice) {
//            this.voice = voice;
//        }
//    }
//
//    static class Sheppard extends Dog {
//        protected String voice;
//
//        public Sheppard(String voice) {
//            setVoice(voice);
//        }
//    }
}
