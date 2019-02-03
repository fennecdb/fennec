package db.fennec.api.grpc.client;

import com.google.common.flogger.FluentLogger;
import com.google.common.util.concurrent.RateLimiter;
import db.fennec.api.grpc.client.error.FennecException;
import db.fennec.common.LogDefinition;
import db.fennec.fql.*;

import java.io.Closeable;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import java.util.logging.Level;

public class FennecClient implements Closeable {

    private static final FluentLogger log = LogDefinition.Companion.jConfig(FluentLogger.forEnclosingClass());

    private String host;
    private int port;
    private FennecGrpcClient client;

    public FennecClient(String host, int port) throws FennecException {
        this(host, port, false);
    }

    public FennecClient(String host, int port, boolean connectDirectly) throws FennecException {
        this(host, port, connectDirectly, Level.WARNING);
    }

    public FennecClient(String host, int port, boolean connectDirectly, Level level) throws FennecException {
        this.host = host;
        this.port = port;
        this.client = new FennecGrpcClient(host, port, level);
        if (connectDirectly) {
            connect();
        }
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

    public void batchInsert(Iterable<FBatch> batch) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.batchInsert(batch);
            return 0;
        };
        handleError(fun);
    }

    public void batchUpsert(Iterable<FBatch> batch) throws FennecException {
        Supplier<Integer> fun = () -> {
            client.batchUpsert(batch);
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
        boolean successful = false;
        int retryLimit = 60;
        int i = 0;
        RateLimiter rateLimiter = RateLimiter.create(0.5);

        while (!successful && i < retryLimit) {
            try {
                rateLimiter.acquire();
                log.atInfo().log("Trying to connect to '%s:%s'... (%s/%s)", host, port, i, retryLimit);
                client.connect();
                successful = true;
                log.atInfo().log("Connected to '%s:%s'.", host, port);
            } catch (Exception e) {
                log.atWarning().withCause(e).log("Failed to connect to server '%s:%s' (%s/%s)", host, port, i, retryLimit);
            } finally {
                i++;
            }
        }
        if (!successful) {
            throw new FennecException(-1, String.format("Unable to connect to server (%s:%s), retrylimit exceeded.", host, port));
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
    }



}
