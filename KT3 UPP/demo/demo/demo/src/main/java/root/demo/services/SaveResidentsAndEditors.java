package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.repository.MagazineRepository;

import java.text.Normalizer;
import java.util.List;

@Service
public class SaveResidentsAndEditors implements JavaDelegate {

    @Autowired
    MagazineRepository magazineRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<FormSubmissionDto> ediAndRev = (List<FormSubmissionDto>)execution.getVariable("EditorAndReviewer");
        List<FormSubmissionDto> mag = (List<FormSubmissionDto>)execution.getVariable("newMagazine");

        String res1 = ediAndRev.get(0).getFieldValue();
        String res2 = ediAndRev.get(1).getFieldValue();
        String edi1 = ediAndRev.get(2).getFieldValue();
        String edi2 = ediAndRev.get(3).getFieldValue();

        String naslov = mag.get(0).getFieldValue();

        List<Magazine> magazines = magazineRepository.findAll();


        for(Magazine m:magazines)
        {
            if(m.getName().equals(naslov))
            {
                m.setResident1(res1);
                m.setResident2(res2);
                m.setEditor1(edi1);
                m.setEditor2(edi2);

                try{
                    magazineRepository.save(m);
                    System.out.println("Uspesno dodati recenzenti i editori za casopis " + m.getName());
                }
                catch (NullPointerException e)
                {
                    System.out.println("Neuspesno dodati recenzenti i editori za casopis " + m.getName());
                }

                break;
            }
        }


    }
}
