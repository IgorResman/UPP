package root.demo.services;

import org.apache.ibatis.jdbc.Null;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.repository.MagazineRepository;

import java.util.List;

@Service
public class SaveMagazine implements JavaDelegate {
    @Autowired
    MagazineRepository magazineRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<FormSubmissionDto> magazine = (List<FormSubmissionDto>)execution.getVariable("newMagazine");
        String editor = (String)execution.getVariable("newMagazineChiefEditor");

        String naziv = magazine.get(0).getFieldValue();
        Long issn = Long.parseLong(magazine.get(1).getFieldValue());
        String placanje = magazine.get(2).getFieldValue();
        String oblast = magazine.get(3).getFieldValue();

        Magazine newMag = new Magazine(naziv,issn,oblast,placanje);
        newMag.setActive("ne");
        newMag.setChiefEditor(editor);

        try {
            magazineRepository.save(newMag);
            System.out.println("Uspesno sacuvan casopis sa nazivom: " + newMag.getName());

        }
        catch (NullPointerException e)
        {
            System.out.println("Nije sacuvano");

        }
    }
}
