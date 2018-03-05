package pl.fream.evryplace.androidmodule;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;

import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
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