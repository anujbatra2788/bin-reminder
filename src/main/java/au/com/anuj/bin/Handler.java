package au.com.anuj.bin;

import au.com.anuj.bin.collection.BinCollection;
import au.com.anuj.bin.response.BinResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.util.List;

public final class Handler implements RequestHandler<Object, String> {

    @SneakyThrows
    @Override
    public String handleRequest(final Object event, final Context context) {
        final LambdaLogger logger = context.getLogger();
        final BinCollection collectors = new BinCollection();
        final List<BinResponse> response = collectors.getBinsDatesAndAttributes();
        logger.log("RESPONSE: " + response);
        return new JSONObject(response).toString();
    }
}
