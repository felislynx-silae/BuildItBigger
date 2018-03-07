package pl.fream.evryplace.androidmodule;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class AsyncInstrumentedTest extends ApplicationTestCase<Application> {

    String mResponse = null;
    CountDownLatch signal = null;

    public AsyncInstrumentedTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    @Test
    public void testTask() throws InterruptedException {
        EndpointAsyncTask task = new EndpointAsyncTask(new EndpointAsyncTask.EndpointCallback() {
            @Override
            public void onStringFetched(String result) {
                mResponse = result;
                signal.countDown();
            }
        });
        task.execute();
        signal.await();

        assertFalse(TextUtils.isEmpty(mResponse));
    }
}