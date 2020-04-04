package com.example.mvpsample.domain;

public class UseCaseHandler {

    private final static String TAG = UseCaseHandler.class.getSimpleName();
    private static UseCaseHandler INSTANCE;
    private final UseCaseScheduler mUseCaseScheduler;

    public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue, E extends UseCase.ResponseError> R executeSync(
            final UseCase<T, R, E> useCase, T values) {

        R result = null;
        useCase.setRequestValues(values);


        result = useCase.runSync();

        return result;

    }

    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue, Z extends UseCase.ResponseError> void execute(
            final UseCase<T, R, Z> useCase, T values, UseCase.UseCaseCallback<R, Z> callback) {


        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper(callback, this));


        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    public <V extends UseCase.ResponseValue, E extends UseCase.ResponseError> void notifyResponse(final V response, final UseCase.UseCaseCallback<V, E> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    private <V extends UseCase.ResponseValue, U extends UseCase.ResponseError> void notifyError(final U error, final UseCase.UseCaseCallback<V, U> useCaseCallback) {
        mUseCaseScheduler.onError(error, useCaseCallback);
    }

    private static final class UiCallbackWrapper<V extends UseCase.ResponseValue, U extends UseCase.ResponseError> implements UseCase.UseCaseCallback<V, U> {
        private final UseCase.UseCaseCallback<V, U> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        UiCallbackWrapper(UseCase.UseCaseCallback<V, U> callback, UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError(U error) {
            mUseCaseHandler.notifyError(error, mCallback);
        }

    }
}