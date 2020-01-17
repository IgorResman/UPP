package root.demo.controller;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import root.demo.model.*;
import root.demo.repository.HashCodeRepository;
import root.demo.repository.UserRepository;
import root.demo.services.RegistrationService;

import javax.jws.soap.SOAPBinding;
import java.util.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    FormService formService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashCodeRepository codeRepository;



    @GetMapping(path = "/getFormRegistration", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_registracija");

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
		/*
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		*/

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

    @GetMapping(path = "/getResidentConfirmForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getResident() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_registracija");

        Task task = taskService.createTaskQuery().taskDefinitionKey("potvrdaRecTask").singleResult();


        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
		/*
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		*/

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

    @PostMapping(path = "/postForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
        HashMap<String, Object> map = this.mapListToDto(dto);

        // list all running/unsuspended instances of the process
//		    ProcessInstance processInstance =
//		        runtimeService.createProcessInstanceQuery()
//		            .processDefinitionKey("Process_1")
//		            .active() // we only want the unsuspended process instances
//		            .list().get(0);

//			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

        boolean exist = false;

        List<User> allUsers = userRepository.findAll();

        if(!allUsers.isEmpty()) {
            for(User uu: allUsers) {
                if(uu.getUsername() == dto.get(7).getFieldValue()) {
                    System.out.println("Korisnik vec postoji sa tim username");
                    exist = true;
                }
            }
        }

        if(!exist) {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            String processInstanceId = task.getProcessInstanceId();
            runtimeService.setVariable(processInstanceId, "registration", dto);
            formService.submitTaskForm(taskId, map);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/postFormResident/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postResident(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
        HashMap<String, Object> map = this.mapListToDto(dto);

        String role = dto.get(0).getFieldValue().toString();
        if(role.toString()=="true")
        {
            registrationService.setResident(role.toString());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(path = "/getFormOblasti/{procesInstance}", produces = "application/json")
    public @ResponseBody FormFieldsDto getOblasti(String procesInstance) {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        //ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracija");
        System.out.println(procesInstance);
        Task task = taskService.createTaskQuery().processInstanceId(procesInstance).list().get(0);

        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
		/*
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		*/

        return new FormFieldsDto(task.getId(), procesInstance, properties);
    }

    @GetMapping(path = "/checkHash/{sha256hex}/{username}", produces = "application/json")
    public @ResponseBody ResponseEntity getHash(@PathVariable String sha256hex, @PathVariable String username) {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        //ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracija");
        System.out.println(sha256hex);

        //Task task = taskService.createTaskQuery().processInstanceId(procesInstance).list().get(0);

        //TaskFormData tfd = formService.getTaskFormData(task.getId());
        //List<FormField> properties = tfd.getFormFields();
		/*
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		*/

        //User user = userRepository.findById(userID).get();
        //String hash = user.getHashCode();

        //if(sha256hex.equals(hash)) {
        //user.setPotvrdjen("da");
        //}

        List<HashCodeConfirm> allHashCode = codeRepository.findAll();

        for(HashCodeConfirm c: allHashCode) {
            if(c.getUsername().equals(username) && c.getHashCode().equals(sha256hex)) {
                c.setPotvrdjen("da");
                codeRepository.save(c);
                break;
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(path = "/login/{username}/{password}", produces = "application/json")
    public @ResponseBody ResponseEntity getLogin(@PathVariable String username, @PathVariable String password)
    {
        List<User> users = userRepository.findAll();
        boolean exist =false;
        String role = "";

        for(User u: users)
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
            {
                exist = true;
                switch (u.getRole()){
                    case "admin":
                        role = "admin";
                        break;
                    case "user":
                        role = "user";
                        break;
                    case "resident":
                        role = "resident";
                        break;
                    case "editor":
                        role = "editor";
                        break;
                }
            }
        }

        if(exist)
        {
            System.out.println("Uspesno se ulogovao " + username);
            return  new ResponseEntity<LoginResponse>(new LoginResponse(role,"Uspesno ste se ulogovali"),HttpStatus.OK);

        }
        else
        {
            System.out.println("Neuspesno ste se ulogovali");
            return new ResponseEntity<>("Neuspesno ste se ulogovali", HttpStatus.OK);
        }
    }

    private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(FormSubmissionDto temp : list){
            map.put(temp.getFieldId(), temp.getFieldValue());
        }

        return map;
    }
}
