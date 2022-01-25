package by.lamaka.servlets.mapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestBodyMapper {
    public static Map<String, String> map(HttpServletRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            while (bufferedReader.ready()) {
                String nextLine = bufferedReader.readLine();
                if (!nextLine.isEmpty() && !nextLine.startsWith("-----")) {
                    stringBuilder.append(nextLine);
                }
            }
        }
        String[] strings = stringBuilder.toString().split("\\s*Content-Disposition: form-data; name=\"\\s*");
        Arrays.stream(strings).skip(1).forEach(s -> params.put(s.split("\"")[0], s.split("\"")[1]));
        return params;
    }
}
