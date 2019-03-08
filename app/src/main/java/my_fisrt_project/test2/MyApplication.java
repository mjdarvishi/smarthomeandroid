package my_fisrt_project.test2;

import android.app.Application;

/**
 * Created by m.j on 01/10/2018.
 */
public class MyApplication extends Application {

    private int someVariable=1 ;

    public int getSomeVariable() {
        return this.someVariable;
    }

    public void setSomeVariable(int someVariable) {
        this.someVariable = someVariable;
    }
}