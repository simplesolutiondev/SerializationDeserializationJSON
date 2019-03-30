package simplesolution.dev;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializationDeserializationJSONSample {

    public static void main(String... args) {
        useGsonSamples();
        useJacksonSamples();
        useFastjsonSamples();
        useMoshiSamples();
        useJsoniterSamples();
    }

    private static List<Employee> createTestObject() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(123, "Buffet", "Jimmy"));
        employees.add(new Employee(456, "Cartman", "Eric"));
        return employees;
    }

    private static String createJsonString() {
        return "[{\"employeeId\":789,\"firstName\":\"Jefferson\",\"lastName\":\"Jimmy\"},{\"employeeId\":987,\"firstName\":\"Cartman\",\"lastName\":\"George\"}]";
    }

    private static void useGsonSamples() {
        List<Employee> employees = createTestObject();
        Gson gson = new Gson();
        String jsonStringFromObject = gson.toJson(employees);
        System.out.println("[Gson] JSON String from Object: " + jsonStringFromObject);

        String jsonString = createJsonString();
        List<Employee> objectFromJsonString = gson.fromJson(jsonString, List.class);
        System.out.println("[Gson] Object from String: " + objectFromJsonString);
    }

    private static void useJacksonSamples() {
        try {
            List<Employee> employees = createTestObject();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStringFromObject = objectMapper.writeValueAsString(employees);
            System.out.println("[Jackson] JSON String from Object: " + jsonStringFromObject);

            String jsonString = createJsonString();
            List<Employee> objectFromJsonString = objectMapper.readValue(jsonString, List.class);
            System.out.println("[Jackson] Object from String: " + objectFromJsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void useFastjsonSamples() {
        List<Employee> employees = createTestObject();
        String jsonStringFromObject = JSON.toJSONString(employees);
        System.out.println("[Fastjson] JSON String from Object: " + jsonStringFromObject);

        String jsonString = createJsonString();
        List<Employee> objectFromJsonString = JSON.parseObject(jsonString, List.class);
        System.out.println("[Fastjson] Object from String: " + objectFromJsonString);
    }

    private static void useMoshiSamples() {
        try {
            List<Employee> employees = createTestObject();
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List> jsonAdapter = moshi.adapter(List.class);
            String jsonStringFromObject = jsonAdapter.toJson(employees);
            System.out.println("[Moshi] JSON String from Object: " + jsonStringFromObject);

            String jsonString = createJsonString();
            List<Employee> objectFromJsonString = jsonAdapter.fromJson(jsonString);
            System.out.println("[Moshi] Object from String: " + objectFromJsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void useJsoniterSamples() {
        List<Employee> employees = createTestObject();
        String jsonStringFromObject = JsonStream.serialize(employees);
        System.out.println("[Jsoniter] JSON String from Object: " + jsonStringFromObject);

        String jsonString = createJsonString();
        List<Employee> objectFromJsonString = JsonIterator.deserialize(jsonString, List.class);
        System.out.println("[Jsoniter] Object from String: " + objectFromJsonString);
    }

}
