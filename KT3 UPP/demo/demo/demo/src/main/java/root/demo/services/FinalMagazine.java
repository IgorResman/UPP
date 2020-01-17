package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.repository.MagazineRepository;

import java.util.List;

@Service
public class FinalMagazine implements JavaDelegate {
    @Autowired
    MagazineRepository magazineRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<FormSubmissionDto> finalMagazine = (List<FormSubmissionDto>)execution.getVariable("checkMagazine");
        String aktivacija = finalMagazine.get(0).getFieldValue();

        String naslov = finalMagazine.get(1).getFieldValue();

        List<Magazine> magazines = magazineRepository.findAll();


        for(Magazine m: magazines)
        {
            if(m.getName().equals(naslov))
            {
                if(aktivacija.equals("true"))
                {
                    m.setActive("da");
                    System.out.println("Casopis sa naslovom " + naslov +" je aktivan");
                    break;
                }
                else
                {
                    System.out.println("Casopis sa naslovom " + naslov +" je ostao neaktivan");
                    break;
                }
            }
        }
    }
}
