package com.apress.prospring4.ch3.lookup;

public class StandardLookupDemoBean implements DemoBean {
    private MyHelper myHelper;

    public void setMyHelper(MyHelper myHelper) {
        this.myHelper = myHelper;
    }

    public MyHelper getMyHelper() {
        return myHelper;
    }

    public void someOperation() {
        myHelper.doSomethingHelpful();
    }
}
