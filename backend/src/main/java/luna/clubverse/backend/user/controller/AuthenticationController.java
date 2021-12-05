package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.user.entity.User;
import luna.clubverse.backend.user.repository.UserRepository;
import luna.clubverse.backend.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    private final UserService userService;
    private  final UserRepository userRepository;

    public AuthenticationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Do not change this function
    // Do not change the name of index.html in templates folder
    @RequestMapping("/home")
    public String welcome() {
        return "index";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/studentHomePage/{userId}")
    public String userHomePage(Model model, @PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        model.addAttribute("user", user);
        return "studentHomePage";
    }

    // Buradaki controllerlar Rest değil sadece Controller
    // Bunların işi html dosyalarını çalıştırmak
    // Html dosyalarının resources da templates altına ekleneceğini front endciler biliyor ama demiş olayım
    // Güzel bir dosyalama ile eklerlerse daha güzel olur

    // url lerin restle karışmamasına dikkat edin
    // Club ve evente de rest olmayan controller ekledim, eskiden yaptıklarımızı ise isim olarak RestController diye güncelledim başka gerekirse eklersiniz

    // Terk düşüncem rest controller ve controller diye ayırmaya gerek var mıydı?
    // Hangilerinin html döndürmek için hangşlerinin data ekleme güncellme vs olduğunu karıştırmamak  için eklemek bana mantıklı geldi farklı fikriniz varsa alırım :P

    // urlyi açtığınızda  (type=Forbidden, status=403) hatası alıyorsanız authentication eklememişsinizdir
    // authenticationla uğraşma istemezsenin security ->SecurityConfiguration a gidip configure(HttpSecurity http)  fonksiyonun içine
    //      /login ve /home gibi 8080 den sonrası eşleşecek şekilde url yi yazım .permitAll() derseniz
    //      o url ye atılan istekler herkes tarfından ulaşılabilir olacaktır

    /*

    // Bu fonksiyon direkt html gönderiyor (içinde backendden hiçbir biligi eklemiyor)
    @RequestMapping("/event_list")
    public String welcome2() {
        return "event_list"; // return e yazılan string templates deki html dosyasının isimi ile eşleşmeli (.html uzantısı olmayack)
                            //      bakınız ilk fonksiyondaki index gibi
    }
    // Bu fonksyion List<EventQueryResponseDemo> yi events adlı modele yükleyerek html dosyasını çalıştırıyor. Html dosyası içinde events. diyerek istenilen kısma ulaşılabilir
    // (örnek için entegration-demo-2 deki htmle bakılabilir)
    @RequestMapping("/admin_event_list_demo")
    public String getAll( Model model) {
        List<EventQueryResponseDemo> events = eventService.getAllDemo(); // event service i kullandığı için eventService Rest controllerlardki gibi property olarak eklenmeli
        model.addAttribute("events", events);
        return "admin_event_list_demo";
    }
     */
}
