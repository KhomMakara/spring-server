package jwt.springjwt.util.response;

public class ResponseData<T> {

    private T body;

    public ResponseData(){};
    public ResponseData(T body) {
        super();
        this.body = body;
    }

    public T getBody() {
        return body;
    }
    public void setBody(T body) {
        this.body = body;
    }

}