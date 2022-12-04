package hello.itemservice.web.message;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String items(Model model) {

        return "/message/test";
    }
}
