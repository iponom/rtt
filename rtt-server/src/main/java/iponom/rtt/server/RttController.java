package iponom.rtt.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ilya.Ponomarev
 * @version 1.0 / 08.06.2018
 */
@Controller
public class RttController {

    @GetMapping("call")
    public void call() {
    }

    @GetMapping("timestamp")
    @ResponseBody
    public String timestamp() {
        return Long.toString(System.currentTimeMillis());
    }

}
