package root.demo.controller;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/magazine")
public class MagazineController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @GetMapping(path="/getFormNewMagazine", produces = "application/json")
    public @ResponseBody FormFieldsDto get(){
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_dodavanjeCasopisa");
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> prop = tfd.getFormFields();

        return new FormFieldsDto(task.getId(),pi.getId(),prop);
    }


    @PostMapping(path = "/postForm/{taskId}/{username}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId, @PathVariable String username){
        HashMap<String,Object> map = this.mapListToDto(dto);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId,"newMagazine",dto);
        runtimeService.setVariable(processInstanceId,"newMagazineChiefEditor",username);
        formService.submitTaskForm(taskId,map);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/getReviewersAndEditorsForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getReviewersAndEditorsForm()
    {
        Task task = taskService.createTaskQuery().taskDefinitionKey("Task_189ex3l").singleResult();
        String processInstanceId = task.getProcessInstanceId();

        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> prop = tfd.getFormFields();

        return new FormFieldsDto(task.getId(),processInstanceId,prop);
    }

    @PostMapping(path="/postFormReviewersAndEditors/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postFormReviewersAndEditors(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
        HashMap<String,Object> map = this.mapListToDto(dto);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId,"EditorAndReviewer",dto);
        formService.submitTaskForm(taskId,map);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/getApproveMagazineForm",produces = "application/json")
    public @ResponseBody FormFieldsDto getMagazineConfirmForm() {

        Task task = taskService.createTaskQuery().taskDefinitionKey("Task_1q30baj").singleResult();

        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();

        return new FormFieldsDto(task.getId(), "", properties);
    }

    @PostMapping(path = "/setMagazineActivity/{taskId}",produces = "application/json")
    public @ResponseBody ResponseEntity setMagazineActivity(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId){
        HashMap<String, Object> map = this.mapListToDto(dto);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId,"checkMagazine",dto);
        formService.submitTaskForm(taskId,map);
        return new ResponseEntity<>(HttpStatus.OK);
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
