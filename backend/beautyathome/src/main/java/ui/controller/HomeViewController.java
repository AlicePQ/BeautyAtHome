package ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import infrastructure.persistence.dao.BookingDAO;
import infrastructure.persistence.dao.ClientDAO;
import infrastructure.persistence.dao.ProfessionalDAO;

/**
 * Simple MVC controller that renders the landing page with quick stats and links.
 */
@Controller
public class HomeViewController {

    private final ClientDAO clientDAO;
    private final ProfessionalDAO professionalDAO;
    private final BookingDAO bookingDAO;

    public HomeViewController(ClientDAO clientDAO,
                              ProfessionalDAO professionalDAO,
                              BookingDAO bookingDAO) {
        this.clientDAO = clientDAO;
        this.professionalDAO = professionalDAO;
        this.bookingDAO = bookingDAO;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("clientCount", clientDAO.findAll().size());
        model.addAttribute("professionalCount", professionalDAO.findAll().size());
        model.addAttribute("bookingCount", bookingDAO.findAll().size());
        return "index";
    }
}
