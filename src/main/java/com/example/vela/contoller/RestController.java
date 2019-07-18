package com.example.vela.contoller;

import com.example.vela.repository.PayloadRepository;
import com.example.vela.response.Payload;
import com.example.vela.response.Response;
import com.example.vela.service.PayloadService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/card-scheme", headers ="Accept=application/json")
public class RestController {

    CloseableHttpClient httpClient;

    HttpPost post;

    HttpGet get;

    Gson gson = new Gson();

    CloseableHttpResponse response;

    @Autowired
    PayloadService service;


    com.example.vela.model.Payload savepayload = new com.example.vela.model.Payload();

    @RequestMapping(value = "testing",method = RequestMethod.GET)
    @ResponseBody
    public Object getTest()throws Exception{

        String getResponse = new Gson().toJson("testing to the new API");

        return getResponse;
    }

    /**
    @RequestMapping(value = "verify/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity <Response> getResponse(@PathVariable long id)throws Exception{
        Payload payload = new Payload();
        Response response = new Response();

        payload.setBank("UBS");
        payload.setScheme("visa");
        payload.setType("visa");
        response.setSuccess(true);
        response.setPayload(payload);
        return ResponseEntity.ok(response);
    }
     */

    @RequestMapping(value = "verify/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> getResponse(@PathVariable long id)throws Exception{

        httpClient = HttpClients.createDefault();

        String result;

        get = new HttpGet("https://lookup.binlist.net/"+id);
        get.setHeader("Content-Type","application/json");

        get.addHeader("Accept","Application/json");

        System.out.println(id);

        response = httpClient.execute(get);
        result = EntityUtils.toString(response.getEntity());

        JsonObject obj = new JsonParser().parse(result).getAsJsonObject();

        savepayload.setType(obj.get("type").getAsString());
        savepayload.setScheme(obj.get("scheme").getAsString());
        savepayload.setBank(obj.get("bank").getAsJsonObject().get("name").getAsString());
        savepayload.setCard(String.valueOf(id));
        service.getPayloadRepository().save(savepayload);

        Payload payload = new Payload();
        Response response = new Response();

        payload.setBank(savepayload.getBank());
        payload.setScheme(savepayload.getScheme());
        payload.setType(savepayload.getType());
        response.setSuccess(true);
        response.setPayload(payload);
        return ResponseEntity.ok(response);

    }

    @RequestMapping(value = "stats",method = RequestMethod.GET)
    @ResponseBody
    public Object getHitNumber(@RequestParam int start , @RequestParam int limit) throws Exception{

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        List<com.example.vela.model.Payload> jsonArray = service.getPayloadRepository().findAll();

        com.example.vela.stat.Response res = new com.example.vela.stat.Response();
        jsonObject1.put("545423",5);
        jsonObject1.put("679234",4);
        jsonObject1.put("329802",1);
        jsonObject.put("success",true);
        jsonObject.put("start",start);
        jsonObject.put("size",jsonArray.size());
        jsonObject.put("limit",limit);
        jsonObject.put("payload",jsonObject1);

        return jsonObject.toString();
    }



}
