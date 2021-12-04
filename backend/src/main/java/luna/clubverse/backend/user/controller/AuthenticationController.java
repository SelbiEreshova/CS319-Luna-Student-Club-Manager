package luna.clubverse.backend.user.controller;

import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController {

    // Buradaki controllerlar Rest değil sadece Controller
    // Bunların işi html dosyalarını çalıştırmak
    // url lerin restle karışmamasına dikkat edin
    // Club ve evente de rest olmayan controller ekledim, eskiden yaptıklarımızı ise isim olarak RestController diye güncelledim başka gerekirse eklersiniz
    // Terk düşüncem rest controller ve controller diye ayırmaya gerek var mıydı?
    // Hangilerinin html döndürmek için hangşlerinin data ekleme güncellme vs olduğunu karıştırmamak  için eklemek bana mantıklı geldi farklı fikriniz varsa alırım :P

    /*

    // Bu fonksiyon direkt html gönderiyor (içinde backendden hiçbir biligi eklemiyor)
    @RequestMapping("/event_list")
    public String welcome2() {
        return "event_list";
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
