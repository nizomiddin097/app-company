package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.ApiResponce;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * Companylarni qaytaradigan method
     *
     * @param id
     * @return
     */
    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    /**
     * Company larni qushadigan method
     *
     * @param companyDto
     * @return apiResponce
     */
    public ApiResponce addCompany(CompanyDto companyDto) {
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName) {
            return new ApiResponce("Bunday Company mavjud", false);
        }
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);


        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new ApiResponce("Company saqlandi", true);
    }

    /**
     * Biz bunda companiylarni @PathVariable yulidagi ID orqali edit qildik
     *
     * @param id
     * @param companyDto
     * @return
     */
    public ApiResponce editCompany(Integer id, CompanyDto companyDto) {
        boolean existsByCorpNameAndIdNot = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        if (existsByCorpNameAndIdNot) {
            return new ApiResponce("Bunday Company mavjud",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponce("Bunday Company mavjud emas",false);
        }
        // Company address buyicha ham saqlangan balki adres buyicha ham tekshirish kerakdir

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);


        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new ApiResponce("Company taxrirlandi", true);
    }
    /**
     * Companyni @Pathvariable dagi id orqali delete qiladigan method
     * @param id
     * @return apiResponce
     */
    public ApiResponce deleteCompany(Integer id){
        companyRepository.deleteById(id);
        return new ApiResponce("Company uchirildi",true);
    }

}
