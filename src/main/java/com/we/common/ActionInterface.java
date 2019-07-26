package com.we.common;

import java.util.Map;

/**
 * Created by aman.ullah on 3/10/2019.
 */
public interface ActionInterface {
    Map executePreCondition(Map parameters);
    Map execute(Map previousResult);
    Map executePostCondition(Map previousResult);
    Map buildSuccessResult(Map executeResult);
    Map buildFailureResult(Map executeResult);
}
