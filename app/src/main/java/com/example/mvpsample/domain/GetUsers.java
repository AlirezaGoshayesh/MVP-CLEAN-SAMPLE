package com.example.mvpsample.domain;

import com.example.mvpsample.data.model.UserModel;
import com.example.mvpsample.data.source.DataSource;
import com.example.mvpsample.data.source.Repository;

import java.util.List;

public class GetUsers extends UseCase<GetUsers.RequestValues, GetUsers.ResponseValue, GetUsers.ResponseError> {

    private final Repository mRepo;

    public GetUsers(Repository repository) {
        mRepo = repository;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        execute(values);
    }

    void execute(RequestValues values) {
        mRepo.getUsers(new DataSource.getUsersCallback() {
            @Override
            public void callback(List<UserModel> users) {
                if (users != null && users.size() != 0) {
                    ResponseValue response = new ResponseValue(users);
                    getUseCaseCallback().onSuccess(response);
                } else {
                    getUseCaseCallback().onError(new ResponseError("error"));
                }
            }

            @Override
            public void onFailure() {
                getUseCaseCallback().onError(new ResponseError("http error"));
            }
        });
    }

    @Override
    protected ResponseValue executeUseCaseSync(RequestValues requestValues) {
        new Throwable("executeUseCaseSync not implement");
        return null;
    }

    public static class RequestValues implements UseCase.RequestValues {
        public RequestValues() {
        }
    }

    public class ResponseValue implements UseCase.ResponseValue {

        private final List<UserModel> users;

        public ResponseValue(List<UserModel> mUsers) {
            users = mUsers;
        }

        public List<UserModel> getUsers() {
            return users;
        }

    }

    public static class ResponseError implements UseCase.ResponseError {

        private String message;

        public ResponseError(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "ResponseError{" +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
