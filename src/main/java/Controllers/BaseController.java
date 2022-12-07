package Controllers;

import InterfacesDefinition.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/tree")
public class BaseController {

    @Autowired
    private IBaseService service;

    @GetMapping("/{id}")
    public

    @GetMapping
    public Tree getTreeById(@RequestParam String name,
                            @RequestParam int age) {
        return repository.findFirstByCommonNameIgnoreCaseAndAge(name, age);
    }
}
