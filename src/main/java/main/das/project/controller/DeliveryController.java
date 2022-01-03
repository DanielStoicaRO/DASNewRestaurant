package main.das.project.controller;

import io.swagger.models.auth.In;
import main.das.project.config.FileUploadUtil;
import main.das.project.dto.DeliveryDto;
import main.das.project.model.Category;
import main.das.project.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DeliveryController {

    private final DeliveryService deliveryService;


    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery")
    public String getDeliveryPage(Model model) {
        model.addAttribute("deliveryDto", deliveryService.findAll());
        return "delivery";
    }

    @GetMapping("/delivery/add")
    public String getAddForm(Model model) {
        model.addAttribute("deliveryDto", new DeliveryDto());
        return "delivery-add";
    }

    @PostMapping("/delivery/add")
    public String addDeliveryForm(@ModelAttribute("deliveryDto") DeliveryDto deliveryDto,
                                  @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        deliveryDto.setPhoto(fileName);

        String uploadDir = "/resources/static/images2" + deliveryDto.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        deliveryService.save(deliveryDto);
        return "redirect:/delivery";
    }

    // show edit form
    @GetMapping("/delivery/{id}/edit")
    public String getEditForm(Model model, @PathVariable Integer id) {
        // need pet data from db
        DeliveryDto deliveryToUpdate = deliveryService.findById(id);

        // add data to model
        model.addAttribute("deliveryDto", deliveryToUpdate);
        return "delivery-edit";
    }

    // update
    @PostMapping("/delivery/{id}/edit")
    public String update(@PathVariable Integer id,
                         @ModelAttribute DeliveryDto deliveryDto) {
        // update
        deliveryService.update(deliveryDto);

        // after update
        return "redirect:/delivery";
    }

    // TODO IS NOT WORKING. PLS CHECK!
    @GetMapping("/delivery/{id}/delete")
    public String delete(@PathVariable Integer id) {
        deliveryService.deleteById(id);
        return "redirect:/delivery";
    }

    @GetMapping("/menu_1")
    public String getMenu_1Page(Model model) {
        model.addAttribute("menu_1", deliveryService.findByCategory(Category.MENU_1));
        return "menu_1";
    }

    @GetMapping("/menu_2")
    public String getMenu_2Page(Model model) {
        model.addAttribute("menu_2", deliveryService.findByCategory(Category.MENU_2));
        return "menu_2";
    }

    @GetMapping("/menu_3")
    public String getMenu_3Page(Model model) {
        model.addAttribute("menu_3", deliveryService.findByCategory(Category.MENU_3));
        return "menu_3";
    }

}
