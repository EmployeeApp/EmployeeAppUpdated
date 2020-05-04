package com.example.itprototypezero;

import android.app.Application;
import android.content.Context;

import ControlMessages.AuthenticationMessage;

import Core.MobileClient;

public class ThisApplication extends Application {

    MobileClient mobileClient;
    private Context currentContext=null;

    private String organisationID=null;
    private String employeeID = null;

    public String getOrganisationID() {
        return organisationID;
    }

    public String getEmployeeID(){
        return this.employeeID;
    }

    public void setOrganisationID(String organisationID) {
        this.organisationID = organisationID;
    }

    public void setEmployeeID(String employeeID) {this.employeeID = employeeID; }

    public void connectToServer(final String employeeID){
        this.employeeID = employeeID;

        Thread connectThd = new Thread(new Runnable() {
            @Override
            public void run() {
                mobileClient = new MobileClient(currentContext);
                mobileClient.connect(employeeID);

                Object o = mobileClient.readNext();

                if(o instanceof AuthenticationMessage){
                    if(currentContext instanceof  SignUp)
                        ((SignUp)currentContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((SignUp)currentContext).showOTPScreen();
                            }
                        });
                }
            }
        });
        connectThd.start();
    }

    public void connectToServer(final String employeeID, final String token){
        this.employeeID = employeeID;

        Thread connectThd = new Thread(new Runnable() {
            @Override
            public void run() {
                mobileClient = new MobileClient(currentContext);
                mobileClient.connect(employeeID,token);

                Object o = mobileClient.readNext();

                if(o instanceof AuthenticationMessage){
                    if(currentContext instanceof  SignUp)
                        ((SignUp)currentContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((SignUp)currentContext).showOTPScreen();
                            }
                        });

                    if(currentContext instanceof SplashScreen){
                        if(((AuthenticationMessage)o).getMESSAGEBODY().equals("VALID"))
                            ((SplashScreen) currentContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((SplashScreen)currentContext).goToStartScan();
                                }
                            });
                        else if(((AuthenticationMessage)o).getMESSAGEBODY().equals("INVALID")){
                            ((SplashScreen) currentContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((SplashScreen)currentContext).goToSignUp();
                                }
                            });
                        }
                    }
                }
            }
        });
        connectThd.start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(Context currentContext) {
        this.currentContext = currentContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
