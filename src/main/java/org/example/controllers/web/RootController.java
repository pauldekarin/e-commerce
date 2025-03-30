package org.example.controllers.web;

import org.example.dto.*;
import org.example.entities.Employee;
import org.example.entities.GoodsType;
import org.example.entities.Position;
import org.example.factory.SelectWebFormField;
import org.example.factory.WebForm;
import org.example.factory.WebFormFactory;
import org.example.factory.WebFormField;
import org.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Controller
public class RootController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private PurchaseTypeService purchaseTypeService;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/purchase_type")
    public String purchaseTypePage(Model model){
        model.addAttribute("title", "Purchase Types");
        model.addAttribute("data", purchaseTypeService.findAll().stream().map(purchaseTypeService::convertToMap).collect(Collectors.toList()));

        model.addAttribute("form", WebFormFactory.createAddForm(PurchaseTypeDTO.class).setUri("/api/purchase_type/add"));
        return "fragments/content";

    }

    @GetMapping("/goods")
    public String goodsPage(Model model){
        model.addAttribute("title", "Goods");
        model.addAttribute("data", goodsService.findAll().stream().map(goodsService::convertToMap).collect(Collectors.toList()));

        model.addAttribute("form", WebFormFactory.createAddForm(GoodsDTO.class).setUri("/api/goods/add"));
        return "fragments/content";

    }

    @GetMapping("/goods_type")
    public String goodsTypePage(Model model){
        model.addAttribute("title", "Goods Types");
        model.addAttribute("data", goodsTypeService.findAll().stream().map(goodsTypeService::convertToMap).collect(Collectors.toList()));
        model.addAttribute("form", WebFormFactory.createAddForm(GoodsTypeDTO.class).setUri("/api/goods_type/add"));

        return "fragments/content";

    }

    @GetMapping("/purchase")
    public String purchasePage(Model model){
        model.addAttribute("title", "Purchases");
        model.addAttribute("data", purchaseService.findAll().stream().map(purchaseService::convertToMap).collect(Collectors.toList()));
        model.addAttribute(
                "form",
                WebFormFactory
                        .createAddForm(PurchaseDTO.class, new HashMap<>(){{
                            put("employeeId", field -> {
                                employeeService.findAllIdsWithName().forEach(field::putOption);
                            });
                            put("goodsId", field -> {
                                goodsService.findAllIdsWithName().forEach(field::putOption);
                            });
                            put("shopId", field -> {
                                shopService.findAllIdsWithName().forEach(field::putOption);
                            });
                            put("purchaseTypeId", field -> {
                                purchaseTypeService.findAllIdsWithName().forEach(field::putOption);
                            });
                        }})
                        .setUri("/api/purchase/add")
        );

        return "fragments/content";

    }

    @GetMapping("/shop")
    public String shopPage(Model model){
        model.addAttribute("title", "Shops");
        model.addAttribute("data", shopService.findAll().stream().map(shopService::convertToMap).collect(Collectors.toList()));
        model.addAttribute("form", WebFormFactory.createAddForm(ShopDTO.class).setUri("/api/shop/add"));
        return "fragments/content";

    }

    @GetMapping("/position")
    public String positions(Model model){
        model.addAttribute("title", "Positions");
        model.addAttribute("data", positionService.findAll().stream().map(positionService::convertToMap).collect(Collectors.toList()));
        model.addAttribute("form", WebFormFactory.createAddForm(PositionDTO.class).setUri("/api/position/add"));

        return "fragments/content";
    }

    @GetMapping("/employee")
    public String employees(
            @RequestParam(defaultValue = "Junior") String position,
            @RequestParam(defaultValue = "2025") int year,
            Model model
    ){
        model.addAttribute("title", "Employees");
        model.addAttribute("data", employeeService.findAll().stream().map(employeeService::convertToMap).collect(Collectors.toList()));
        model.addAttribute(
                "form",
                WebFormFactory.createAddForm(EmployeeDTO.class, new HashMap<>(){{
                    put("positionId", field -> {
                        positionService.findAllIdsWithName().forEach(field::putOption);
                    });
                    put("shopId", field -> {
                        shopService.findAllIdsWithName().forEach(field::putOption);
                    });}}).setUri("/api/employee/add"));

        return "fragments/content";
    }
}
