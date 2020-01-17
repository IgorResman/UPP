package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;

import java.util.List;

@Service
public class RegistrationService implements JavaDelegate {
    @Autowired
    UserRepository userRepository;

    public void setResident(String resident) {
        this.resident = resident;
    }

    String resident = "";

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
        String name = registration.get(0).getFieldValue();
        String surname = registration.get(1).getFieldValue();
        String city = registration.get(2).getFieldValue();
        String country = registration.get(3).getFieldValue();
        String tittle = registration.get(4).getFieldValue();
        String username = registration.get(5).getFieldValue();
        String password = registration.get(6).getFieldValue();
        String role = registration.get(7).getFieldValue();
        String sciFields = registration.get(8).getFieldValue();
        String email = registration.get(9).getFieldValue();

        String r = "";

        if(this.resident != "")
        {
            if(resident == "true")
            {
                r = "resident";
                resident = "";
            }
            else
            {
                r = "user";
                resident = "";
            }
        }
        else
        {
            if(role == "true")
                r = "resident";
            else
                r = "user";
        }


        User newUser = new User(name,surname,city,country,tittle,email,username,password,sciFields,r);

        try {
            userRepository.save(newUser);
            System.out.println("Uspesno sacuvan korisnik sa korisnickim imenom: " + newUser.getUsername());
        }
        catch (NullPointerException e)
        {
            System.out.println("Nije sacuvano");
        }

    }

    public void SetResident(String resident)
    {
        this.resident = resident;
    }
}
