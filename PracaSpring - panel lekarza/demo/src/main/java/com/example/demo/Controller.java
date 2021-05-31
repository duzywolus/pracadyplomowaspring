package com.example.demo;


import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private LekarzRepository lekarzRepo;
    @Autowired
    private PacjentRepository pacjentRepo;
    @Autowired
    private WizytyRepository wizytyRepo;

    @GetMapping("/medimed")
    public String viewHomePage() {
        return "homepage";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("lekarz", new Lekarz());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String saveLekarz(Lekarz lekarz) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(lekarz.getHasloLekarz());
        lekarz.setHasloLekarz(encodedPassword);
        lekarzRepo.save(lekarz);

        return "register_success";
    }

    @GetMapping("/startpage")
    public String viewStartPage() {
        return "panel_lekarza";
    }

    @GetMapping("/pacjent_list")
    public String userList(Model model) {

        List<Pacjent> pacjentList = pacjentRepo.findAll();
        model.addAttribute("pacjentList", pacjentList);

        return "lista_pacjentow";
    }

    /*@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }*/

    @GetMapping("/visit_register")
    public String showVisitForm(Model model) {
        model.addAttribute("wizyta", new Wizyty());

        return "dodawanie_wizyt";
    }

    @PostMapping("/visit_registered")
    public String saveVisit(Wizyty wizyta) {

        wizytyRepo.save(wizyta);

        return "visit_register_success";
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }


    @GetMapping("/your_created_visits")
    public String createdVisits(@AuthenticationPrincipal LekarzDetails lekarz, Model model) {

        List<Wizyty> wizytyList = wizytyRepo.findYourCreatedVisit(lekarz.getUsername());
        model.addAttribute("wizytyList", wizytyList);


        return "twoje_dodane_wizyty";
    }

    @RequestMapping("/anulowanie-wizyty/{id}")
    public String anulowanieWizyty(@PathVariable Integer id) {

        wizytyRepo.deleteById(id);
        return "redirect:/your_created_visits";
    }

    @GetMapping("/rozpocznij_wizyte")
    public String startVisit(@AuthenticationPrincipal LekarzDetails lekarz, Model model) {

        List<Wizyty> wizytyList = wizytyRepo.findByStatus(lekarz.getUsername());
        model.addAttribute("wizytyList", wizytyList);


        return "rozpoczecie_wizyty";

    }


    /*@GetMapping("/okno-wizyty/{id}")
    public String oknoWizyty(@PathVariable("id") Integer id, Model model){
        Wizyty wizyta = wizytyRepo.findById(id).get();
        model.addAttribute("wizyta", wizyta);

        return "okno_wizyty";
    }*/


    @GetMapping (value = "/okno-wizyty/{id}")
    public String oknoWizyty(@PathVariable("id") Integer id, Model model)
        {

        Wizyty znajdzwizyte = wizytyRepo.findById(id).get();
        model.addAttribute("znajdzwizyte", znajdzwizyte);

        return "okno_wizyty";
    }


    @RequestMapping(value = "/visit_completed/{id}")
    public String zakonczWizyte(@PathVariable("id") Integer id, String diagnoza, String zalecenia, String symptomy){

        Wizyty wizyta = wizytyRepo.findById(id).get();
       String nowaDiagnoza = wizyta.getDiagnoza();
        wizyta.setDiagnoza(nowaDiagnoza);
        String noweZalecenia = wizyta.getZalecenia();
        wizyta.setDiagnoza(noweZalecenia);
        String noweSymptomy = wizyta.getSymptomy();
        wizyta.setDiagnoza(noweSymptomy);

        wizytyRepo.zakonczenieWizyty(symptomy, diagnoza, zalecenia, id);
        return "visit_success";
    }

    /*@RequestMapping (value = "/visit_completed")
    public String zakonczWizyte(@RequestParam("termin") String termin,
                                @RequestParam("godzina") String godzina,
                                @RequestParam("lekarz") String lekarz,
                                @RequestParam("rodzaj") String rodzaj,
                                @RequestParam("pacjent") String pacjent,
                                @RequestParam("status") String status,
                                @RequestParam("symptomy") String symptomy,
                                @RequestParam("diagnoza") String diagnoza,
                                @RequestParam("zalecenia") String zalecenia,
                                @RequestParam("id") Integer id){
        //String nowyStatus = "zakonczona";
        Wizyty wizytaPoprzednia = wizytyRepo.findWizytaById(id).get();

        termin = wizytaPoprzednia.getTermin();
        Wizyty wizyta = new Wizyty(termin, godzina, lekarz, rodzaj, pacjent, status, symptomy, diagnoza, zalecenia, id);
        wizytyRepo.save(wizyta);
        return "visit_success";
    }*/

    /*@GetMapping(value = "/visit_completed")
    public String zakonczWizyte(Wizyty wizyta, @RequestParam("id") Integer id,
                                @RequestParam("symptomy") String symptomy,
                                @RequestParam("diagnoza") String diagnoza,
                                @RequestParam("zalecenia") String zalecenia, Model model) {

        model.addAttribute("wizyta", wizyta);
        String nowyStatus = "zakonczona";
        wizyta.setStatus(nowyStatus);
        wizyta.setSymptomy(symptomy);
        wizyta.setDiagnoza(diagnoza);
        wizyta.setZalecenia(zalecenia);

        wizytyRepo.zakonczenieWizyty(symptomy, diagnoza, zalecenia, id);
        return "visit_success";
    }*/


    @GetMapping("/patient_history")
    public String patientHistory(Model model, String pacjent) {

        model.addAttribute("wizytyList", wizytyRepo.patientHistory(pacjent));


        return "historia_pacjenta";
    }

    @GetMapping("/your_visit_history")
    public String yourHistory(@AuthenticationPrincipal LekarzDetails lekarz, Model model) {

        model.addAttribute("wizytyList", wizytyRepo.findYourVisitHistory(lekarz.getUsername()));


        return "historia_twoich_wizyt";
    }

}
