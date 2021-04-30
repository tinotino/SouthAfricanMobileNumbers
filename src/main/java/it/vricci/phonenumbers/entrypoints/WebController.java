package it.vricci.phonenumbers.entrypoints;

import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.checkNumber.CheckNumberResult;
import it.vricci.phonenumbers.core.usecases.checkNumber.CheckNumberUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

@Controller
public class WebController {
    public static final String CHECK_FORM_URI = "/";
    public final CheckNumberUseCase useCase;

    @Autowired
    public WebController(CheckNumberUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(CHECK_FORM_URI)
    public String showForm(Model model) {
        model.addAttribute("numberForm", new NumberForm());
        return "form";
    }

    @PostMapping(CHECK_FORM_URI)
    public String checkNumber(NumberForm numberForm, Model model) {
        if(StringUtils.isEmpty(numberForm.getNumber())) {
            numberForm.setResult("No number");
            model.addAttribute("numberForm", numberForm);
            return "form";
        }

        try{
            final CheckNumberResult result = useCase.check(numberForm.getNumber());
            numberForm.setResult(result.toString());
        }catch (BusinessException e){
            numberForm.setResult("Error "+ e);
        }
        model.addAttribute("numberForm", numberForm);
        return "form";
    }

}
