package demo.domain;

/**
 * Created by yefeiw on 6/30/17.
 */
public enum OrderEventType {
    //This order has been created and no responses has been received.
    PROCESSING,
    //This order has been successfully created.
    SUCCEED,
    //This order has failed to be processed.
    FAILURE;
}
