package jwt.springjwt.util.request;

public class RequestData<T> {
    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}