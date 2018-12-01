package com.malk.citer.exception;

public class Message extends Color {

    //terminal character output
    private static final String LB = BRACKET_AND_ARROW + "[";
    private static final String RB = BRACKET_AND_ARROW + "]";
    private static final String ARROW = BRACKET_AND_ARROW + " -> " + RESET;

    //terminal command output
    private static final String EXECUTE = COMMAND + " execute";
    private static final String CONNECT = COMMAND + " connect";
    private static final String CLOSE = COMMAND + " close";

    //message for Gayo
    private static void counter() {
        System.out.print(LB + Color.COUNTER + Counter.getCount() + RB);
    }

    protected void msgExecute(String param) {
        counter();
        System.out.println(EXECUTE + "\t" + ARROW + INPUT + param);
    }

    protected void msgConnect(String param) {
        counter();
        System.out.println(CONNECT + "\t" + ARROW + param);
    }

    protected void msgClose(String param) {
        counter();
        System.out.println(CLOSE + "\t" + ARROW + param);
    }

    //message for Connector
    protected void msgConnectorSuccess(String param){
        System.out.println(SUCCESS+"\t\t\t\t"+param);
    }
    protected void msgConnectorError(String param){
        System.out.println(ERROR+"\t\t\t\t"+param);
    }


    //nothing :D
    protected void msgMe(){
        System.out.println(SUCCESS+"Gayo Java MySQL library");
        System.out.println(WARNING+"DEVELOPED BY : "+COUNTER+" Malk");
        System.out.println(SUCCESS+"github.com/malko_18");
        System.out.println(RESET+"------------------------------------------------------------------------------\n\n");
    }
}