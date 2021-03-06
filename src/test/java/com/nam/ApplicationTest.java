package com.nam;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private Data data;
    private Result result;
    private Application application;

    @Before
    public void setUp(){
        data = new Data();
        application = new Application();
    }

    @Test
    public void RemovedPagesTest(){
        data.getYesterdayTable().put("1", "1001");

        result = application.process(data);

        assertEquals(result.getRemovedPages(), Arrays.asList("1"));
        assertEquals(result.getChangedPages(), Arrays.asList());
        assertEquals(result.getAddedPages(), Arrays.asList());
    }

    @Test
    public void ChangedPagesTests(){
        data.getYesterdayTable().put("1", "1001");
        data.getTodayTable().put("1", "1002");

        result = application.process(data);

        assertEquals(result.getRemovedPages(), Arrays.asList());
        assertEquals(result.getChangedPages(), Arrays.asList("1"));
        assertEquals(result.getAddedPages(), Arrays.asList());
    }

    @Test
    public void AddedPagesTest(){
        data.getYesterdayTable().put("1", "1001");
        data.getTodayTable().put("1", "1001");
        data.getTodayTable().put("2", "1002");

        result = application.process(data);

        assertEquals(result.getRemovedPages(), Arrays.asList());
        assertEquals(result.getChangedPages(), Arrays.asList());
        assertEquals(result.getAddedPages(), Arrays.asList("2"));
    }

    @Test
    public void NoModificationTest(){
        data.getYesterdayTable().put("1", "1001");
        data.getTodayTable().put("1", "1001");

        result = application.process(data);

        assertEquals(result.getRemovedPages(), Arrays.asList());
        assertEquals(result.getChangedPages(), Arrays.asList());
        assertEquals(result.getAddedPages(), Arrays.asList());
    }
}
