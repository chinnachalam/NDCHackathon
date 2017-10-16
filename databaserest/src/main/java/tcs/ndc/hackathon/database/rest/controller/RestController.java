package tcs.ndc.hackathon.database.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tcs.ndc.hackathon.database.rest.dao.DaoService;
import tcs.ndc.hackathon.database.rest.dao.Response;

import java.util.UUID;

@Controller
public class RestController {

    @Autowired DaoService daoService;

    @RequestMapping(value = "/collection/{collectionName}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void createCollection(@PathVariable String collectionName) {
        daoService.createCollection(collectionName);
    }

    @RequestMapping(value = "/collection/{collectionName}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public void deleteCollection(@PathVariable String collectionName) {
        daoService.deleteCollection(collectionName);
    }

    @RequestMapping(value = "/{collectionName}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable String id, @PathVariable String collectionName) {
        return daoService.get(id, collectionName);
    }

    @RequestMapping(value = "/{collectionName}/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response saveWithId(@PathVariable String collectionName, @RequestBody Object objectToStore, @PathVariable(required = false) String id) {
        id = id == null || id == ""? UUID.randomUUID().toString() : id;
        return daoService.save(objectToStore, collectionName, id);
    }

    @RequestMapping(value = "/{collectionName}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response save(@PathVariable String collectionName, @RequestBody Object objectToStore) {
        return daoService.save(objectToStore, collectionName,  UUID.randomUUID().toString() );
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete() {
        return "delete";
    }
}