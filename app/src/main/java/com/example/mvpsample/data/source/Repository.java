package com.example.mvpsample.data.source;

import androidx.annotation.NonNull;

public class Repository implements DataSource.Remote, DataSource {

    private static Repository INSTANCE = null;

    private final DataSource.Remote mRemoteDataSource;

    private Repository(@NonNull DataSource.Remote autoUpdateRemoteDataSource) {
        mRemoteDataSource = autoUpdateRemoteDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param dataSource the backend data source
     * @return the {@link Repository} instance
     */
    public static Repository getInstance(DataSource.Remote dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(dataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(DataSource.Remote)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getUsers(getUsersCallback callback) {
        mRemoteDataSource.getUsers(callback);
    }
}
