package ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import application.facade.BeautyAtHomeFacade;
import infrastructure.persistence.dao.ReviewDAO;
import ui.viewmodel.ReviewForm;

/**
 * MVC controller that handles review pages.
 */
@Controller
@RequestMapping("/reviews")
public class ReviewViewController {

    private final BeautyAtHomeFacade facade;
    private final ReviewDAO reviewDAO;

    public ReviewViewController(BeautyAtHomeFacade facade, ReviewDAO reviewDAO) {
        this.facade = facade;
        this.reviewDAO = reviewDAO;
    }

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewDAO.findAll());
        model.addAttribute("reviewForm", new ReviewForm());
        return "reviews";
    }

    @PostMapping
    public String addReview(@ModelAttribute("reviewForm") ReviewForm form,
                            RedirectAttributes redirectAttributes) {
        try {
            facade.addReview(form.getBookingId(), form.getRating(), form.getText());
            redirectAttributes.addFlashAttribute("message", "Reseña registrada");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/reviews";
    }
}
