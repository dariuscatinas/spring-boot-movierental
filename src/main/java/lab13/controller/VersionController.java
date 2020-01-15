package lab13.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    public String versionHash = "";

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String getVersionHash(){
        return versionHash;
    }


}
