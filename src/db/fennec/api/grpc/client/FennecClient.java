package db.fennec.api.grpc.client;

import com.google.common.flogger.FluentLogger;
import db.fennec.api.grpc.client.error.FennecException;
import db.fennec.fql.FData;
import db.fennec.fql.FQuery;
import db.fennec.fql.FResult;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Supplier;

public class FennecClient implements Closeable {

    private static final FluentLogger log = FluentLogger.forEnclosingClass();

    private String host;
    private int port;
    private FennecGrpcClient client;

    public FennecClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public FResult query(FQuery query) throws FennecException {
        return handleError(() -> client.query(query));
    }

    public void insert(Iterable<FData> data, String field, String namespace) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.insert(data, field, namespace);
            return 0;
        };
        handleError(fun);
    }

    public void upsert(Iterable<FData> data, String field, String namespace) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.upsert(data, field, namespace);
            return 0;
        };
        handleError(fun);
    }

    public void remove(long low, long high, String field, String namespace) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.remove(low, high, field, namespace);
            return 0;
        };
        handleError(fun);
    }

    public void removeNamespace(String namespace) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.removeNamespace(namespace);
            return 0;
        };
        handleError(fun);
    }

    private <T> T handleError(Supplier<T> fun) throws FennecException {
        try {
            return fun.get();
        } catch (Exception e) {
            log.atWarning().withCause(e).log("Failed to execute action due to: '%s'", e.getMessage());
            //TODO
        }
        throw new FennecException(-1, "Undefined internal error");
    }

    public void connect() throws FennecException {
        try {
            client.connect();
        } catch (Exception e) {
            log.atWarning().withCause(e).log("Failed to connect to server (%s:%s) due to: '%s'", host, port, e.getMessage());
            // TODO
            throw new FennecException(-1, String.format("Failed to connect to server (%s:%s)", host, port));
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
