package com.example.task10.Controller;

import com.example.task10.Model.Employee;
import com.example.task10.Repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("employee",new Employee());
        return "index";

    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employees/allemployees";
    }
    @GetMapping("/allemployees")
        public String listEmployees(Model model){
            model.addAttribute("employees",employeeRepository.findAll());
            return "employees";
        }
@GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable String id,Model model){
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
        model.addAttribute("employee",employee);
        return "employee-details";
}

}
