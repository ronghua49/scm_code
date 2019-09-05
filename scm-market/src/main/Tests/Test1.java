import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.util.Base64;
import com.hotent.base.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;


public class Test1 {
    @Test
    public void tese5() throws IOException {
        String fromBase64 = Base64.getFromBase64("eyJBdXRob3JpemF0aW9uIjoiQmFzaWMgWW5CdFlXUnRhVzQ2WW5CdFFXUnRhVzVBTWpBeE9RPT0ifQ==");
        JsonNode jsonNode = JsonUtil.toJsonNode(fromBase64);
        String authorization = jsonNode.get("Authorization").asText();
        System.out.println(authorization);
        authorization = authorization.substring(6);
        String result = Base64.getFromBase64(authorization);
        System.out.println(authorization);
        System.out.println(result);
    }
}
