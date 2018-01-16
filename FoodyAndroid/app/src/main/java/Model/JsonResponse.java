package Model;

import java.util.List;

/**
 * Created by nhox_ on 18/12/2017.
 */

public class JsonResponse<T> {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    boolean success;
    T data;

}
