package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.ApiResponce;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    /**
     * Companylarni qaytaradigan method
     * @param id
     * @return
     */

    @GetMapping("/read/{id}")
    public Company getCompany(@PathVariable Integer id){
        Company company = companyService.getCompany(id);
        return company;
    }
    /**
     * Company larni qushadigan method
     * @param companyDto
     * @return apiResponce
     */
    @PostMapping("/read")
    public ApiResponce addCompany(@RequestBody CompanyDto companyDto){
        ApiResponce apiResponce = companyService.addCompany(companyDto);
        return apiResponce;
    }
    /**
     * Biz bunda companiylarni @PathVariable yulidagi ID orqali edit qildik
     * @param id
     * @param companyDto
     * @return
     */
    @PutMapping("/edit/{id}")
    public ApiResponce editCompany(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        ApiResponce apiResponce = companyService.editCompany(id, companyDto);
        return apiResponce;
    }

    /**
     * Companyni @Pathvariable dagi id orqali delete qiladigan method
     * @param id
     * @return apiResponce
     */
    @DeleteMapping("/delete")
    public ApiResponce deleteCompany(@PathVariable Integer id){
        ApiResponce apiResponce = companyService.deleteCompany(id);
        return apiResponce;
    }
}
