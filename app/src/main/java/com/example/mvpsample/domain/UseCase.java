package com.example.mvpsample.domain;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue,Z extends UseCase.ResponseError> {

    private Q mRequestValues;

    private UseCaseCallback<P,Z> mUseCaseCallback;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P,Z> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P,Z> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }


    P runSync() {
        return executeUseCaseSync(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);


    protected abstract P executeUseCaseSync(Q requestValues);


    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {

    }


    /**
     * Error Data received from a request.
     */
    public interface ResponseError {

        String getMessage();

    }



    public interface UseCaseCallback<R,E> {
        void onSuccess(R response);
        void onError(E error);

    }
}
