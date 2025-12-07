package ui.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import application.facade.BeautyAtHomeFacade;
import domain.professional.Professional;
import infrastructure.persistence.dao.ProfessionalDAO;
import ui.viewmodel.ProfessionalForm;

/**
 * MVC controller that lists and registers professionals.
 */
@Controller
@RequestMapping("/professionals")
public class ProfessionalViewController {

    private final BeautyAtHomeFacade facade;
    private final ProfessionalDAO professionalDAO;

    public ProfessionalViewController(BeautyAtHomeFacade facade, ProfessionalDAO professionalDAO) {
        this.facade = facade;
        this.professionalDAO = professionalDAO;
    }

    @GetMapping
    public String listProfessionals(@RequestParam(required = false) String zone,
                                    @RequestParam(required = false) String category,
                                    Model model) {
        Iterable<Professional> data = (zone == null && category == null)
                ? professionalDAO.findAll()
                : facade.searchProfessionals(zone, category);
        List<Professional> professionals = StreamSupport
                .stream(data.spliterator(), false)
                .collect(Collectors.toList());
        Map<String, Double> ratingByProfessional = professionals.stream()
                .collect(Collectors.toMap(Professional::getId,
                        pro -> facade.getProfessionalAverageRating(pro.getId())));
        double networkAverageRating = ratingByProfessional.values().stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
        model.addAttribute("professionals", professionals);
        model.addAttribute("ratingByProfessional", ratingByProfessional);
        model.addAttribute("networkAverageRating", networkAverageRating);
        model.addAttribute("selectedZone", zone);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("professionalForm", new ProfessionalForm());
        return "professionals";
    }

    @PostMapping
    public String registerProfessional(@ModelAttribute("professionalForm") ProfessionalForm form,
                                       RedirectAttributes redirectAttributes) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("id", form.getId());
            data.put("type", form.getType());
            data.put("name", form.getName());
            data.put("photoUrl", form.getPhotoUrl());
            data.put("experienceSummary", form.getExperienceSummary());
            data.put("coverage", form.coverageAsList());
            data.put("services", Collections.emptyList());
            if (form.getBrandName() != null && !form.getBrandName().isBlank()) {
                Map<String, String> brand = new HashMap<>();
                brand.put("name", form.getBrandName());
                brand.put("logo", form.getBrandLogo());
                data.put("brand", brand);
            }
            facade.registerProfessional(data);
            redirectAttributes.addFlashAttribute("message", "Profesional registrada correctamente");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/professionals";
    }
}
