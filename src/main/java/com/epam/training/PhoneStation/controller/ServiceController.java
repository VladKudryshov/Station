package com.epam.training.PhoneStation.controller;


import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.ContractService;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    @Autowired
    private ServiceModelService serviceModelService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    private Authentication auth;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView service(ModelAndView model) {
        List<ServiceEntity> services = serviceModelService.getAll();
        model.addObject("listService", services);
        model.setViewName("admin/service");
        return model;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView catalog(ModelAndView model) {
        List<ServiceEntity> services = serviceModelService.getAll();
        List<ServiceEntity> servicesByUser = new ArrayList<>();

        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userService.getByUserName(userName);

        List<ContractEntity> contractByUser = user.getContractEntities();

        for (ContractEntity contract : contractByUser) {
            servicesByUser.add(contract.getService());
        }

        List<ServiceEntity> result = services.stream()
                .filter(item -> !servicesByUser.contains(item))
                .collect(Collectors.toList());

        model.addObject("listService", result);
        model.setViewName("users/catalogService");
        return model;
    }

    @RequestMapping(value = "/connected", method = RequestMethod.GET)
    public ModelAndView addedService(HttpServletRequest request, ModelAndView model) {
        UserEntity user = userService.getByUserName(request.getUserPrincipal().getName());
        List<ContractEntity> contractByUser = user.getContractEntities();

        model.addObject("listContract", contractByUser);
        model.setViewName("users/addedService");
        return model;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addService(ModelAndView model) {
        ServiceEntity service = new ServiceEntity();
        model.addObject("service", service);
        model.setViewName("admin/addService");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveService(@ModelAttribute ServiceEntity service) {
        if (service.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            serviceModelService.addService(service);
        } else {
            serviceModelService.update(service);
        }
        return new ModelAndView("redirect:/service");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView deleteService(@PathVariable(value = "id") Long serviceId) {
        serviceModelService.delete(serviceId);
        return new ModelAndView("redirect:/service/");
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable(value = "id") Long serviceId) {
        ServiceEntity service = serviceModelService.getById(serviceId);
        ModelAndView model = new ModelAndView();
        model.addObject("serviceEdit", service);
        model.setViewName("admin/editService");

        return model;
    }

    @RequestMapping(value = "{serviceId}/activate", method = RequestMethod.GET)
    public String activateTheService(@PathVariable long serviceId){
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        contractService.addContract(userName,serviceId);
        return "redirect:/service/catalog";
    }

    @RequestMapping(value = "{serviceId}/deactivate", method = RequestMethod.GET)
    public String deactivateTheService(@PathVariable long serviceId){
        ContractEntity contract = contractService.getById(serviceId);
        if(contract.getPayment().getPaid()){
            contract.setUser(null);
            contractService.update(contract);
        }
        return "redirect:/service/connected";
    }


}
