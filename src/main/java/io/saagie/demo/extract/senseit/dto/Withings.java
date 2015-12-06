package io.saagie.demo.extract.senseit.dto;

/**
 * Created by youen on 02/12/2015.
 */
public class Withings {

    public int status;
    public Body body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Withings{" +
                "status=" + status +
                ", body='" + body + '\'' +
                '}';
    }
}
