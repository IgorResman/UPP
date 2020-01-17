package root.demo.services;

import org.apache.commons.lang.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.model.HashCodeConfirm;
import root.demo.repository.HashCodeRepository;


@Service
public class HashService implements JavaDelegate {

    @Autowired
    HashCodeRepository hashCodeRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String hash = RandomizeString(6);

        execution.setVariable("hashValue",hash);

        HashCodeConfirm code = new HashCodeConfirm(execution.getVariable("username").toString(),hash,"ne");

        try
        {
            hashCodeRepository.save(code);
            System.out.println("Uspesno sacuvan hash code: " + hash);
        }
        catch (NullPointerException e)
        {
            System.out.println("Neuspesno sacuvan hash code");
        }

    }

    public String RandomizeString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for(int i = 0; i < n ; i++)
        {
            int index =(int)(AlphaNumericString.length()*Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
